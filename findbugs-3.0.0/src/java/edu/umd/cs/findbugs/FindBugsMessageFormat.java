/*
 * FindBugs - Find bugs in Java programs
 * Copyright (C) 2003,2004 University of Maryland
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package edu.umd.cs.findbugs;

/**
 * Format the message for a BugInstance. This class works in much the same way
 * as <code>java.text.MessageFormat</code>; however, each placeholder may have
 * an optional "key" which specifies how the object at that position should be
 * formatted.
 * <p/>
 * <p>
 * Example:
 *
 * <pre>
 * new FindBugsMessageFormat(&quot;BUG: {1} does something bad to field {2.fullField}&quot;)
 * </pre>
 *
 * In this example, the method annotation at position 1 is formatted using the
 * empty (default) key. The field annotation at position 2 is formatted using
 * the "fullField" key, which uses the long format for the field rather than the
 * usual "class.fieldname" format.
 *
 * @author David Hovemeyer
 * @see BugInstance
 */
public class FindBugsMessageFormat {
    private final String pattern;

    /**
     * Constructor.
     *
     * @param pattern
     *            the pattern for the message
     */
    public FindBugsMessageFormat(String pattern) {
        this.pattern = pattern;
    }

    public String format(BugAnnotation[] args, ClassAnnotation primaryClass) {
        return format(args, primaryClass, false);
    }

    /**
     * Format the message using the given array of BugAnnotations as arguments
     * to bind to the placeholders in the pattern string.
     *
     * @param args
     *            the BugAnnotations used as arguments
     * @param primaryClass
     *            TODO
     * @return the formatted message
     */
    public String format(BugAnnotation[] args, ClassAnnotation primaryClass, boolean abridgedMessages) {
        String pat = pattern;
        StringBuilder result = new StringBuilder();

        while (pat.length() > 0) {
            int subst = pat.indexOf('{');
            if (subst < 0) {
                result.append(pat);
                break;
            }

            result.append(pat.substring(0, subst));
            pat = pat.substring(subst + 1);

            int end = pat.indexOf('}');
            if (end < 0) {
                throw new IllegalStateException("unmatched { in " + pat);
            }

            String substPat = pat.substring(0, end);

            int dot = substPat.indexOf('.');
            String key = "";
            if (dot >= 0) {
                key = substPat.substring(dot + 1);
                substPat = substPat.substring(0, dot);
            } else if (abridgedMessages && primaryClass != null) {
                key = "givenClass";
            }

            int fieldNum;
            try {
                fieldNum = Integer.parseInt(substPat);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Bad integer value " + substPat + " in " + pattern);
            }

            // System.out.println("fn: " + fieldNum);
            if (fieldNum < 0) {
                result.append("?<?" + fieldNum + "/" + args.length + "???");
            } else if (fieldNum >= args.length) {
                result.append("?>?" + fieldNum + "/" + args.length + "???");
            } else {
                BugAnnotation field = args[fieldNum];
                String formatted = "";
                try {
                    formatted = field.format(key, primaryClass);
                } catch (IllegalArgumentException iae) {
                    if (SystemProperties.ASSERTIONS_ENABLED) {

                        throw new IllegalArgumentException("Problem processing " + pattern + " format " + substPat + " for "
                                + field.getClass().getSimpleName(), iae);
                    }
                    // unknown key -- not unprecedented when reading xml
                    // generated by older versions of findbugs
                    formatted = "\u00BF" + fieldNum + ".(key=" + key + ")?"; // "\u00BF"
                    // is
                    // inverted
                    // question
                    // mark
                    // System.err.println(iae.getMessage()+" in FindBugsMessageFormat");
                    // // FIXME: log this error better
                }
                result.append(formatted);
            }

            pat = pat.substring(end + 1);
        }

        return result.toString();
    }
}


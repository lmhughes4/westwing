var jdata = {"name":"root","children":[{"bugs":"0","dependencies":0,"name":"GreetingService","loc":12},{"bugs":"0","dependencies":0,"name":"StockPrice","loc":45},{"bugs":"0","dependencies":0,"name":"StockWatcherTest","loc":24},{"bugs":"0","dependencies":0,"name":"GreetingServiceAsync","loc":10},{"bugs":7,"dependencies":{"call_number":2,"dep_name":"StockPrice"},"name":"StockWatcher","loc":216},{"bugs":"0","dependencies":{"call_number":1,"dep_name":"GreetingService"},"name":"GreetingServiceImpl","loc":19}]}
var total = countElements(jdata);
w = parseInt(Math.sqrt(total) * 33, 13);
h = parseInt(Math.sqrt(total) * 33, 13);
var myFlower = new CodeFlower("#visualization", w, h);
myFlower.update(jdata);
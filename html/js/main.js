function init() {
 loadJSON(function(response) {
  // Parse JSON string into object
    var actual_JSON = JSON.parse(response);
    console.log(actual_JSON);

    Object.keys(actual_JSON.tests).forEach(function(key,index) {
      addRow(key, actual_JSON.tests[key]);
    });

 });
}

function addRow(site, values){

  console.log(site);
  console.log(values);

  var table = document.getElementById("resultstable");

  var row = table.insertRow(-1);

  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  var cell3 = row.insertCell(2);

  cell1.innerHTML = site;
  cell2.innerHTML = values.time;
  if(values.status){
    cell3.innerHTML = '<img src="img/green.jpg" alt="fail" width="40px;">';
  }else {
    cell3.innerHTML = '<img src="img/red.jpg" alt="fail" width="40px;">';
  }

}

function loadJSON(callback) {

   var xobj = new XMLHttpRequest();
       xobj.overrideMimeType("application/json");
   xobj.open('GET', 'logs/status.json', true);
   xobj.onreadystatechange = function () {
         if (xobj.readyState == 4 && xobj.status == "200") {
           // Required use of an anonymous callback as .open will NOT return a value but simply returns undefined in asynchronous mode
           callback(xobj.responseText);
         }
   };
   xobj.send(null);
}

function tailf() {

  var lastByte = 0;
  var url = "logs/mainlog.log";
  var ajax = new XMLHttpRequest();
  ajax.open("POST",url,true);

  if (lastByte == 0) {
    // First request - get everything
  } else {
    //
    // All subsequent requests - add the Range header
    //
    ajax.setRequestHeader("Range", "bytes=" + parseInt(lastByte) + "-");
  }

  ajax.onreadystatechange = function() {
    if(ajax.readyState == 4) {

      if (ajax.status == 200) {
        // only the first request
        lastByte = parseInt(ajax.getResponseHeader("Content-length"));
        document.getElementById("thePlace").innerHTML = ajax.responseText;
        document.getElementById("theEnd").scrollIntoView()

      } else if (ajax.status == 206) {
        lastByte += parseInt(ajax.getResponseHeader("Content-length"));
        document.getElementById("thePlace").innerHTML += ajax.responseText;
        document.getElementById("theEnd").scrollIntoView()

      } else if (ajax.status == 416) {
        // no new data, so do nothing

      } else {
        //  Some error occurred - just display the status code and response
        alert("Ajax status: " + ajax.status + "\n" + ajax.getAllResponseHeaders());
      }
    }// ready state 4
  }//orsc function def

  ajax.send(null);

}// function tailf

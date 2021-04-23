var counter = 0;
var timeout = 1000;
setInterval(
    function (){
        counter++;
        document.title = "("+counter+")Notifications";
}, timeout);
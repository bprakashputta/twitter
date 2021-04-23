<html>
<head>

</head>
<body>
    USER is trying to login at <span> </span><div id="time"></div>
    <br><br><br><br><br><br>
    <marquee>Made with Love by Bhanu</marquee>

    <script type="text/javascript">
        function updateTime(){
            document.getElementById("time").innerText = new Date().toString();
        }
        setInterval(updateTime,0);
    </script>
</body>
</html>
<html>
<head>
    <link rel="shortcut icon" type="image/png" href="/static/images/favicon.ico">
    <script type="text/javascript" src="/static/js/hello.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/welcome.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous">
    </script>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <div class="head">
                <img src="/static/images/favicon.ico">
                <h3>Login</h3>
<%--                <p>It's free and only takes a minute</p>--%>
            </div>
            <p id="signup-error" style="color: #ff0000; display: none"></p>
            <form class="signup-form">
                <label>Email</label>
                <input id="signup-email" type="email" name="email" placeholder="abc@gmail.com">
                <label>Password</label>
                <input id="signup-password" type="password" name="password" placeholder="********">
                <button type="button" id="btn-signup">Login</button>
            </form>

            <div class="footer">
                <p>By clicking the Signup Button you agree to our
                    <a href="#">Term & conditions </a>and <a href="#">Privacy policy</a></p>
            </div>
        </div>
        <div class="signup"> Already have an account ? <a href="#">Login Here</a></div>
    </div>
</body>
    <%-- JavaScript for interaction with form --%>
    <script>
        function isFormValid(){
            var name = $("#signup-name").val();
            var email = $("#signup-email").val();
            var password = $("#signup-password").val();

            var error = "";
            if(!email){
                error += "Email is Empty\n";
            }
            if(!password){
                error += "Password is Empty\n";
            }else if(password.length < 5){
                error += "Password length is less than 5\n"
            }

            //display the error.
            $("#signup-error").html(error);
            //check if error is null or not
            if(error.length > 0){
                // $("#signup-error").show();
                return false;
            }

            // $("#signup-error").hide();
            return true;
        }

        $("#btn-signup").click(function () {
            if(isFormValid()){
                $("#signup-error").hide();
                // alert("Successfully signed up")
                var email = $("#signup-email").val();
                var password = $("#signup-password").val();

                var user = {
                    "email" : email,
                    "password" : password
                };

                $.ajax({
                    type: "POST",
                    url: "/login/welcome",
                    data: JSON.stringify(user),
                    success: function(response){
                        if(!!response){
                            // alert(response.message);
                            if(response.isLoggedIn === true){
                                location.href = "/welcome";
                            }else{
                                $("#signup-password").val("");
                                $("#signup-error").html(response.message);
                                $("#signup-error").show();
                            }
                        }
                    },
                    contentType: 'application/json'
                });

            }else {
                $("#signup-error").show();
                // alert("Form is not valid");
            }
        });
    </script>
</html>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Signup</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.4.2/css/fontawesome.min.css" integrity="sha384-BY+fdrpOd3gfeRvTSMT+VUZmA728cfF9Z2G42xpaRkUGu2i3DyzpTURDo5A6CaLK" crossorigin="anonymous">
  <!-- Vendor CSS Files -->
   <link href="assets/css/css_inder.css" rel="stylesheet" type="text/css"/>
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
  <script src="assets/js/Lgnscrpt.js" type="text/javascript">
       
      
  </script>
  <!-- Template Main CSS File -->
 

        <%@include  file="Header_login.jsp" %>  
  
</head>

<body>

        <!-- ======= Hero Section ======= -->
        <section id="hero2" class="d-flex align-items-right">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-6 col-xs-12">
                        <div class="left-logo">
                            <img src="assets/img/logo.png">
                            <p class="Rcs">Login and access VNS/BSNL APIs to send and receive RCS messages to/from India RCS Users.</p>
                        </div>

                    </div>
                    <div id="signup" class="col-sm-6 col-md-6 col-xs-12">
                        <div class="main-form">
                            <h1 class="sign-in">Sign UP</h1>
                            <form>
                                <div class="form-group">
                                    <label for="exampleInputEmail1"><img class="email-id" src="assets/img/emailid.png">Email ID</label>
                                    <input type="email" class="form-control" id="Email" aria-describedby="emailHelp" placeholder="Enter email">
                                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                </div>
                                <div >

                                    <div class="form-group">
                                        <label for="exampleInputPassword1"><img class="email-id" src="assets/img/password.png">Password*</label>
                                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"  >
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1"><img class="email-id" src="assets/img/password.png">Confirm Password*</label>
                                        <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Confirm Password"  >
                                    </div>
                                    <!--                                <div class="form-group form-check">
                                                                        <input type="checkbox" class="form-check-input" id="exampleCheck1">
                                                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                                                    </div>-->

                                </div>
                                 
                                <div  id="spcae_otp" style="display :none;" >
                                    <h4 id="Info_tag" style="display :none;">OTP Sent</h4>
                                    <div >
                                        <input class="form-control" id="OTP_VAL"  />
                                         
                                        <br>
                                         
                                           
                                        
                                    </div>
                                </div>
                            </form >
                              <p class="resend text-muted mb-0">
					  Already Have Account? <a href="Login.jsp">Click here</a>
					</p>
                           
                            <button type="submit" id="OTP" class="login-btn" onclick="GETOTP()" >Get OTP</button>
                            <button type="submit"  id="Sign" class="login-btn"  onclick="SIGNUP()"  style="display:none">SIGN UP</button>
                            <button type="submit" id="wait" class="login-btn"   onclick="Wait()" style="display:none">Please Wait</button>

                        </div>
                    </div>
                </div>
            </div>
        </section><!-- End Hero -->





        <%@include  file="Footer_footer.jsp" %>  


      
        <!-- ======= Footer ======= -->
 

    </body>

</html>
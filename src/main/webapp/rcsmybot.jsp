<!DOCTYPE html>
<%@include file="session.jsp" %>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>MyBot</title>
        <!-- Include necessary CSS -->
        <!--        <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/google-libphonenumber/7.2.7/libphonenumber.js"></script>
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
                 Include necessary JS libraries 
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.4.2/css/fontawesome.min.css" integrity="sha384-BY+fdrpOd3gfeRvTSMT+VUZmA728cfF9Z2G42xpaRkUGu2i3DyzpTURDo5A6CaLK" crossorigin="anonymous">-->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
        <!-- Google Fonts -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
        <!-- Vendor CSS Files -->
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <!-- Template Main CSS File -->
        <link href="assets/css/style24.css" rel="stylesheet">

        <link href="assets/css/rbm_css.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/rbm_script.js" type="text/javascript"></script>
    </head>
    <!--    <header id="header" class="fixed-top">
            <div class="container d-flex align-items-center justify-content-between">
                <h1 class="logo">
                    <a href="index.html"><img src="assets/img/logo.png"></a>
                </h1>
                 Uncomment below if you prefer to use an image logo 
                 <a href="index.html" class="logo"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>
                <nav id="navbar" class="navbar">
                    <ul>
                        <li>
                            <a class="nav-link scrollto active" href="#hero">MY BOTS</a>
                        </li>
                        <li>
                            <a class="nav-link scrollto" href="#about">MY BRANDS</a>
                        </li>
                        <li>
                            <a class="nav-link scrollto" href="#services">MY CAMPAIGNS</a>
                        </li>
                        <li>
                            <a class="nav-link scrollto o" href="#portfolio">API</a>
                        </li>
                        <li>
                            <a class="nav-link scrollto" href="#team">MY BILLING</a>
                        </li>
    
                    </ul>
                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav>
                 .navbar 
            </div>
        </header
    <%@include file="header.jsp" %>
  
      <body>
    <!-- action="Database2.jsp"  FormSubmissionServlet1 -->


    <form id="combinedForm" action="FormSubmissionServlet1" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">


        <section id="hero" class="d-flex align-items-center">
            <div class="container position-relative" data-aos="fade-up" data-aos-delay="100">
                <div class="row justify-content-center">
                    <!--                    <form id="combinedForm" action="FormSubmissionServlet1" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">-->
                    <!-- Bot Message Type Section -->
                    <div class="col-md-12 col-sm-12">
                        <h3 for="botMessageType">Bot Message Type*</h3>
                        <label for="OTP">OTP</label>
                        <input type="radio" id="OTP" name="botMessageType" value="OTP">

                        <label for="Transactional">Transactional</label>
                        <input type="radio" id="Transactional" name="botMessageType" value="Transactional">

                        <label for="Promotional">Promotional</label>
                        <input type="radio" id="Promotional" name="botMessageType" value="Promotional">
                    </div>
                    <br>
                    <div class="col-md-12 col-sm-12 full-width">
                        <!-- Bot Name Section -->
                        <h3 class="title1" for="brandName">Bot Name*</h3>
                        <p>Enter the name of the chatbot that the user will see at the top of the message thread (40 chars. max)</p>
                        <input type="text" id="botname" name="botname" maxlength="40" oninput="updateCharCount(this.value)" required>
                        <div id="charCount">40 characters left</div>
                    </div>

                    <div class="col-md-12 col-sm-12 full-width">
                        <!-- Brand Name Section -->
                        <h3 class="title" for="brandName">Brand Name*</h3>
                        <p>Enter the brand name with which your chatbot will be associated.</p>
                        <input type="text" id="brandName" name="brandName" maxlength="100" oninput="updateBrandCharCount(this.value)" required>
                        <div id="charCountbrand">100 characters left</div>
                    </div>

                    <div class="col-md-12 col-sm-12 full-width">
                        <!-- Short Description Section -->
                        <h3 class="title" for="shortdescription">Short Description*</h3>
                        <input type="text" id="shortdescription" name="shortdescription" maxlength="100" oninput="updateshortdescription(this.value)" required>
                        <div id="charCountshortdescription">100 characters left</div>
                    </div>
                    <br>

                    <div class="row">
                        <div class="col-md-12 col-sm-12 full-w">
                            <h3 class="title1" for="favcolor">color*</h3>

                            <p>Specify a color for your agent with a minimum 4.5 : 1 contrast ratio relative to white.<a href="https://webaim.org/resources/contrastchecker/">Learn More</a></p>
                            <input type="color" id="favcolor" name="favcolor" value="#00000">
                            <input type="text" id="favcolors" name="favcolors" oninput="updateColor()">
                        </div>

                        <!-- <div class="col-md-10 col-sm-10">
            
             </div> -->
                    </div>
                </div>

                <div style="height:24px;"></div>


                <div class="row" id="phoneNumbersContainer">
                    <div class="col-md-1 mt-10">
                        <div class="form-group">
                            <select class="form-select" aria-label="Default select example">
                                <option value="AF">India</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-4 ">
                        <label for="exampleInputPassword1">Primary phone number*</label>
                        <input type="number" class="form-control" id="exampleInputphone" name="primaryPhoneNumber" aria-describedby="emailHelp" placeholder="+91" required>
                    </div>
                    <div class="col-md-4">
                        <label for="exampleInputPassword1">Label for Primary phone number*</label>
                        <input type="number" class="form-control" id="exampleInputphone" name="primaryPhoneNumber" aria-describedby="emailHelp"  required>
                        <!--                            <p class="chter">25 Characters left</p>-->
                    </div>
                </div>

                <a type="button" id="addPhoneNumberBt" onclick="addPhoneNumber()">+Add Phone Number</a>

                <div style="height:8px;"></div>

                <div class="row" id="emailAddressesContainer">

                    <div class="col-md-4">
                        <label for="exampleInputPassword1">Primary Email Id</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" name="emailAddress" aria-describedby="emailHelp" placeholder="@">
                    </div>
                    <div class="col-md-4">
                        <label for="exampleInputPassword1">Label for primary email id</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" name="emailAddress" aria-describedby="emailHelp" placeholder="">
                        <!--                            <p class="chter">25 Characters left</p>-->
                    </div>
                    <!--                                        <span class="addphone">-->

                    <!--                                        </span>-->
                </div>
                <a type="button" id="addEmailBtn" onclick="addEmail()">+ Add Email Address</a>



                <div class="row" id="websiteContainer">
                    <div class="col-md-4">
                        <label for="exampleInputPassword1">Primary website</label>
                        <input type="url" class="form-control" id="exampleInputEmail1" name="websiteUrl" aria-describedby="emailHelp" placeholder="website">
                    </div>
                    <div class="col-md-4">
                        <label for="exampleInputPassword1">Label for primary website</label>
                        <input type="url" class="form-control" id="exampleInputEmail1"  name="websiteUrl" aria-describedby="emailHelp" placeholder="">
                        <!--                            <p class="chter">25 Characters left</p>-->
                    </div>



                </div>

                <a type="button" id="addWebsiteBtn" onclick="addWebsite()">+ Add Website URL</a>

                <div class="form-group mb-10">

                    <h3 class="title" for="image" required>Bot Logo*</h3>
                    <p>Provide a logo for your bot that will be displayed in connection with the bot's name.</p>
                    <img src="C:/Users/Ram Ishwer Kumar/Documents/ImageFolder/sim.jpg" alt="Image" id="botImage" name="imagess" accept="image/*" ><br><br>

                    <button class="upload-b" type="button" onclick="openModal()">Upload</button>

                    <!-- The modal for image selection -->
                    <div id="imageModal" class="modal">
                        <label for="modalImage">Select an image:</label>
                        <label>Upload Image</label>
                        <input type="file" id="modalImage" name="image"><br><br>
                        <label>Upload from URL</label>
                        <input type="text" id="websiteInput" name="websiteInput" accept="website/*" >
                        <!--//onchange="toggleRequired()" -->
                        <br><br>
                        <button type="button" onclick="upload()">Select</button>
                        <button type="button" onclick="closeModal()">Close</button>
                    </div>

                    <!-- Banner Image Section -->
                    <h3 class="title" for="bannerimage">Banner Image*</h3>
                    <p>Provide a brand image for your bot that will be displayed in the bot's 'Info & options' screen.<br>
                        Note: Your logo will be overlaid on the Banner Image (bottom centre) so be careful with your design.</p>
                    <img id="bannerImage" src="C:/Users/Ram Ishwer Kumar/Documents/ImageFolder/sim.jpg" alt="Image"><br><br>
                    <button class="upload-b" type="button" onclick="openModal1()">Upload</button>

                    <!--             The modal for banner image selection -->
                    <div id="bannerimageModal" class="modal" required>
                        <label for="bannermodalImage">Select a banner image:</label>
                        <label>Upload Image</label>
                        <input type="file" id="bannermodalImage" name="bannerimage"  ><br><br>
                        <label>Upload from URL</label>
                        <input type="text" id="bannerwebsiteInput" name="bannerwebsiteInput" accept="website/*">

                        <br><br>
                        <button type="button" onclick="uploadBanner()">Select</button>
                        <button type="button" onclick="closeModal1()">Close</button>
                    </div>


                    <!-- Additional Fields Section -->
                    <div class="col-md-12 full-width">
                        <h3 class="title" for="urlterm">Terms of Use URL*</h3>
                        <p>Enter the URL of the website</p>
                        <input type="url" id="urlterm" name="urlterm" required>

                        <h3 class="title" for="urlterm">Privacy Policy URL*</h3>
                        <p>Enter the URL of the website</p>
                        <input type="url" id="urlprivacy" name="urlprivacy" required><br>

                        <!-- Development Platform Section -->
                        <h3 class="title">Select the development platform that you will use to create your bot<br> (GSMA API or Google styled API)*</h3>
                        <select name="developmentPlatform" id="developmentPlatform" required>
                            <option value="" disabled selected hidden>Development Platform</option>
                            <option value="googleapi">Google API</option>
                            <option value="gsmaapi">GSMA API</option>
                        </select>

                        <!-- Chatbot Webhook Section -->
                        <h3 class="title" for="chatbotwebhook">Chatbot Webhook</h3>
                        <p>Enter the webhook that your bot will receive messages from the VI agent.<br>
                            NOTE: The webhook needs to be active and be able to respond with a 200 OK to POST requests.</p>
                        <input type="text" id="chatbotwebhook" name="chatbotwebhook" required><br>

                        <h3 class="title" for="languagessupport">Languages Supported*</h3>
                        <p>Please specify the languages supported by the bot</p>
                        <input type="text" id="languagessupport" name="languagessupport" required><br>
                    </div>
                    <label>
                        <input type="checkbox" name="agree" required> I agree to lunch the bot on all indian carriers
                    </label>
                </div> 
                <input  class="submit-b" type="button" onclick="goBack()" value="Back">
                <input class="submit-b" type="submit" value="Submit" >
                </form>

            </div>



            </div>
        </section>

        <div id="customPopup">
            <p>Form submitted successfully!</p>
            <!-- Going button -->
            <button id="goingButton">Go To Dashboard</button>
        </div>

        <!-- End Hero -->
        <!--            <footer id="footer">
                        <div class="container d-md-flex py-4">
                            <div class="me-md-auto text-center text-md-start">
                                <div class="copyright"> &copy; Copyright <strong>
                                        <span>OnePage</span>
                                    </strong>. All Rights Reserved </div>
                            </div>
                        </div>
                    </footer>-->
        <%@include file="footer.jsp" %>

        <!-- End Footer -->
        <!-- <div id="preloader"></div> -->
        <a href="#" class="back-to-top d-flex align-items-center justify-content-center">
            <i class="bi bi-arrow-up-short"></i>
        </a>
        <!-- Vendor JS Files -->
        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>



    </body>
    <script>
                        function showCustomPopup() {
                            try {
                                var popup = document.getElementById('customPopup');
                                popup.style.display = 'block';

                                // Add event listener to the "Going" button
                                var goingButton = document.getElementById('goingButton');
                                goingButton.addEventListener('click', function () {
                                    // Redirect to new.jsp when the button is clicked
                                    window.location.href = 'new.jsp';
                                });
                                // Example log message
                                logMessage("Custom popup displayed successfully.");
                            } catch (error) {
                                // Example log error
                                logError("Error while displaying custom popup: " + error.message);
                            }
                        }

                        document.addEventListener("DOMContentLoaded", function () {
                            document.getElementById("combinedForm").addEventListener("submit", function (event) {
                                event.preventDefault();

                                var formData = new FormData(this);

                                var xhr = new XMLHttpRequest();
                                xhr.open("POST", "FormSubmissionServlet1", true);
                                xhr.onreadystatechange = function () {
                                    if (xhr.readyState === 4) {
                                        if (xhr.status === 200) {

                                            showCustomPopup();
// Example log message
                                            logMessage("Form submitted successfully.");
                                            // alert("Error submitting form. Please try again.");
                                            //openPopup();
                                        } else {
                                            // Handle errors
                                            //    alert("Error submitting form. Please try again.");
                                            logError("Error submitting form. Please try again.");

                                        }
                                    }
                                };

                                // Send the form data
                                xhr.send(formData);
                            });
                        });



    </script>


    <style>
        /* Your existing styles here */
        #customPopup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            text-align: center;
        }

        #goingButton {
            padding: 10px 20px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            cursor: pointer;
        }
    </style>

</html>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="db.dbcon"%>
<%@include file="session.jsp" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>RCS BOT</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.4.2/css/fontawesome.min.css" integrity="sha384-BY+fdrpOd3gfeRvTSMT+VUZmA728cfF9Z2G42xpaRkUGu2i3DyzpTURDo5A6CaLK" crossorigin="anonymous">
        <!-- Vendor CSS Files -->
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style24.css" rel="stylesheet">


    </head>

    <body>

        <!-- ======= Header ======= -->
        <header id="header" class="fixed-top">
            <div class="container d-flex align-items-center justify-content-between">
                <h1 class="logo"><a href="index.html"><img src="assets/img/logo.png"></a></h1>
                <!-- Uncomment below if you prefer to use an image logo -->
                <!-- <a href="index.html" class="logo"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

                <nav id="navbar" class="navbar">
                    <ul>
                        <li><a class="nav-link scrollto active" href="#hero">MY BOTS</a></li>
                        <li><a class="nav-link scrollto" href="#about">MY BRANDS</a></li>
                        <li><a class="nav-link scrollto" href="#services">MY CAMPAIGNS</a></li>
                        <li><a class="nav-link scrollto o" href="#portfolio">API</a></li>
                        <li><a class="nav-link scrollto" href="#team">MY BILLING</a></li>
                    </ul>
                    <i class="bi bi-list mobile-nav-toggle"></i>

                </nav><!-- .navbar -->
                <div class="dropdown">
                    <button class="btn btn-secondary1 dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="assets/img/profile_img.png">
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </div>
            </div>
        </header><!-- End Header -->

        <!-- ======= Hero Section ======= -->
        <section id="hero1" class="d-flex align-items-center">
            <div class="container" data-aos="fade-up" data-aos-delay="100">
                <div class="row justify-content-left">

                    <div class="col-md-4 col-sm-4 col-xs-12">
                        <button type="button" class="btn btn-secondary" onclick="openJspPage()">Create New RCS Bot</button><br>
                        <!-- Search form -->
                        <label class="s_bot" for="search">Search Bots</label>
                        <div class="active-purple-3 active-purple-4 mb-4">
                            <input id="searchInput" class="form-control" type="text" placeholder="Search" aria-label="Search">
                        </div>
                    </div>
                    <div class="col-md-12 col-sm-12 colxs-12 table-responsive">
                        <div class="main-table">
                            <table class="table">
                                <thead class="table-top">
                                    <tr class="bg-color">
                                        <th scope="col">My RCS Bots</th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>

                                    </tr>
                                </thead>
                                <tbody class="table-data">
                                    <tr>
                                        <th scope="row">Bot Name</th>
                                        <td><b>Bot Type</b></td>
                                        <td><b>Brand</b></td>
                                        <td><b>Status</b></td>
                                        <td><b>Action</b></td>

                                    </tr>
                                    <%
                                        try {
                                            dbcon db = new dbcon();
                                            db.getCon("VNS_RCS");

                                            String Query = "Select bot_name,bot_message_types,brand_name from rbm_table order by id DESC LIMIT 5";
                                            //select * from rbm_table order by id DESC LIMIT 5;
                                            //Select bot_name,bot_message_types,brand_name from rbm_tableorder by id DESC LIMIT 5
                                            Statement st = db.getStmt();
                                            ResultSet rs = st.executeQuery(Query);

                                            while (rs.next()) {
                                    %>
                                    <tr class="bot-row">
                                        <td style="padding:10px;"><%=rs.getString("bot_name")%></td>
                                        <td style="padding:10px;"><%=rs.getString("bot_message_types")%></td>
                                        <td style="padding:10px;"><%=rs.getString("brand_name")%></td>

                                    </tr>
                                    <%
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </section>


        <!-- ======= Footer ======= -->
        <footer id="footer">



            <div class="container d-md-flex py-4">

                <div class="me-md-auto text-center text-md-start">
                    <div class="copyright">
                        &copy; Copyright <strong><span>OnePage</span></strong>. All Rights Reserved
                    </div>

                </div>
                <div class="social-links text-center text-md-right pt-3 pt-md-0">
                    <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
                    <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
                    <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
                    <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
                    <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
                </div>
            </div>
        </footer><!-- End Footer -->

        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>

    </body>


    <script>
                            function openJspPage() {
                                // Replace 'your-page.jsp' with the actual path to your JSP page
                                window.location.href = 'rcsmybot.jsp';
                            }
                            
                            
                            document.addEventListener('DOMContentLoaded', function () {
                                const searchInput = document.getElementById('searchInput');

                                searchInput.addEventListener('input', function () {
                                    const searchTerm = searchInput.value.trim().toLowerCase();
                                    const botRows = document.querySelectorAll('.bot-row');

                                    botRows.forEach(function (row) {
                                        const botName = row.children[0].innerText.trim().toLowerCase();
                                        const botType = row.children[1].innerText.trim().toLowerCase();
                                        const brand = row.children[2].innerText.trim().toLowerCase();

                                        // Check if any of the columns contain the search term
                                        if (
                                                botName.includes(searchTerm) ||
                                                botType.includes(searchTerm) ||
                                                brand.includes(searchTerm)
                                                ) {
                                            row.style.display = '';
                                        } else {
                                            row.style.display = 'none';
                                        }
                                    });
                                });
                            });


    </script>



</html>
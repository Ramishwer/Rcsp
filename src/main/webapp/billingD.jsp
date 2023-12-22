<!DOCTYPE html>
<%@include file="session.jsp" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>RCS BOT</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets1/img/favicon.png" rel="icon">
        <link href="assets1/img/apple-touch-icon.png" rel="apple-touch-icon">
        <script src="billingScript/billingJS.js" type="text/javascript"></script>
        <script src="trafficScript/trafficDummyjs.js" type="text/javascript"></script>
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.4.2/css/fontawesome.min.css" integrity="sha384-BY+fdrpOd3gfeRvTSMT+VUZmA728cfF9Z2G42xpaRkUGu2i3DyzpTURDo5A6CaLK" crossorigin="anonymous">
        <!-- Vendor CSS Files -->
        <link href="assets1/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets1/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets1/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets1/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets1/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets1/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets1/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.1/xlsx.full.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.5/xlsx.full.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.5.0-beta4/html2canvas.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/pdfmake.min.js"></script>
        <!-- Template Main CSS File -->
        <link href="assets1/css/style24.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.1/xlsx.full.min.js"></script>


        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
  
        <!-- Include flatpickr JS -->
        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    </head>

    <body>
        <%@include file="header.jsp" %>
<!--         ======= Header ======= 
        <header id="header" class="fixed-top">
            <div class="container d-flex align-items-center justify-content-between">
                <h1 class="logo"><a href="index.html"><img src="assets1/img/logo.png"></a></h1>
                <nav id="navbar" class="navbar">
                    <ul>
                        <li><a class="nav-link scrollto active" href="#hero">MY BOTS</a></li>
                        <li><a class="nav-link scrollto" href="#about">MY BRANDS</a></li>
                        <li><a class="nav-link scrollto" href="#services">MY CAMPAIGNS</a></li>
                        <li><a class="nav-link scrollto o" href="#portfolio">API</a></li>
                        <li><a class="nav-link scrollto" href="#team">MY BILLING</a></li>
                    </ul>
                    <i class="bi bi-list mobile-nav-toggle"></i>

                </nav> .navbar 
                <div class="dropdown">
                    <button class="btn btn-secondary1 dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="assets1/img/profile_img.png">
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </div>
            </div>
        </header> End Header -->

        <!-- ======= Hero Section ======= -->
        <section id="hero1" class="d-flex align-items-center">
            <div class="container" data-aos="fade-up" data-aos-delay="100">
                <br>
                <br>
                <!-- Nav tabs -->

                <div class="tab-top">
                    <ul class="nav nav-pills tab_Multiple" role="tablist">
                        <li class="nav-item">
                            <a href="#" class="nav-link active" data-toggle="pill" data-target="#l1"  data-one="#l2" data-two="#l3" >Billing Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link" data-toggle="pill" data-target="#d1"  data-one="#d2" data-two="#d3" >Traffic Dashboard</a>
                        </li>
                    </ul>
                </div>
                <!-- Tab One-->
                <div class="tab-content">
                    <div id="l1" class="container tab-pane active"><br>
                        <div class="row">
                            <div class="col-sm-4 col-md-4 mt-10">
                                <label for="dateRange" class="form-label">Select Date Range:</label>
                                <select class="form-control" id="dateRange" onchange="updateDateRange()">
                                    <option value="None">None</option>
                                    <option value="today">Today</option>
                                    <option value="yesterday">Yesterday</option>
                                    <option value="currentWeek">Current Week</option>
                                    <option value="Last 7 days">Last 7 days</option>
                                    <option value="LastWeek">Last Week</option>
                                    <option value="Last30Days">Last 30 Days</option>
                                    <option value="ThisMonth">Current Month</option>
                                    <option value="LastMonth">Last Month</option>
                                    <option value="custom">Custom Range</option>
                                </select>

                                <div id="todayRangeInputs1" style="display: none;">
                                    <input type="date" id="startDate" name="startDate"  >
                                    <input type="date" id="endDate" name="endDate"  >
                               </div> 
                                <br>
                                <div id="customRangeInput1" style="display: none;">
                                    <label for="customStartDate1">Custom Start Date:</label>
                                    <input type="date" id="customStartDate1" name="customStartDate1">
                                    <br><br>
                                    <label for="customEndDate1">Custom End Date:</label>
                                    <input type="date" id="customEndDate1" name="customEndDate1">
                                    &nbsp;&nbsp;
                                    <button onclick="applyCustomRange1()">Apply</button>
                                    <button id="closeCustomRangebtn" onclick="closeCustomRange()">Close</button>
                                </div>

                            </div>
                            <div class="col-sm-4 col-md-4 mt-10">

                                <label for="disabledSelect" class="form-label">Brands</label>
                                <select class="form-control" name="Option_val" id="BrandList" onclick="GetBot()" >

                                    <option>All</option>
                                </select>
                            </div>
                            <div class="col-sm-4 col-md-4 mt-10">
                                <label for="disabledSelect" class="form-label">Bots</label>
                                <select class="form-control" name="Option_val"  id="BotList"  >
                                    <option>All</option>
                                </select>
                            </div>

                            <div class="col-sm-4 col-md-4 mt-10">
                                <label for="disabledSelect" class="form-label">List By</label>
                                <select id="listby" class="form-select">
                                    <option value="ALL">ALL</option>
                                    <option value="DateRange">Date</option>
                                    <option value="Brands">Brands</option>
                                    <option value="Bots">Bot</option>
                                </select>
                            </div>
                            <div class="col-sm-4 col-md-4 mt-10">
                                <button type="submit" class="btn btn-primary1" onclick="SearchBill()">Search</button> 
                            </div>
                        </div>

                        <div class="row">
                       
                            <hr class="top-space">
                            <div class="col-md-6">
                                <h1 class="summary">Summary</h1>
                            </div>
                            <div class="col-md-6">
                                <span class="dwonload-data"><a href="#">Download Report</a>&nbsp;&nbsp;<img src="assets1/img/icon_excel.svg" alt="Pdf" onclick="downloadPDFReport()">&nbsp;&nbsp;&nbsp;
                                    <img src="assets1/img/icon_pdf.svg"  alt="Excel" onclick="downloadReport()"></span>

                            </div>

                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <div class="box_sum">
                                    <h2 class="basic-m">Basic Message </h2><span class="zero" ><div id="basic_msg_bill">0</div>
                                    </span>
                                    <div class="box-1">
                                        <span class="d">Domestic</span><br><b class="zero2"><div id="Domesticvalue">0</div></b>
                                    </div>
                                    <div class="box-2">
                                        <span class="d">International</span><br><b class="zero2"><div id="Intervalue">0</div></b>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <div class="box_sum">
                                    <h2 class="basic-m">A2P Single Message</h2><span class="zero"><div id="A2P_msg_bill">0</div></span>
                                    <div class="box-1">
                                        <span class="d">Domestic</span><br><b class="zero2"><div id="DomesticA2p">0</div></b>
                                    </div>
                                    <div class="box-2">
                                        <span class="d">International</span><br><b class="zero2"><div id="InternationalA2p">0</div></b>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <div class="box_sum">
                                    <h2 class="basic-m">A2P Conversation </h2><span class="zero"><div id="A2P_Conv_bill">0</div></span>
                                    <div class="box-1">
                                        <span class="d">Domestic</span><br><b class="zero2"><div id="DomesticA2PConversation">0</div></b>
                                    </div>
                                    <div class="box-2">
                                        <span class="d">International</span><br><b class="zero2"><div id="InternationalA2PConversation">0</div></b>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <div class="box_sum">
                                    <h2 class="basic-m">P2A Conversation </h2><span class="zero"><div id="P2A_Conv_bill">0</div></span>
                                    <div class="box-1">
                                        <span class="d">Domestic</span><br><b class="zero2"><div id="DomesticP2AConversation">0</div></b>
                                    </div>
                                    <div class="box-2">
                                        <span class="d">International</span><br><b class="zero2"><div id="InternationalP2AConversation">0</div></b>
                                    </div>
                                </div>
                            </div>
                        </div>  

                        <!---------Table-------->
                        <div class="row">
                            <div class="col-md-12">
                            </div>
                            <div id="no-more-tables" >
                                <div id="no-records-message">
                                    <center>No Billing Found</center>
                                </div>                              
                                 <table id="no-more-tablesssall" class="col-md-12 table-bordered table-striped table-condensed cf" style="display:none;">
                                    <thead class="cf">
                                        <tr>
                                            <th>Date</th>
                                            <th>Brands</th>
                                            <th>Bots</th>
                                            <th>Transaction Type</th>
                                            <th class="numeric">Basic Message</th>
                                            <th class="numeric">A2P Single Message</th>
                                            <th class="numeric">A2P Conversation</th>
                                            <th class="numeric">P2A Conversation</th>
                                        </tr>
                                    </thead>
                                    <tbody>


                                    </tbody>
                                </table>

                                <table id="no-more-tablesss" class="col-md-12 table-bordered table-striped table-condensed cf">
                                    <thead class="cf">
                                        <tr>
                                            <th>Date</th>
                                            <th>Transaction Type</th>
                                            <th class="numeric">Basic Message</th>
                                            <th class="numeric">A2P Single Message</th>
                                            <th class="numeric">A2P Conversation</th>
                                            <th class="numeric">P2A Conversation</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>

                                <table id="no-more-tablesss1" class="col-md-12 table-bordered table-striped table-condensed cf">
                                    <thead class="cf">
                                        <tr>
                                            <th>Brand Name </th>
                                            <th>Transaction Type</th>
                                            <th class="numeric">Basic Message</th>
                                            <th class="numeric">A2P Single Message</th>
                                            <th class="numeric">A2P Conversation</th>
                                            <th class="numeric">P2A Conversation</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>

                                <table id="no-more-tablesss2" class="col-md-12 table-bordered table-striped table-condensed cf">
                                    <thead class="cf">
                                        <tr>
                                            <th>Bot Name </th>
                                            <th>Transaction Type</th>
                                            <th class="numeric">Basic Message</th>
                                            <th class="numeric">A2P Single Message</th>
                                            <th class="numeric">A2P Conversation</th>
                                            <th class="numeric">P2A Conversation</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- Tab One-End--->
                    <!---------Tab-Scend---------->
                    <div id="d1" class="container tab-pane fade"><br>
                        <div class="row">
                            <div class="col-sm-4 col-md-4 mt-10">
                                <label for="dateRange" class="form-label">Select Date Range:</label>
                                <select class="form-control" id="dateRange1" onchange="updateDateRange1()">
                                    <option value="None">None</option>
                                    <option value="today">Today</option>
                                    <option value="yesterday">Yesterday</option>
                                    <option value="Last 7 days">Last 7 days</option>
                                    <option value="currentWeek">Current Week</option>
                                    <option value=LastWeek">Last Week</option>
                                    <option value="Last30Days">Last 30 Days</option>
                                    <option value="ThisMonth">Current Month</option>
                                    <option value="LastMonth">Last Month</option>
                                    <option value="custom">Custom Range</option>
                                </select>

                                <div id="todayRangeInputs" style="display: none;">
                                    <input type="date" id="startDate1" name="startDate1"  >
                                    <input type="date" id="endDate1" name="endDate1"  >
                                </div> 
                                <br>
                                <div id="customRangeInputs" style="display: none;">
                                    <label for="customStartDate">Custom Start Date:</label>
                                    <input type="date" id="customStartDate" name="customStartDate">
                                    <br><br>
                                    <label for="customEndDate">Custom End Date:</label>
                                    <input type="date" id="customEndDate" name="customEndDate">
                                    &nbsp;&nbsp;
                                    <button onclick="applyCustomRange()">Apply</button>
                                    <button id="closeCustomRangebtn1" onclick="closeCustomRange1()">Close</button>
                                </div>
                            </div>
                            <div class="col-sm-4 col-md-4 mt-10">                             
                                <label for="disabledSelect" class="form-label">Brands</label>
                                <select class="form-control" name="Option_val" id="BrandList1" onclick="GetBot1()" >
                                    <option>All</option>
                                </select>
                            </div>
                            <div class="col-sm-4 col-md-4 mt-10">
                                <label for="disabledSelect" class="form-label">Bots</label>
                                <select class="form-control" name="Option_val"  id="BotList1" onclick="Gettemplate()">
                                    <option>All</option>
                                </select>
                            </div>
                            <div class="col-sm-4 col-md-4 mt-10">
                                <label for="disabledSelect" class="form-label">Templates</label>
                                <select class="form-select" name="Option_val" id="templatesid">
                                    <option>All</option>
                                </select>
                            </div>
                            <div class="col-sm-4 col-md-4 mt-10">
                                <label for="disabledSelect" class="form-label">List By</label>
                                <select id="listby1" class="form-select">
                                    <option value="ALL">ALL</option>
                                    <option value="DateRanget">Date</option>
                                    <option value="Brandst">Brands</option>
                                    <option value="Botst">Bot</option>
                                    <option value="Templates">Templates</option>
                                </select>
                            </div>
                            <div class="col-sm-4 col-md-4 mt-10">
                                <button type="submit" class="btn btn-primary1" onclick="Searchtraffic()" >Search</button> 
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-12">
                       
                                <div class="col-md-6">
                                    <span class="dwonload-data1"><a href="#">Download Report</a>&nbsp;&nbsp;
                                        <img src="assets1/img/icon_pdf.svg" alt="Excel" onclick="exportAllToExcel()"></span>
                                </div>

                            </div>
                            <div id="no-more-tables">
                                <div id="traffictableD">
                                    <center>
                                        traffic no records
                                    </center>
                                    <div>

                                    </div>


                                </div>
                            </div>
                        </div>
                        <!---------Tab-Scend-----End----->

                    </div>
                    </section><!-- End Hero -->
<%@include file="footer.jsp" %>
<!--                     ======= Footer ======= 
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
                    </footer> End Footer -->

                    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

                    <!-- Vendor JS Files -->
                    <script src="assets1/vendor/purecounter/purecounter_vanilla.js"></script>
                    <script src="assets1/vendor/aos/aos.js"></script>
                    <script src="assets1/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
                    <script src="assets1/vendor/glightbox/js/glightbox.min.js"></script>
                    <script src="assets1/vendor/isotope-layout/isotope.pkgd.min.js"></script>
                    <script src="assets1/vendor/swiper/swiper-bundle.min.js"></script>
                    <script src="assets1/vendor/php-email-form/validate.js"></script>

                    <!-- Template Main JS File -->
                    <script src="assets1/js/main.js"></script>
                    </body>

                    <style>
                        #no-more-tablesss,#no-more-tablesss1,#no-more-tablesss2{
                            display: none;
                        }
                        #closeCustomRangebtn{
                            position: relative;
                            left: 376px;
                            top: -29px;
                        }
                        #closeCustomRangebtn1{
                            position: relative;
                            left: 376px;
                            top: -29px;
                        }
                    </style>
                    </html>
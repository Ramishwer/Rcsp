<%-- 
    Document   : TestDevice
    Created on : 18 Dec, 2023, 11:34:38 AM
    Author     : Admin
--%>
<%@include file="session.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TestDevice</title>
        <%@include file="common_jcb/jcb.jsp"%>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col col-6">
                    <p>To add a test device, enter the device's phone number, including the country code and the area code.
                        To add multiple numbers, separate phone numbers with commas.</p>
                    <form class="form-group mt-3">
                        <div class="input-group">
                            <input type="text" class="form-control" name="testNumber" id="testNumber" placeholder="Enter Number">&nbsp;&nbsp;
                            <span class="input-group-btn" style="width:10px;">
                                <button type="button" class="btn btn-primary" onclick="testDevice()">ADD</button>
                            </span>
                        </div>
                    </form> 
                </div>
                
            </div>

            <div class="row">
                <div class="col col-10">
                    <div class="card">
                        <div class="card-header">
<div id="test4">
                    
                </div>
                        </div> 
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Phone Number</th>
                                        <th scope="col">Submitted</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Remove Device</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>

                                        <td>+919639632083</td>
                                        <td>Dec 15, 2023, 5:18:27 PM</td>
                                        <td>Ready (Send test message)</td>
                                        <td>Delete</td>
                                    </tr>
                                    <tr>

                                        <td>+919639632083</td>
                                        <td>Dec 15, 2023, 5:18:27 PM</td>
                                        <td>Ready (Send test message)</td>
                                        <td>Delete</td>
                                    </tr>
                                    <tr>

                                        <td>+919639632083</td>
                                        <td>Dec 15, 2023, 5:18:27 PM</td>
                                        <td>Ready (Send test message)</td>
                                        <td>Delete</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

<%-- 
    Document   : index
    Created on : 7 Dec, 2023, 8:09:58 PM
    Author     : Admin
--%>
<%--<%@include file="session.jsp" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RCS</title>
        <%@include file="common_jcb/jcb.jsp"%>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col col-3 mt-3">
                    <div class="card">
                        <div class="card-header text-center">
                            <h3>Send Tester Invite</h3> 
                        </div>
                        <div class="card-body">
                            <form class="form-group" action="send_Tester_Invite" method="post">
                                <input type="text" class="form-control" id="mobile" name="mobile" placeholder="Enter Mobile">
                                <div class="mt-3 text-center">
                                    <button type="button" class="btn btn-outline-primary" onclick="getData()">Submit</button>
                                </div>
                                <div id="test" class="mt-3 text-center">

                                </div>

                            </form> 
                        </div>
                    </div>

                    <div class="card mt-3">
                        <div class="card-header text-center">
                            <h3>Send Text</h3> 
                        </div>
                        <div class="card-body">
                            <form class="form-group">
                                <input type="text" class="form-control" id="mobile2" name="mobile2" placeholder="Enter Mobile">
                                <input type="text" class="form-control mt-3" id="text" name="text" placeholder="Enter message">

                                <div class="mt-3 text-center">
                                    <button type="button" class="btn btn-outline-primary" onclick="getMsg()">Submit</button>
                                </div>
                                <div id="test3" class="mt-3 text-center">

                                </div>

                            </form> 
                        </div>
                    </div>

                </div>

                <div class="col col-6 mt-3">
                    <div class="card">
                        <div class="card-header text-center">
                            <h3>Rich card carousels</h3> 
                        </div>
                        <div class="card-body">
                            <form class="form-group">
                                <input type="text" class="form-control mt-2" id="mobile1" name="mobile1" placeholder="Enter Mobile">
                                <input type="text" class="form-control mt-2" id="c1" name="c1" placeholder="Enter Suggestion1">
                                <input type="text" class="form-control mt-2" id="Title1" name="Title1" placeholder="Enter Title1">
                                <textarea type="text" class="form-control mt-2" id="d1" name="d1" placeholder="Enter description1"></textarea>
                                <input type="text" class="form-control mt-2" id="caredImage1" name="caredImage1" placeholder="Enter caredImageUrl_1">

                                <input type="text" class="form-control mt-2" id="c2" name="c2" placeholder="Enter Suggestion2">
                                <input type="text" class="form-control mt-2" id="Title2" name="Title2" placeholder="Enter Title2">
                                <textarea type="text" class="form-control mt-2" id="d2" name="d2" placeholder="Enter description2"></textarea>
                                <input type="text" class="form-control mt-2" id="caredImage2" name="caredImage2" placeholder="Enter caredImageUrl_2">


                                <input type="text" class="form-control mt-2" id="c3" name="c3" placeholder="Enter Suggestion3">
                                <input type="text" class="form-control mt-2" id="Title3" name="Title3" placeholder="Enter Title3">
                                <textarea type="text" class="form-control mt-2" id="d3" name="d3" placeholder="Enter description3"></textarea>
                                <input type="text" class="form-control mt-3" id="caredImage3" name="caredImage3" placeholder="Enter caredImageUrl_3">


                                <div class="mt-3 text-center">
                                    <button type="button" class="btn btn-outline-primary" onclick="getCarousels()">Submit</button>
                                </div>
                                <div id="test1" class="mt-3 text-center">

                                </div>

                            </form> 
                        </div>
                    </div>
                </div>
                <div class="col col-3 mt-3">
                    <div class="card">
                        <div class="card-header text-center">
                            <h3>Send Rich Card</h3> 
                            <div id="test5">
                                
                            </div>
                        </div>
                        <div class="card-body">
                            <form class="form-group">
                                <input type="text" class="form-control mt-2" id="mobile_richcard" name="mobile_richcard" placeholder="Enter Mobile">
                                <input type="text" class="form-control mt-2" id="url_richcard" name="url_richcard" placeholder="Enter Url_richcard">
                                <input type="text" class="form-control mt-2" id="suggestion1_richcard" name="suggestion1_richcard" placeholder="Enter Suggestion1">
                                <input type="text" class="form-control mt-2" id="suggestion2_richcard" name="suggestion2_richcard" placeholder="Enter Suggestion2">
                                <input type="text" class="form-control mt-2" id="title_richcard" name="title_richcard" placeholder="Enter Title">
                                <textarea type="text" class="form-control mt-2" id="description_richcard" name="description_richcard" placeholder="Enter description"></textarea>
                                <div class="mt-3 text-center">
                                    <button type="button" class="btn btn-outline-primary" onclick="getRichCard()">Submit</button>
                                </div>

                            </form> 
                        </div>
                    </div> 
                    
                       <div class="card mt-3">
                        <div class="card-header text-center">
                            <h3>Create a calendar event</h3> 
                            <div id="test6">
                                
                            </div>
                        </div>
                        <div class="card-body">
                            <form class="form-group">
                                <input type="text" class="form-control mt-2" id="mobile_calendar" name="mobile_calendar" placeholder="Enter Mobile">
                                <input type="text" class="form-control mt-2" id="calendarTitle" name="calendarTitle" placeholder="calendarTitle">
                                <textarea type="text" class="form-control mt-2" id="calendarDesc" name="calendarDesc" placeholder="Enter description"></textarea>
                                <input type="datetime-local" class="form-control mt-2" id="startTime" name="startTime" placeholder="StartTime">
                                <input type="datetime-local" class="form-control mt-2" id="setEndTime" name="setEndTime" placeholder="setEndTime">
                                <input type="text" class="form-control mt-2" id="setText" name="setText" placeholder="setText">
                                <input type="text" class="form-control mt-2" id="messageText" name="messageText" placeholder="messageText">
                                <div class="mt-3 text-center">
                                    <button type="button" class="btn btn-outline-primary" onclick="getCalendar()">Submit</button>
                                </div>

                            </form> 
                        </div>
                    </div> 
                </div>
            </div>
    </body>
</html>

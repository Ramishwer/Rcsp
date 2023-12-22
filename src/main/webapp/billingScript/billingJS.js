
/* global XLSX */



// Logger function to log messages with a timestamp
function logMessage(message) {
    var timestamp = new Date().toLocaleString();
    console.log(`[${timestamp}] ${message}`);
}
//search code script

function SearchBill() {
    var fromd = document.getElementById("startDate").value;
    var tod = document.getElementById("endDate").value;
    var BrandN = document.getElementById("BrandList").value;
    var BotList = document.getElementById("BotList").value;
    var listby = document.getElementById("listby").value;

    var url = "BillingTable.jsp?fromd=" + fromd + "&tod=" + tod + "&BrandN=" + BrandN + "&BotList=" + BotList + "&listby=" + listby;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    var jsonResponse = JSON.parse(this.responseText);
                    var basic = 0;
                    var A2PSingleMessageC = 0;
                    var A2PConversationC = 0;
                    var P2AConversationC = 0;
                    var basictotalDomestic = 0;
                    var basictotalInternational = 0;
                    var A2PSingletotalDomestic = 0;
                    var A2PSingletotalInternational = 0;
                    var A2PConversationtotalDomestic = 0;
                    var A2PConversationtotalInternational = 0;
                    var P2AConversationtotalDomestic = 0;
                    var InternationaltotalP2AConversation = 0;
//A2P Conversation
                    // Get the tbody element where the content will be added
                    var tbody = document.querySelector("#no-more-tablesss tbody");
                    var tbody1 = document.querySelector("#no-more-tablesss1 tbody");
                    var tbody2 = document.querySelector("#no-more-tablesss2 tbody");
                    var tbody3 = document.querySelector("#no-more-tablesssall tbody");

                    // Clear existing content in the tbody
                    tbody.innerHTML = "";
                    tbody1.innerHTML = "";
                    tbody2.innerHTML = "";
                    tbody3.innerHTML = "";

                    // Generate HTML based on the JSON data
                    for (var i = 0; i < jsonResponse.length; i++) {
                        var row = jsonResponse[i];
                        basic = basic + row.BasicMessageCount;
                        A2PSingleMessageC = A2PSingleMessageC + row.A2PSingleMessageCount;
                        A2PConversationC = A2PConversationC + row.A2PConversationCount;
                        P2AConversationC = P2AConversationC + row.P2AConversationCount;


                        // Additional logging
                        logMessage(`Processing row ${i + 1} - DateRange: ${row.DateRange}, TransactionType: ${row.TransactionType}, BasicMessageCount: ${row.BasicMessageCount}, A2PSingleMessageCount: ${row.A2PSingleMessageCount}, A2PConversationCount: ${row.A2PConversationCount}, P2AConversationCount: ${row.P2AConversationCount}`);


                        if (row.TransactionType === "Domestic") {
                            basictotalDomestic++;
                        }
                        if (row.TransactionType === "International") {
                            basictotalInternational++;
                        }
                        if (row.TransactionType === "Domestic") {
                            A2PSingletotalDomestic++;
                        }
                        if (row.TransactionType === "International") {
                            A2PSingletotalInternational++;
                        }
                        if (row.TransactionType === "Domestic") {
                            A2PConversationtotalDomestic++;
                        }
                        if (row.TransactionType === "International") {
                            A2PConversationtotalInternational++;
                        }
                        if (row.TransactionType === "Domestic") {
                            P2AConversationtotalDomestic++;
                        }

                        if (row.TransactionType === "International") {
                            InternationaltotalP2AConversation++;
                        }

                        if (listby === "ALL") {

                            document.getElementById("no-more-tablesss").style.display = "none";
                            document.getElementById("no-more-tablesss1").style.display = "none";
                            document.getElementById("no-more-tablesss2").style.display = "none";
                            document.getElementById("no-more-tablesssall").style.display = "table";

                            logMessage(`Adding row to Brands table - Brands: ${row.Brands}, TransactionType: ${row.TransactionType}, BasicMessageCount: ${row.BasicMessageCount}, A2PSingleMessageCount: ${row.A2PSingleMessageCount}, A2PConversationCount: ${row.A2PConversationCount}, P2AConversationCount: ${row.P2AConversationCount}`);


                            var newRow = document.createElement("tr");

                            // Add cells to the new row
                            newRow.innerHTML = "<td>" + row.DateRange + "</td>" +
                                    "<td>" + row.Brands + "</td>" +
                                    "<td>" + row.Bots + "</td>" +
                                    "<td>" + row.TransactionType + "</td>" +
                                    "<td>" + row.BasicMessageCount + "</td>" +
                                    "<td>" + row.A2PSingleMessageCount + "</td>" +
                                    "<td>" + row.A2PConversationCount + "</td>" +
                                    "<td>" + row.P2AConversationCount + "</td>";
//                                "<td>" + row.Brands + "</td>";

                            // Append the new row to the tbody
                            tbody3.appendChild(newRow);

                        } else if (listby === "DateRange") {
                            document.getElementById("no-more-tablesss").style.display = "table";
                            document.getElementById("no-more-tablesss1").style.display = "none";
                            document.getElementById("no-more-tablesss2").style.display = "none";
                            document.getElementById("no-more-tablesssall").style.display = "none";


                            // Logging for DateRange
                            logMessage(`Adding row to DateRange table - DateRange: ${row.DateRange}, TransactionType: ${row.TransactionType}, BasicMessageCount: ${row.BasicMessageCount}, A2PSingleMessageCount: ${row.A2PSingleMessageCount}, A2PConversationCount: ${row.A2PConversationCount}, P2AConversationCount: ${row.P2AConversationCount}`);

                            // Create a new table row
                            var newRow = document.createElement("tr");

                            // Add cells to the new row
                            newRow.innerHTML = "<td>" + row.DateRange + "</td>" +
                                    "<td>" + row.TransactionType + "</td>" +
                                    "<td>" + row.BasicMessageCount + "</td>" +
                                    "<td>" + row.A2PSingleMessageCount + "</td>" +
                                    "<td>" + row.A2PConversationCount + "</td>" +
                                    "<td>" + row.P2AConversationCount + "</td>";
//                                "<td>" + row.Brands + "</td>";

                            // Append the new row to the tbody
                            tbody.appendChild(newRow);

                        } else if (listby === "Brands") {
                            document.getElementById("no-more-tablesss").style.display = "none";
                            document.getElementById("no-more-tablesss1").style.display = "table";
                            document.getElementById("no-more-tablesss2").style.display = "none";
                            document.getElementById("no-more-tablesssall").style.display = "none";

                            logMessage(`Adding row to Brands table - Brands: ${row.Brands}, TransactionType: ${row.TransactionType}, BasicMessageCount: ${row.BasicMessageCount}, A2PSingleMessageCount: ${row.A2PSingleMessageCount}, A2PConversationCount: ${row.A2PConversationCount}, P2AConversationCount: ${row.P2AConversationCount}`);


                            var newRow = document.createElement("tr");

                            // Add cells to the new row
                            newRow.innerHTML = "<td>" + row.Brands + "</td>" +
                                    "<td>" + row.TransactionType + "</td>" +
                                    "<td>" + row.BasicMessageCount + "</td>" +
                                    "<td>" + row.A2PSingleMessageCount + "</td>" +
                                    "<td>" + row.A2PConversationCount + "</td>" +
                                    "<td>" + row.P2AConversationCount + "</td>";
//                                "<td>" + row.Brands + "</td>";

                            // Append the new row to the tbody
                            tbody1.appendChild(newRow);


                        } else if (listby === "Bots") {

                            document.getElementById("no-more-tablesss").style.display = "none";
                            document.getElementById("no-more-tablesss1").style.display = "none";
                            document.getElementById("no-more-tablesss2").style.display = "table";
                            document.getElementById("no-more-tablesssall").style.display = "none";

                            logMessage(`Adding row to Bots table - Bots: ${row.Bots}, TransactionType: ${row.TransactionType}, BasicMessageCount: ${row.BasicMessageCount}, A2PSingleMessageCount: ${row.A2PSingleMessageCount}, A2PConversationCount: ${row.A2PConversationCount}, P2AConversationCount: ${row.P2AConversationCount}`);

                            var newRow = document.createElement("tr");

                            // Add cells to the new row
                            newRow.innerHTML = "<td>" + row.Bots + "</td>" +
                                    "<td>" + row.TransactionType + "</td>" +
                                    "<td>" + row.BasicMessageCount + "</td>" +
                                    "<td>" + row.A2PSingleMessageCount + "</td>" +
                                    "<td>" + row.A2PConversationCount + "</td>" +
                                    "<td>" + row.P2AConversationCount + "</td>";
//                                "<td>" + row.Brands + "</td>";

                            // Append the new row to the tbody
                            tbody2.appendChild(newRow);


                        } else {
                            document.getElementById("no-records-message").innerHTML = "No Record Found!!";
                        }
                        document.getElementById("no-records-message").style.display = "none";
                        document.getElementById("basic_msg_bill").innerHTML = basic;
                        document.getElementById("A2P_msg_bill").innerHTML = A2PSingleMessageC;
                        document.getElementById("A2P_Conv_bill").innerHTML = A2PConversationC;
                        document.getElementById("P2A_Conv_bill").innerHTML = P2AConversationC;
                        document.getElementById("Domesticvalue").innerHTML = basictotalDomestic;
                        document.getElementById("Intervalue").innerHTML = basictotalInternational;
                        document.getElementById("DomesticA2p").innerHTML = A2PSingletotalDomestic;
                        document.getElementById("InternationalA2p").innerHTML = A2PSingletotalInternational;
                        document.getElementById("DomesticA2PConversation").innerHTML = A2PConversationtotalDomestic;
                        document.getElementById("InternationalA2PConversation").innerHTML = A2PConversationtotalInternational;
                        document.getElementById("DomesticP2AConversation").innerHTML = P2AConversationtotalDomestic;
                        document.getElementById("InternationalP2AConversation").innerHTML = InternationaltotalP2AConversation;

                    }

                    logMessage("SearchBill function completed successfully.");


                } catch (error) {
                    console.error("Error:", error);

                    logMessage("Error occurred in SearchBill function. Check the console for details.");

                    alert("Error catch");
                }
            } else {
                console.error("Failed to load. Status:", xhr.status);
                //  alert("Failed to load");
                logMessage("SearchBill function failed to load. Check the console for details.");

            }
        }
    };

    xhr.open("GET", url, true);
    xhr.send();
}

logMessage("Script initialized.");


///  brand search ajax
function GetBrands() {
    var fromd = document.getElementById("startDate").value;
    var tod = document.getElementById("endDate").value;

    var url = "Bransearch.jsp?fromd=" + fromd + "&tod=" + tod;
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    // Log that JSON parsing is about to start
                    logMessage("Parsing JSON response.");

                    var responseText = xhr.responseText;
                    if (responseText) {
                        const myJSON = JSON.parse(responseText);
                        if (myJSON.Array1) {
                            // Log that Drp_val_jsn_call is being called
                            logMessage("Calling Drp_val_jsn_call function.");

                            Drp_val_jsn_call(myJSON.Array1);
                        } else {
                            console.error("JSON structure is not as expected:", myJSON);

                        }
                    } else {
                        console.error("Empty response text");

                    }

                    // Log that JSON parsing is completed
                    logMessage("JSON parsing completed.");
                } catch (error) {
                    console.error("Error parsing JSON:", error);

                }
            } else {
                console.error("Request failed with status:", xhr.status);

            }
            // Log that the function has completed
            logMessage("GetBrands function completed.");
        }
    };

    xhr.open("GET", url, true);
    xhr.send();
}
// Example usage of the logger
logMessage("Script initialized.");

function Drp_val_jsn_call(Drp_val_jsn) {

    // Log that the function has started
    logMessage("Drp_val_jsn_call function started.");

    var dropdown = document.getElementById('BrandList');
    dropdown.innerHTML = ''; // Clear existing options

    for (var i = 0; i < Drp_val_jsn.length; i++) {
        var opt = Drp_val_jsn[i];

        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;

        dropdown.appendChild(el);

    }
    // Log that the function has completed
    logMessage("Drp_val_jsn_call function completed.");
}


// bot ajax

function GetBot() {

    // Log that the function has started
    logMessage("GetBot function started.");

    var fromd = document.getElementById("startDate").value;
    var tod = document.getElementById("endDate").value;
    var BrandN = document.getElementById("BrandList").value;

    var url = "botsearch.jsp?fromd=" + fromd + "&tod=" + tod + "&BrandN=" + BrandN;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    // Log that JSON parsing is about to start
                    logMessage("Parsing JSON response.");

                    var responseText = xhr.responseText;
                    if (responseText) {
                        const myJSON = JSON.parse(responseText);
                        if (myJSON.Array1) {
                            // Log that Drp_val_jsn_call_bot is being called
                            logMessage("Calling Drp_val_jsn_call_bot function.");

                            Drp_val_jsn_call_bot(myJSON.Array1);
                        } else {
                            console.error("JSON structure is not as expected:", myJSON);

                        }
                    } else {
                        console.error("Empty response text");

                    }
                    // Log that JSON parsing is completed
                    logMessage("JSON parsing completed.");
                } catch (error) {
                    console.error("Error parsing JSON:", error);

                }
            } else {
                console.error("Request failed with status:", xhr.status);

            }
            // Log that the function has completed
            logMessage("GetBot function completed.");
        }
    };

    xhr.open("GET", url, true);
    xhr.send();
}



function Drp_val_jsn_call_bot(Drp_val_jsn) {

    // Log that the function has started
    logMessage("Drp_val_jsn_call_bot function started.");


    var dropdown = document.getElementById('BotList');
    dropdown.innerHTML = ''; // Clear existing options

    for (var i = 0; i < Drp_val_jsn.length; i++) {
        var opt = Drp_val_jsn[i];

        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;

        dropdown.appendChild(el);
    }
    // Log that the function has completed
    logMessage("Drp_val_jsn_call_bot function completed.");
}


// date js 
function updateDateRange() {
    // Log that the function has started
    logMessage("updateDateRange function started.");

    var dateRange = document.getElementById("dateRange").value;


    var todayDate = new Date();
    var year = todayDate.getFullYear();
    var month = (todayDate.getMonth() + 1).toString().padStart(2, '0');
    var day = todayDate.getDate().toString().padStart(2, '0');
    var todaysDate = year + '-' + month + '-' + day;


    if (dateRange === "today") {
        setDates(todaysDate, todaysDate);
    } else if (dateRange === "yesterday") {
        var yesterday = new Date(todayDate);
        yesterday.setDate(todayDate.getDate() - 1);
        var yearYesterday = yesterday.getFullYear();
        var monthYesterday = (yesterday.getMonth() + 1).toString().padStart(2, '0');
        var dayYesterday = yesterday.getDate().toString().padStart(2, '0');
        var yesterdayDate = yearYesterday + '-' + monthYesterday + '-' + dayYesterday;
        setDates(yesterdayDate, yesterdayDate);
    } else if (dateRange === "Last 7 days") {
        var Last7days = new Date(todayDate);
        Last7days.setDate(todayDate.getDate() - 6);
        var yearLast7days = Last7days.getFullYear();
        var monthLast7days = (Last7days.getMonth() + 1).toString().padStart(2, '0');
        var dayLast7days = Last7days.getDate().toString().padStart(2, '0');
        var Last7days = yearLast7days + '-' + monthLast7days + '-' + dayLast7days;
        setDates(Last7days, todaysDate);
    } else if (dateRange === "currentWeek") {
        // Calculate the start date as Monday of the current week
        var thisWeekStart = new Date(todayDate);
        thisWeekStart.setDate(todayDate.getDate() - todayDate.getDay() + 1);
        var yearStart = thisWeekStart.getFullYear();
        var monthStart = (thisWeekStart.getMonth() + 1).toString().padStart(2, '0');
        var dayStart = thisWeekStart.getDate().toString().padStart(2, '0');
        var thisWeekStartStr = yearStart + '-' + monthStart + '-' + dayStart;
        setDates(thisWeekStartStr, todaysDate);
    } else if (dateRange === "Last30Days") {
        var Last30Days = new Date(todayDate);
        Last30Days.setDate(todayDate.getDate() - 30);
        var yearLast30Days = Last30Days.getFullYear();
        var monthLast30Days = (Last30Days.getMonth() + 1).toString().padStart(2, '0');
        var dayLast30Days = Last30Days.getDate().toString().padStart(2, '0');
        var Last30DaysDate = yearLast30Days + '-' + monthLast30Days + '-' + dayLast30Days;
        setDates(Last30DaysDate, todaysDate);
    } else if (dateRange === "ThisMonth") {
        // Calculate the start date as the 1st day of the current month
        var thisMonthStart = new Date(todayDate.getFullYear(), todayDate.getMonth(), 1);
        var yearMonthStart = thisMonthStart.getFullYear();
        var monthMonthStart = (thisMonthStart.getMonth() + 1).toString().padStart(2, '0');
        var dayMonthStart = thisMonthStart.getDate().toString().padStart(2, '0');
        var thisMonthStartStr = yearMonthStart + '-' + monthMonthStart + '-' + dayMonthStart;
        setDates(thisMonthStartStr, todaysDate);
    } else if (dateRange === "LastMonth") {
        var lastMonthStart = new Date(todayDate.getFullYear(), todayDate.getMonth() - 1, 1);
        var lastMonthEnd = new Date(todayDate.getFullYear(), todayDate.getMonth(), 0);
        var yearLastMonthStart = lastMonthStart.getFullYear();
        var monthLastMonthStart = (lastMonthStart.getMonth() + 1).toString().padStart(2, '0');
        var dayLastMonthStart = lastMonthStart.getDate().toString().padStart(2, '0');
        var yearLastMonthEnd = lastMonthEnd.getFullYear();
        var monthLastMonthEnd = (lastMonthEnd.getMonth() + 1).toString().padStart(2, '0');
        var dayLastMonthEnd = lastMonthEnd.getDate().toString().padStart(2, '0');
        var lastMonthStartStr = yearLastMonthStart + '-' + monthLastMonthStart + '-' + dayLastMonthStart;
        var lastMonthEndStr = yearLastMonthEnd + '-' + monthLastMonthEnd + '-' + dayLastMonthEnd;
        setDates(lastMonthStartStr, lastMonthEndStr);
    } else if (dateRange === "LastWeek") {
        var lastWeekStart = new Date(todayDate);
        lastWeekStart.setDate(todayDate.getDate() - todayDate.getDay() - 6);
        var lastWeekEnd = new Date(todayDate);
        lastWeekEnd.setDate(todayDate.getDate() - todayDate.getDay());
        var yearLastWeekStart = lastWeekStart.getFullYear();
        var monthLastWeekStart = (lastWeekStart.getMonth() + 1).toString().padStart(2, '0');
        var dayLastWeekStart = lastWeekStart.getDate().toString().padStart(2, '0');
        var yearLastWeekEnd = lastWeekEnd.getFullYear();
        var monthLastWeekEnd = (lastWeekEnd.getMonth() + 1).toString().padStart(2, '0');
        var dayLastWeekEnd = lastWeekEnd.getDate().toString().padStart(2, '0');
        var lastWeekStartStr = yearLastWeekStart + '-' + monthLastWeekStart + '-' + dayLastWeekStart;
        var lastWeekEndStr = yearLastWeekEnd + '-' + monthLastWeekEnd + '-' + dayLastWeekEnd;
        setDates(lastWeekStartStr, lastWeekEndStr);
    } else if (dateRange === "custom") {
        document.getElementById("customRangeInput1").style.display = "block";
        var customStartDate = document.getElementById("customStartDate1").value;
        var customEndDate = document.getElementById("customEndDate1").value;
        setDates(customStartDate, customEndDate);
        //  GetBrands1();  // Apply GetBrands1 directly for custom range
    }
// Log that the function has completed
    logMessage("updateDateRange function completed.");

}

function setDates(LstartDate, LendDate) {

    // Log that the function has started
    logMessage("setDates function started.");
    // Set the values directly to the hidden input fields
    document.getElementById("startDate").value = LstartDate;
    document.getElementById("endDate").value = LendDate;
    // alert(LstartDate);
    // alert(LendDate);

    // Log that the function has completed
    logMessage("setDates function completed.");
    GetBrands();
}

function applyCustomRange1() {
    // Log that the function has started
    logMessage("applyCustomRange1 function started.");

    var customStartDate = document.getElementById("customStartDate1").value;
    var customEndDate = document.getElementById("customEndDate1").value;
    setDates(customStartDate, customEndDate);
    GetBrands();  // Apply GetBrands1 when the Apply button is clicked

    // Log that the function has completed
    logMessage("applyCustomRange1 function completed.");
}
function closeCustomRange() {
    // Log that the function has started
    logMessage("closeCustomRange function started.");

    document.getElementById("customRangeInput1").style.display = "none";

    // Log that the function has completed
    logMessage("closeCustomRange function completed.");
}

function exportToPDF(tableId, pdfFileName) {
    // Log that the function has started
    logMessage("exportToPDF function started.");

    html2canvas(document.getElementById(tableId), {
        onrendered: function (canvas) {
            var data = canvas.toDataURL();
            var pdfContent = {
                content: [{
                        image: data,
                        width: 500
                    }]
            };
            pdfMake.createPdf(pdfContent).download(pdfFileName);

            // Log that the PDF creation and download are completed
            logMessage(`PDF exported and downloaded with the filename: ${pdfFileName}`);
        }
    });
    // Log that the function has completed
    logMessage("exportToPDF function completed.");
}

function downloadPDFReport() {

    // Log that the function has started
    logMessage("downloadPDFReport function started.");

    var tableId;

    if (document.getElementById("no-more-tablesss").style.display === "table") {
        tableId = "no-more-tablesss";
    } else if (document.getElementById("no-more-tablesss1").style.display === "table") {
        tableId = "no-more-tablesss1";
    } else if (document.getElementById("no-more-tablesss2").style.display === "table") {
        tableId = "no-more-tablesss2";
    } else if (document.getElementById("no-more-tablesssall").style.display === "table") {
        tableId = "no-more-tablesssall";
    }


    //no-more-tablesssall

    if (tableId) {
        exportToPDF(tableId, "exported_data.pdf");
    } else {
        // Handle the case when no table is visible
        console.log("No visible table to export");

        // Handle the case when no table is visible
        logMessage("No visible table to export");
    }
    // Log that the function has completed
    logMessage("downloadPDFReport function completed.");
}


function exportToExcel(tableId, sheetName) {
    // Log that the function has started
    logMessage("exportToExcel function started.");

    var table = document.getElementById(tableId);
    var ws = XLSX.utils.table_to_sheet(table);
    var wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, sheetName);
    XLSX.writeFile(wb, "exported_data.xlsx");

    // Log that the Excel creation and download are completed
    logMessage(`Excel exported and downloaded with the sheetName: ${sheetName}`);

    // Log that the function has completed
    logMessage("exportToExcel function completed.");
}

function downloadReport() {
    // Log that the function has started
    logMessage("downloadReport function started.");

    var tableId;

    if (document.getElementById("no-more-tablesss").style.display === "table") {
        tableId = "no-more-tablesss";
    } else if (document.getElementById("no-more-tablesss1").style.display === "table") {
        tableId = "no-more-tablesss1";
    } else if (document.getElementById("no-more-tablesss2").style.display === "table") {
        tableId = "no-more-tablesss2";
    } else if (document.getElementById("no-more-tablesssall").style.display === "table") {
        tableId = "no-more-tablesssall";
    }


    if (tableId) {
        exportToExcel(tableId, "Sheet1");
    } else {
        // Handle the case when no table is visible
        console.log("No visible table to export");

        // Handle the case when no table is visible
        logMessage("No visible table to export");
    }

    // Log that the function has completed
    logMessage("downloadReport function completed.");
}




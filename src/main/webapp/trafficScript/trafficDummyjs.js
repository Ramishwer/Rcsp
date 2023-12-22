/* global XLSX */

// Logger function to log messages with a timestamp
function logMessage(message) {
    var timestamp = new Date().toLocaleString();
    console.log(`[${timestamp}] ${message}`);
}

//search code script
function Searchtraffic() {
    logMessage("Searchtraffic function called");
    // alert("Traffic");
    var startDate1 = document.getElementById("startDate1").value;
    logMessage(`startDate1: ${startDate1}`);
    //  alert("startDate1:"+startDate1);

    var endDate1 = document.getElementById("endDate1").value;
    logMessage(`endDate1: ${endDate1}`);

    //  alert("endDate1:"+startDate1);

    var BrandList1 = document.getElementById("BrandList1").value;
    logMessage(`BrandList1: ${BrandList1}`);
    //  alert("BrandList1:"+BrandList1);

    var BotList1 = document.getElementById("BotList1").value;
    logMessage(`BotList1: ${BotList1}`);
    //  alert("BotList1:"+BotList1);

    var listby1 = document.getElementById("listby1").value;
    logMessage(`listby1: ${listby1}`);
    //  alert("listby1:"+listby1);


    //   var url = "TrafiicTable.jsp";

    var url = "TrafiicTable.jsp?startDate1=" + startDate1 + "&endDate1=" + endDate1 + "&BrandList1=" + BrandList1 + "&BotList1=" + BotList1 + "&listby1=" + listby1;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    var a = this.responseText;
                    document.getElementById("traffictableD").innerHTML = a;
                    logMessage("Response received successfully");

                } catch (error) {
                    logMessage(`Error in response handling: ${error}`);
                    alert("Error catch");
                }
            } else {
                logMessage(`Failed to load. Status code: ${xhr.status}`);
                alert("Failed to load");
            }
        }
    };

    xhr.open("GET", url, true);
    xhr.send( );
}

///  brand search ajax
function GetBrands1() {
    logMessage("GetBrands1 function called");

    var fromd = document.getElementById("startDate1").value;
    var tod = document.getElementById("endDate1").value;

    var url = "Brandtraffic.jsp?fromd=" + fromd + "&tod=" + tod;
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    var responseText = xhr.responseText;
                    if (responseText) {
                        const myJSON = JSON.parse(responseText);
                        if (myJSON.Array1) {
                            Drp_val_jsn_call1(myJSON.Array1);
                            logMessage("Dropdown values successfully retrieved and processed");

                        } else {
                            logMessage("JSON structure is not as expected:" + JSON.stringify(myJSON));
                            console.error("JSON structure is not as expected:", myJSON);

                        }
                    } else {
                        logMessage("Empty response text");
                        console.error("Empty response text");

                    }
                } catch (error) {
                    logMessage("Error parsing JSON:" + error);
                    console.error("Error parsing JSON:", error);

                }
            } else {
                logMessage("Request failed with status:" + xhr.status);
                console.error("Request failed with status:", xhr.status);

            }
        }
    };

    xhr.open("GET", url, true);
    xhr.send();
}

function Drp_val_jsn_call1(Drp_val_jsn) {

    logMessage("Drp_val_jsn_call1 function called");
    var dropdown = document.getElementById('BrandList1');
    dropdown.innerHTML = ''; // Clear existing options

    for (var i = 0; i < Drp_val_jsn.length; i++) {
        var opt = Drp_val_jsn[i];

        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;

        dropdown.appendChild(el);

    }
    logMessage("Dropdown values successfully populated");

}


// bot ajax

function GetBot1() {

    logMessage("GetBot1 function called");

    var fromd = document.getElementById("startDate1").value;
    var tod = document.getElementById("endDate1").value;
    var BrandN = document.getElementById("BrandList1").value;

    var url = "bottraffic.jsp?fromd=" + fromd + "&tod=" + tod + "&BrandN=" + BrandN;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    var responseText = xhr.responseText;
                    if (responseText) {
                        const myJSON = JSON.parse(responseText);
                        if (myJSON.Array1) {
                            Drp_val_jsn_call_bot1(myJSON.Array1);
                            logMessage("Dropdown values for bots successfully retrieved and processed");
                        } else {
                            logMessage("JSON structure is not as expected: " + JSON.stringify(myJSON));
                            console.error("JSON structure is not as expected:", myJSON);
                        }
                    } else {
                        logMessage("Empty response text");
                        console.error("Empty response text");
                    }
                } catch (error) {
                    logMessage("Error parsing JSON: " + error);
                    console.error("Error parsing JSON:", error);
                }
            } else {
                logMessage("Request failed with status: " + xhr.status);
                console.error("Request failed with status:", xhr.status);

            }
        }
    };

    xhr.open("GET", url, true);
    xhr.send();
}



function Drp_val_jsn_call_bot1(Drp_val_jsn) {

    logMessage("Drp_val_jsn_call_bot1 function called");

    var dropdown = document.getElementById('BotList1');
    dropdown.innerHTML = ''; // Clear existing options

    for (var i = 0; i < Drp_val_jsn.length; i++) {
        var opt = Drp_val_jsn[i];

        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;

        dropdown.appendChild(el);
    }
    logMessage("Dropdown values for bots successfully populated");

}



//templates

function Gettemplate() {

    logMessage("Gettemplate function called");

    var fromd = document.getElementById("startDate1").value;
    var tod = document.getElementById("endDate1").value;
    var BrandN = document.getElementById("BrandList1").value;
    var BotN = document.getElementById("BotList1").value;
    var template = document.getElementById("templatesid").value;

    //  alert("template:"+template);

    var url = "templatetraffic.jsp?fromd=" + fromd + "&tod=" + tod + "&BrandN=" + BrandN + "&BotN=" + BotN + "&template" + template;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    var responseText = xhr.responseText;
                    if (responseText) {
                        const myJSON = JSON.parse(responseText);
                        if (myJSON.Array1) {
                            Drp_val_jsn_call_bot2(myJSON.Array1);
                            logMessage("Dropdown values for template successfully retrieved and processed");

                        } else {
                            logMessage("JSON structure is not as expected: " + JSON.stringify(myJSON));

                            console.error("JSON structure is not as expected:", myJSON);

                        }
                    } else {
                        logMessage("Empty response text");

                        console.error("Empty response text");

                    }
                } catch (error) {
                    logMessage("Error parsing JSON: " + error);

                    console.error("Error parsing JSON:", error);

                }
            } else {
                logMessage("Request failed with status: " + xhr.status);

                console.error("Request failed with status:", xhr.status);

            }
        }
    };

    xhr.open("GET", url, true);
    xhr.send();
}



function Drp_val_jsn_call_bot2(Drp_val_jsn) {
    logMessage("Drp_val_jsn_call_bot2 function called");


    var dropdown = document.getElementById('templatesid');
    dropdown.innerHTML = ''; // Clear existing options

    for (var i = 0; i < Drp_val_jsn.length; i++) {
        var opt = Drp_val_jsn[i];

        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;

        dropdown.appendChild(el);
    }
    logMessage("Dropdown values for templates successfully populated");

}


// date js dateRange1
function updateDateRange1() {
    var dateRange = document.getElementById("dateRange1").value;
    logMessage("updateDateRange1 function called");

//document.getElementById("todayRangeInputs").style.display = "none";
//    document.getElementById("customRangeInputs").style.display = "none";


    var todayDate = new Date();
    var year = todayDate.getFullYear();
    var month = (todayDate.getMonth() + 1).toString().padStart(2, '0');
    var day = todayDate.getDate().toString().padStart(2, '0');

    var todaysDate = year + '-' + month + '-' + day;


    if (dateRange === "today") {
        setDates1(todaysDate, todaysDate);

    } else if (dateRange === "yesterday") {
        var yesterday = new Date(todayDate);
        yesterday.setDate(todayDate.getDate() - 1);
        var yearYesterday = yesterday.getFullYear();
        var monthYesterday = (yesterday.getMonth() + 1).toString().padStart(2, '0');
        var dayYesterday = yesterday.getDate().toString().padStart(2, '0');
        var yesterdayDate = yearYesterday + '-' + monthYesterday + '-' + dayYesterday;
        setDates1(yesterdayDate, yesterdayDate);
    } else if (dateRange === "Last 7 days") {
        var Last7days = new Date(todayDate);
        Last7days.setDate(todayDate.getDate() - 6);
        var yearLast7days = Last7days.getFullYear();
        var monthLast7days = (Last7days.getMonth() + 1).toString().padStart(2, '0');
        var dayLast7days = Last7days.getDate().toString().padStart(2, '0');
        var Last7days = yearLast7days + '-' + monthLast7days + '-' + dayLast7days;
        setDates1(Last7days, todaysDate);
    } else if (dateRange === "Last30Days") {
        var Last30Days = new Date(todayDate);
        Last30Days.setDate(todayDate.getDate() - 30);
        var yearLast30Days = Last30Days.getFullYear();
        var monthLast30Days = (Last30Days.getMonth() + 1).toString().padStart(2, '0');
        var dayLast30Days = Last30Days.getDate().toString().padStart(2, '0');
        var Last30DaysDate = yearLast30Days + '-' + monthLast30Days + '-' + dayLast30Days;
        setDates1(Last30DaysDate, todaysDate);
    } else if (dateRange === "currentWeek") {
        // Calculate the start date as Monday of the current week
        var thisWeekStart = new Date(todayDate);
        thisWeekStart.setDate(todayDate.getDate() - todayDate.getDay() + 1);
        var yearStart = thisWeekStart.getFullYear();
        var monthStart = (thisWeekStart.getMonth() + 1).toString().padStart(2, '0');
        var dayStart = thisWeekStart.getDate().toString().padStart(2, '0');
        var thisWeekStartStr = yearStart + '-' + monthStart + '-' + dayStart;
        setDates1(thisWeekStartStr, todaysDate);
    } else if (dateRange === "ThisMonth") {
        // Calculate the start date as the 1st day of the current month
        var thisMonthStart = new Date(todayDate.getFullYear(), todayDate.getMonth(), 1);
        var yearMonthStart = thisMonthStart.getFullYear();
        var monthMonthStart = (thisMonthStart.getMonth() + 1).toString().padStart(2, '0');
        var dayMonthStart = thisMonthStart.getDate().toString().padStart(2, '0');
        var thisMonthStartStr = yearMonthStart + '-' + monthMonthStart + '-' + dayMonthStart;
        setDates1(thisMonthStartStr, todaysDate);
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
        setDates1(lastMonthStartStr, lastMonthEndStr);
    } else if (dateRange === "custom") {
        document.getElementById("customRangeInputs").style.display = "block";
        var customStartDate = document.getElementById("customStartDate").value;
        var customEndDate = document.getElementById("customEndDate").value;
        setDates1(customStartDate, customEndDate);
        //  GetBrands1();  // Apply GetBrands1 directly for custom range
    }
    logMessage("Date range updated");

}

function setDates1(LstartDate1, LendDate1) {

    logMessage("setDates1 function called");

    // Set the values directly to the hidden input fields
    document.getElementById("startDate1").value = LstartDate1;
    document.getElementById("endDate1").value = LendDate1;
    //  alert(LstartDate1);
    //alert(LendDate1);

    logMessage(`Dates set - Start Date: ${LstartDate1}, End Date: ${LendDate1}`);
    GetBrands1();
}

function applyCustomRange() {
    logMessage("applyCustomRange function called");

    var customStartDate = document.getElementById("customStartDate").value;
    var customEndDate = document.getElementById("customEndDate").value;
    setDates1(customStartDate, customEndDate);
    GetBrands1();  // Apply GetBrands1 when the Apply button is clicked

    logMessage("Custom range applied");

}

// Rest of your JavaScript code, including updateDateRange1, setDates1, and GetBrands1 functions

function closeCustomRange1() {
    document.getElementById("customRangeInputs").style.display = "none";
}


function exportAllToExcel() {

    logMessage("exportAllToExcel function called");

    var wb = XLSX.utils.book_new();

    // Iterate through tables and add them to the workbook
    ['myTable', 'myTable1', 'myTable2', 'myTable3', 'myTable4'].forEach(function (tableId, index) {
        var table = document.getElementById(tableId);

        // Check if the table element is found
        if (table) {
            var ws = XLSX.utils.table_to_sheet(table);
            XLSX.utils.book_append_sheet(wb, ws, 'Sheet' + (index + 1));
        } else {
            logMessage("Table with ID " + tableId + " not found.");

            console.error("Table with ID " + tableId + " not found.");
        }
    });

    // Trigger the download if at least one table is found
    if (wb.SheetNames.length > 0) {
        XLSX.writeFile(wb, "exported_data.xlsx");
        logMessage("Excel file exported successfully");

    } else {
        logMessage("No tables found for export.");

        console.error("No tables found for export.");
    }
}

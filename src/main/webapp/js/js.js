
function getData() {


    var mobile = document.getElementById("mobile").value;
    if (mobile === "") {
        alert("please enter number");
        return false;
    }

    document.getElementById("test").innerHTML = "Please wait ....";
    var url = "send_Tester_Invite?mobile=" + mobile;
    var xhr = new XMLHttpRequest();
    xhr.open("get", url, true);
    setTimeout(function () {
        document.getElementById("test").innerHTML = "Send";
    }, 1000);
    xhr.onload = function () {

        document.getElementById("test").innerHTML = this.responseText;

    },
            xhr.send();
}


function getCarousels() {
    var mobile1 = document.getElementById("mobile1").value;
    var c1 = document.getElementById("c1").value;
    var Title1 = document.getElementById("Title1").value;
    var d1 = document.getElementById("d1").value;
    var caredImage1 = document.getElementById("caredImage1").value;
    var c2 = document.getElementById("c2").value;
    var Title2 = document.getElementById("Title2").value;
    var d2 = document.getElementById("d2").value;
    var caredImage2 = document.getElementById("caredImage2").value;
    var c3 = document.getElementById("c3").value;
    var Title3 = document.getElementById("Title3").value;
    var d3 = document.getElementById("d3").value;
    var caredImage3 = document.getElementById("caredImage3").value;
    
   

    document.getElementById("test1").innerHTML = "Please wait ....";
    var url = "Rich_card_carousels?mobile1="+mobile1+"&c1="+c1+"&Title1="+Title1+"&d1="+d1+"&caredImage1="+caredImage1+"&c2="+c2+"&Title2="+Title2+"&d2="+d2+"&caredImage2="+caredImage2+"&c3="+c3+"&Title3="+Title3+"&d3="+d3+"&caredImage3="+caredImage3;
    var xhr = new XMLHttpRequest();
    xhr.open("get", url, true);
    setTimeout(function () {
        document.getElementById("test1").innerHTML = "Send";
    }, 1000);
    xhr.onload = function () {

        document.getElementById("test1").innerHTML = this.responseText;

    },
            xhr.send();
}


function getMsg() {


    var mobile2 = document.getElementById("mobile2").value;
    var text = document.getElementById("text").value;
    if (mobile2 === "") {
        alert("please enter number");
        return false;
    }
    
     if (text === "") {
        alert("please enter message");
        return false;
    }
 
    document.getElementById("test3").innerHTML = "Please wait ....";
    var url = "textServlet?mobile2=" + mobile2+"&text="+text;
    var xhr = new XMLHttpRequest();
    xhr.open("get", url, true);
    setTimeout(function () {
        document.getElementById("test3").innerHTML = "Send";
    }, 1000);
    xhr.onload = function () {

        document.getElementById("test3").innerHTML = this.responseText;

    },
            xhr.send();
}

function getRichCard(){


    var mobile_richcard = document.getElementById("mobile_richcard").value;
    var url_richcard = document.getElementById("url_richcard").value;
    var suggestion1_richcard = document.getElementById("suggestion1_richcard").value;
    var suggestion2_richcard = document.getElementById("suggestion2_richcard").value;
    var title_richcard = document.getElementById("title_richcard").value;
    var description_richcard = document.getElementById("description_richcard").value;

    if (mobile_richcard === "") {
        alert("please enter number");
        return false;
    }
    
     if (url_richcard === "") {
        alert("please enter message");
        return false;
    }
 
    document.getElementById("test5").innerHTML = "Please wait ....";
    var url = "richcardServlet?mobile_richcard=" + mobile_richcard+"&url_richcard="+url_richcard+"&suggestion1_richcard="+suggestion1_richcard+"&suggestion2_richcard="+suggestion2_richcard+"&title_richcard="+title_richcard+"&description_richcard="+description_richcard;
    var xhr = new XMLHttpRequest();
    xhr.open("get", url, true);
    setTimeout(function () {
        document.getElementById("test5").innerHTML = "Send";
    }, 1000);
    xhr.onload = function () {

        document.getElementById("test5").innerHTML = this.responseText;

    },
            xhr.send();
}



function testDevice()
{
    var testNumber = document.getElementById("testNumber").value;
     if (testNumber === "") {
        alert("please enter number");
        return false;
    }
      document.getElementById("test4").innerHTML = "Please wait ....";
    var url = "TestDevice?testNumber=" + testNumber;
    var xhr = new XMLHttpRequest();
    xhr.open("get", url, true);
    setTimeout(function () {
        document.getElementById("test4").innerHTML = "Send";
    }, 1000);
    xhr.onload = function () {

        document.getElementById("test4").innerHTML = this.responseText;

    },
            xhr.send();
}


function getCalendar(){


    var mobile_calendar = document.getElementById("mobile_calendar").value;
    var calendarTitle = document.getElementById("calendarTitle").value;
    var calendarDesc = document.getElementById("calendarDesc").value;
    var startTime = document.getElementById("startTime").value;
    var setEndTime = document.getElementById("setEndTime").value;
    var setText = document.getElementById("setText").value;
    var messageText = document.getElementById("messageText").value;

    if (mobile_calendar === "") {
        alert("please enter number");
        return false;
    }
    
     if (calendarTitle === "") {
        alert("please enter message");
        return false;
    }
 
    document.getElementById("test6").innerHTML = "Please wait ....";
    var url = "Create_calendar_event?mobile_calendar=" + mobile_calendar+"&calendarTitle="+calendarTitle+"&calendarDesc="+calendarDesc+"&startTime="+startTime+"&setEndTime="+setEndTime+"&setText="+setText+"&messageText="+messageText;
    var xhr = new XMLHttpRequest();
    xhr.open("get", url, true);
    setTimeout(function () {
        document.getElementById("test6").innerHTML = "Send";
    }, 1000);
    xhr.onload = function () {

        document.getElementById("test6").innerHTML = this.responseText;

    },
            xhr.send();
}










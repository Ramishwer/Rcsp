function GETOTP() {
    var Username = document.getElementById("Email").value;
    

   

    var url = "SendMailServlet?Username=" +  Username;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        try {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var resp = xhr.responseText;
                
                

                if (resp.startsWith("Sent")) {
                     alert("OTP Sent Check Your Emial !!")
                    document.getElementById("Email").readOnly = true;
                    document.getElementById("exampleInputPassword1").style.display = "none";
                    document.getElementById("exampleInputPassword2").style.display = "none";
                    document.getElementById("spcae_otp").style.display = "inline";
                    document.getElementById("wait").style.display = "none";
                    document.getElementById("Sign").style.display = "inline";
                     document.getElementById("Info_tag").style.display = "inline";
                } else {
                       alert("Error  Please Check Your Email again !!")
                      document.getElementById("OTP").style.display = "inline";
                       document.getElementById("Email").readOnly = false;
                       document.getElementById("wait").style.display = "none";
                      
                }
            } else if (xhr.readyState === 4 && xhr.status !== 200) {
                console.error("Failed to load. Status:", xhr.status);
                
            }
        } catch (error) {
            console.error("Error:", error);
            
        }
    }; 
        document.getElementById("OTP").style.display = "none";;wait
            document.getElementById("wait").style.display = "inline";

    xhr.open("GET", url, true);
    xhr.send();
}

function SIGNUP() {
     
   
      var Username = document.getElementById("Email").value;
    var Pass = document.getElementById("exampleInputPassword1").value;
    var CPass = document.getElementById("exampleInputPassword2").value;
     var OTP_VAL = document.getElementById("OTP_VAL").value;
     
     
    

    

    var url = "SighupD1?Username=" +  Username + "&Pass=" + Pass + "&OTP_VAL=" + OTP_VAL ;
 
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        try {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var resp = xhr.responseText;
               // alert(resp+"resp");

                if (resp.startsWith("Rows")) {
                   
                    alert("Your Account Under Verification !!");
                      window.location.href = "Login.jsp";
                      
                } else if(resp.startsWith("Wrong")) {
                     document.getElementById("OTP_VAL").value = "";
                    alert("Wrong_OTP");
                     
                    
                }else{
                    alert("Unable to enter Data");
                    
                }
            } else if (xhr.readyState === 4 && xhr.status !== 200) {
                console.error("Failed to load. Status:", xhr.status);
                
            }
        } catch (error) {
            console.error("Error:", error);
            
        }
    }; 
         
    xhr.open("GET", url, true);
    xhr.send();
}
// Login 
function Login_btn(){
   
  var Username = document.getElementById("exampleInputEmail1").value;
    var Pass = document.getElementById("exampleInputPassword1").value;
    
   
    var url = "LoginD1?Username=" +  Username + "&Pass=" + Pass ;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        try {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var resp = xhr.responseText;
              

                if (resp.startsWith("Correct")) {
                    
                      window.location.href = "index.jsp";
                      
                } else if (resp.startsWith("NoAccount")){
                    alert("This account is not verified");
                      
                }
                else{
                   
                    alert("Your Account Is Still Under Verification Process !!");
                    
                }
            } else if (xhr.readyState === 4 && xhr.status !== 200) {
                console.error("Failed to load. Status:", xhr.status);
                
            }
        } catch (error) {
            console.error("Error:", error);
            
        }
    }; 
         
    xhr.open("GET", url, true);
    xhr.send();
}
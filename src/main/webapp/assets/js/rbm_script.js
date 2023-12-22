
// Your existing JavaScript code here
// Enhanced logger functions with timestamps
function logMessage(message) {
    var timestamp = getFormattedTimestamp();
    console.log(`[${timestamp}] [INFO] ${message}`);
}

function logError(error) {
    var timestamp = getFormattedTimestamp();
    console.error(`[${timestamp}] [ERROR] ${error}`);
}

// Function to get a formatted timestamp
function getFormattedTimestamp() {
    var now = new Date();
    var timestamp = now.toISOString().replace(/T/, ' ').replace(/\..+/, '');
    return timestamp;
}

function upload() {

    var modalImageInput = document.getElementById("modalImage");
    var botImage = document.getElementById("botImage");

    // Check if a file is selected
    if (modalImageInput.files.length > 0) {
        var file = modalImageInput.files[0];
        var reader = new FileReader();

        reader.onload = function (e) {
            // Update the src attribute of the imag
            botImage.src = e.target.result;
            // Log success
            logMessage("Image uploaded successfully!");
        };

        // Read the file as a data URL
        reader.readAsDataURL(file);
    } else {
        // Log an error with timestamp if no file is selected
        logError("No file selected for upload.");
    }

    // Close the modal
    closeModal();


}



function uploadBanner() {

    var bannermodalImageInput = document.getElementById("bannermodalImage");
    var bannerImage = document.getElementById("bannerImage");

    // Check if a file is selected
    if (bannermodalImageInput.files.length > 0) {
        var file = bannermodalImageInput.files[0];
        var reader = new FileReader();

        reader.onload = function (e) {
            // Update the src attribute of the image
            bannerImage.src = e.target.result;
            // Log success
            logMessage("Banner Image uploaded successfully!");
        };

        // Read the file as a data URL
        reader.readAsDataURL(file);
    } else {
        // Log an error with timestamp if no file is selected
        logError("No file selected for upload.");
    }

    // Close the modal
    closeModal1();
}

function validateForm() {
    // document.getElementById('popup').style.display = 'block';

//    var phoneNumberInputs = document.getElementsByName("primaryPhoneNumber");
//    var phoneNumbers = [];
//
//    for (var i = 0; i < phoneNumberInputs.length; i++) {
//        var phoneNumber = phoneNumberInputs[i].value;
//        if (phoneNumber.trim() === "") {
//            alert("Phone number cannot be empty");
//            return false; // Prevent form submission
//        }
//
//        phoneNumbers.push(phoneNumber);
//    }
    logMessage("Validation started.");


    var emailInputs = document.getElementsByName("emailAddress");
    var emailAddresses = [];

    for (var i = 0; i < emailInputs.length; i++) {
        var emailAddress = emailInputs[i].value;
        emailAddresses.push(emailAddress);
    }


    var websiteInputs = document.getElementsByName("websiteUrl");
    var websiteAddresses = [];

    for (var i = 0; i < websiteInputs.length; i++) {
        var websiteUrl = websiteInputs[i].value;
        websiteAddresses.push(websiteUrl);

    }

    //   var result = upload();


    //  var resultBanner = uploadBanner();

    var developmentPlatform = document.getElementById("developmentPlatform").value;

    if (developmentPlatform.trim() === "") {
        alert("Please select a development platform");
        return false; // Prevent form submission
    }

    var chatbotWebhook = document.getElementById("chatbotwebhook").value;

    if (chatbotWebhook.trim() === "") {
        alert("Chatbot Webhook cannot be empty");
        return false; // Prevent form submission
    }

    var termsOfUseUrl = document.getElementById("urlterm").value;
    if (termsOfUseUrl.trim() === "") {
        alert("Terms of Use URL* cannot be empty");
        return false; // Prevent form submission
    }

    var privacyPolicyUrl = document.getElementById("urlprivacy").value;
    if (privacyPolicyUrl.trim() === "") {
        alert("Privacy Policy URL* cannot be empty");
        return false; // Prevent form submission
    }

    var languagesSupported = document.getElementById("languagessupport").value;
    if (languagesSupported.trim() === "") {
        alert("Languages Supported* cannot be empty");
        return false; // Prevent form submission
    }

    // Bot Name validation
    var botName = document.getElementById("botname").value;
    if (botName.trim() === "") {
        alert("Bot Name cannot be empty");
        return false; // Prevent form submission
    }

    // Bot Message Type validation
    var botMessageTypeInputs = document.getElementsByName("botMessageType");
    var selectedBotMessageTypes = [];

    for (var i = 0; i < botMessageTypeInputs.length; i++) {
        if (botMessageTypeInputs[i].checked) {
            selectedBotMessageTypes.push(botMessageTypeInputs[i].value);
        }
    }

    if (selectedBotMessageTypes.length === 0) {
        alert("Please select at least one Bot Message Type");
        return false; // Prevent form submission
    }

    // Brand Name validation
    var brandName = document.getElementById("brandName").value;
    if (brandName.trim() === "") {
        alert("Brand Name cannot be empty");
        return false; // Prevent form submission
    }

    // Brand Name character count validation
    var charCountBrand = 100 - brandName.length;
    if (charCountBrand < 0) {
        alert("Brand Name should not exceed 100 characters");
        return false; // Prevent form submission
    }

    // Short Description validation
    var shortDescription = document.getElementById("shortdescription").value;
    if (shortDescription.trim() === "") {
        alert("Short Description cannot be empty");
        return false; // Prevent form submission favcolors
    }

    var favcolors = document.getElementById("favcolors").value;
    if (favcolors.trim() === "") {
        alert("color cannot be empty");
        return false; // Prevent form submission favcolors
    }

    // Short Description character count validation
    var charCountShortDescription = 100 - shortDescription.length;
    if (charCountShortDescription < 0) {
        alert("Short Description should not exceed 100 characters");
        return false; // Prevent form submission
    }


//    var modalImage = document.getElementById("modalImage");
//    var websiteInput = document.getElementById("websiteInput");
//
//    if (!modalImage.files.length && websiteInput.value.trim() === "") {
//        alert("Bot Logo not uploaded. Please select an image");
//        return false;
//    }

    var botfileInput = document.getElementById('modalImage');

    // Check if the file is selected
    if (botfileInput.value === '') {
        // File is not selected, show an alert
        alert('Please select bot logo image file.');
        return false; // Prevent form submission
    }

//    var modalImage1 = document.getElementById("bannermodalImage");
//    var websiteInput1 = document.getElementById("bannerwebsiteInput");
//
//    if (!modalImage1.files.length && websiteInput1.value.trim() === "") {
//        alert("Banner Image not uploaded. Please select an image");
//        return false;
//    }

    var bannerfileInput = document.getElementById('bannermodalImage');

    // Check if the file is selected
    if (bannerfileInput.value === '') {
        // File is not selected, show an alert
        alert('Please select banner image file.');
        return false; // Prevent form submission
    }




    // Display all form data in the alert botimagesss
    //                alert("Phone numbers entered: " + phoneNumbers.join(", ") +
    //                        "\nEmail addresses entered: " + emailAddresses.join(", ") +
    //                        "\nDevelopment Platform: " + developmentPlatform +
    //                        "\nChatbot Webhook: " + chatbotWebhook +
    //                        "\nTerms of Use URL*: " + termsOfUseUrl +
    //                        "\nPrivacy Policy URL*: " + privacyPolicyUrl +
    //                        "\nLanguages Supported*: " + languagesSupported +
    //                        "\nBot Name: " + botName +
    //                        "\nBot Message Types: " + selectedBotMessageTypes +
    //                        "\nBrand Name: " + brandName +
    //                        "\nWebsite URLs: " + websiteAddresses.join(", ") +
    //                        "\ncolors Image: " + favcolors +
    //                        "\nShort Description: " + shortDescription);
    //  alert("Form Submitted Sucessfully:");
    logMessage("Validation completed successfully.");

    // Allow form submission favcolors
    return true;
}









//function updateCharCountphone(input) {
//    var maxLength = 25; // Update this to the actual max length if needed
//    var charCount = maxLength - input.value.length;
//
//    // Find the corresponding charCount div
//    var charCountDiv = findCharCountDiv(input);
//
//    // Display the character count
//    charCountDiv.textContent = charCount + " characters left";
//}
//
//function findCharCountDiv(input) {
//    // Traverse up the DOM to find the parent container with the charCount class
//    var parentContainer = input.closest('.col-md-4');
//
//    // Find the charCount div within the parent container
//    var charCountDiv = parentContainer.querySelector('.chter');
//
//    return charCountDiv;
//}




function addPhoneNumber() {

    try {
        var phoneNumbersContainer = document.getElementById("phoneNumbersContainer");

        // Create a new form for an additional phone number
        var newPhoneNumberForm = document.createElement("div");
        newPhoneNumberForm.classList.add("row");

        // Add country select
        var countrySelectDiv = document.createElement("div");
        countrySelectDiv.classList.add("col-md-18", "mt-10");
        countrySelectDiv.innerHTML = '<div class="form-group"><select class="form-select" aria-label="Default select example"><option value="AF">India</option></select></div>';
        newPhoneNumberForm.appendChild(countrySelectDiv);

        // Add input for primary phone number
        var primaryPhoneNumberInputDiv = document.createElement("div");
        primaryPhoneNumberInputDiv.classList.add("col-md-4");
        primaryPhoneNumberInputDiv.innerHTML = '<label for="exampleInputPassword1"><i class="fa fa-mobile" aria-hidden="true"></i>Other phone number*</label><input type="number" class="form-control" name="primaryPhoneNumber" aria-describedby="emailHelp" placeholder="+91" required>';
        newPhoneNumberForm.appendChild(primaryPhoneNumberInputDiv);

        // Add label for primary phone number
        var primaryPhoneNumberLabelDiv = document.createElement("div");
        primaryPhoneNumberLabelDiv.classList.add("col-md-4");
        primaryPhoneNumberLabelDiv.innerHTML = '<label for="exampleInputPassword1">Label for other phone number*</label><input type="number" class="form-control" name="primaryPhoneNumber" aria-describedby="emailHelp" placeholder="+91" required>';
        newPhoneNumberForm.appendChild(primaryPhoneNumberLabelDiv);

        // Add delete button
        var deleteButtonDiv = document.createElement("div");
        deleteButtonDiv.classList.add("col-md-12"); // Make it full width to appear below the other elements
        deleteButtonDiv.innerHTML = '<button type="button" class="deleteButton">Delete</button>';
        deleteButtonDiv.querySelector('.deleteButton').addEventListener('click', function () {
            newPhoneNumberForm.parentNode.removeChild(newPhoneNumberForm);
        });

        // Append the delete button below other elements within newPhoneNumberForm
        newPhoneNumberForm.appendChild(deleteButtonDiv);

        // Append the new form to the container
        phoneNumbersContainer.insertBefore(newPhoneNumberForm, phoneNumbersContainer.lastChild);

        //  updateCharCountphone(primaryPhoneNumberInputDiv.querySelector('input'));
        //  updateCharCountphone(primaryPhoneNumberInputDiv.querySelector('input'));
        logMessage("New phone number added successfully.");
    } catch (error) {
        // Example log error
        logError("Error while adding a new phone number: " + error.message);
    }

}



//                        function removePhoneNumber(button) {
//                            var phoneNumberForm = button.parentElement.parentElement;
//                            phoneNumberForm.parentNode.removeChild(phoneNumberForm);
//                        }




function addEmail() {
    try {

        var emailAddressesContainer = document.getElementById("emailAddressesContainer");

        // Create a new form for an additional email address
        var newEmailForm = document.createElement("div");
        newEmailForm.classList.add("row");

        // Add input for email address
        var emailInputDiv = document.createElement("div");
        emailInputDiv.classList.add("col-md-4");
        emailInputDiv.innerHTML = '<label for="exampleInputPassword1">Other Email ID</label><input type="email" class="form-control" name="emailAddress" aria-describedby="emailHelp" placeholder="@">';
        newEmailForm.appendChild(emailInputDiv);

        // Add label for email address
        var emailLabelDiv = document.createElement("div");
        emailLabelDiv.classList.add("col-md-4");
        emailLabelDiv.innerHTML = '<label for="exampleInputPassword1">Label for other email ID</label><input type="email" class="form-control" name="emailAddress" aria-describedby="emailHelp" placeholder="">';
        newEmailForm.appendChild(emailLabelDiv);

        var deleteButtonDiv = document.createElement("div");
        deleteButtonDiv.classList.add("col-md-12"); // Make it full width to appear below the other elements
        deleteButtonDiv.innerHTML = '<button type="button" class="deleteButton1">Delete</button>';
        deleteButtonDiv.querySelector('.deleteButton1').addEventListener('click', function () {
            newEmailForm.parentNode.removeChild(newEmailForm);
        });

        // Append the delete button below other elements within newPhoneNumberForm
        newEmailForm.appendChild(deleteButtonDiv);


        // Append the new form to the container
        emailAddressesContainer.insertBefore(newEmailForm, emailAddressesContainer.lastChild);
        //   newEmailForm.style.display = 'block';

        logMessage("New email address added successfully.");
    } catch (error) {
        // Example log error
        logError("Error while adding a new email address: " + error.message);
    }
}



function updateCharCountwebsite(input) {
    var maxLength = 25;
    var charCount = maxLength - input.value.length;

    // Find the corresponding charCount div
    var charCountDiv = input.parentElement.nextElementSibling;

    // Display the character count
    charCountDiv.textContent = charCount + " characters left";
}



function addWebsite() {
    try{
    var websiteContainer = document.getElementById("websiteContainer");

    // Create a new form for an additional website
    var newWebsiteForm = document.createElement("div");
    newWebsiteForm.classList.add("row");

    // Add input for website URL
    var websiteInputDiv = document.createElement("div");
    websiteInputDiv.classList.add("col-md-4");
    websiteInputDiv.innerHTML = '<label for="exampleInputPassword1">Other Website URL</label><input type="url" class="form-control" name="websiteUrl" aria-describedby="emailHelp" placeholder="website">';
    newWebsiteForm.appendChild(websiteInputDiv);

    // Add label for website URL
    var websiteLabelDiv = document.createElement("div");
    websiteLabelDiv.classList.add("col-md-4");
    websiteLabelDiv.innerHTML = '<label for="exampleInputPassword1">Label for other website URL</label><input type="url" class="form-control" name="websiteUrl" aria-describedby="emailHelp" placeholder="">';
    newWebsiteForm.appendChild(websiteLabelDiv);

    var deleteButtonDiv = document.createElement("div");
    deleteButtonDiv.classList.add("col-md-12"); // Make it full width to appear below the other elements
    deleteButtonDiv.innerHTML = '<button type="button" class="deleteButton1">Delete</button>';
    deleteButtonDiv.querySelector('.deleteButton1').addEventListener('click', function () {
        newWebsiteForm.parentNode.removeChild(newWebsiteForm);
    });

    // Append the delete button below other elements within newWebsiteForm
    newWebsiteForm.appendChild(deleteButtonDiv);

    // Append the new form to the container
    websiteContainer.insertBefore(newWebsiteForm, websiteContainer.lastChild);
    //  newEmailForm.style.display = 'block';
    
        logMessage("New Website address added successfully.");
    } catch (error) {
        // Example log error
        logError("Error while adding a new website: " + error.message);
    }
}




function updateCharCount(value) {
    var charCount = 40 - value.length;
    document.getElementById("charCount").textContent = charCount + " characters left";
}

function updateBrandCharCount(value) {
    var charCount = 100 - value.length;
    document.getElementById("charCountbrand").textContent = charCount + " characters left";
}

function updateshortdescription(value) {
    var charCount = 100 - value.length;
    document.getElementById("charCountshortdescription").textContent = charCount + " characters left";
}



function updateColor() {
    var colorInput = document.getElementById("favcolor");
    var colorTextInput = document.getElementById("favcolors");

    // Set the color input value to the text input value
    colorInput.value = colorTextInput.value;

    // Update the color value dynamically
    colorInput.style.backgroundColor = colorTextInput.value;
}

function toggleDropdown() {
    var dropdown = document.getElementById("myDropdown");
    var cancelLink = document.getElementById("cancelLink");
    var autofillLink = document.getElementById("autofillLink");

    // Show the dropdown
    dropdown.style.display = "block";
    cancelLink.style.display = "inline";
    autofillLink.style.display = "none";
}

function updateInput() {
    var displayInput = document.getElementById("brandName");
    var dropdown = document.getElementById("myDropdown");
    var cancelLink = document.getElementById("cancelLink");
    var autofillLink = document.getElementById("autofillLink");

    // Set the input value directly based on the selected option
    displayInput.value = dropdown.options[dropdown.selectedIndex].text;

    // Hide the dropdown and cancel link after selecting
    dropdown.style.display = "none";
    cancelLink.style.display = "none";
    autofillLink.style.display = "inline";
}

function cancelSelection() {
    var dropdown = document.getElementById("myDropdown");
    var cancelLink = document.getElementById("cancelLink");
    var autofillLink = document.getElementById("autofillLink");

    // Hide the dropdown and cancel link
    dropdown.style.display = "none";
    cancelLink.style.display = "none";
    autofillLink.style.display = "inline";
}

function goBack() {
    // Redirect to a different page when the "Back" button is clicked
    window.location.href = 'new.jsp';
}




function openModal() {
    // Show the modal
    var modal = document.getElementById("imageModal");
    if (modal) {
        modal.style.display = "block";
    }
}

function closeModal() {
    // Hide the modal
    var modal = document.getElementById("imageModal");
    if (modal) {
        modal.style.display = "none";

    }
}


function openModal1() {
    var modal = document.getElementById('bannerimageModal');
    modal.style.display = 'block';
}

function closeModal1() {
    document.getElementById('bannerimageModal').style.display = 'none';
    // document.body.style.overflow = ''; // Allow scrolling
}


//document.addEventListener('DOMContentLoaded', function () {
//    var inputs = document.querySelectorAll('.phoneNumber');
//
//    inputs.forEach(function (input) {
//        // Initialize intlTelInput for each input
//        var iti = window.intlTelInput(input, {
//            utilsScript: 'https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js',
//            separateDialCode: true,
//            initialCountry: 'auto',
//        });
//
//        // Manually update flag when input changes
//        input.addEventListener('input', function () {
//            var enteredValue = input.value.trim();
//
//            // If the entered text does not start with '+', assume it's just the country code
//            if (!enteredValue.startsWith('+')) {
//                // Ensure that the entered country code is valid
//                if (iti.getCountryData().some(country => country.dialCode === enteredValue)) {
//                    // Set the country based on the entered code
//                    iti.setCountry(enteredValue.toUpperCase());
//                }
//            }
//        });
//    });
//});
        
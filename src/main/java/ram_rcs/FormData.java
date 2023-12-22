package ram_rcs;


import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FormData implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FormData.class.getName());

    private String[] phoneNumbers;
    private String developmentPlatform;
    private String chatbotWebhook;
    private String[] emailAddresses;
    private String[] websiteAddresses;
    private String termsOfUseUrl;
    private String privacyPolicyUrl;
    private String languagesSupported;
    private String botName;
    private String[] botMessageTypes;
    private String brandName;
    private String shortDescription;
    private String favcolors;

    private String websiteInput;

    private String bannerWebsiteInput;
    //   private String SelectedData;

    private String SelectedData;
    private InputStream inputStreamimage;

    private String messageType;

    private String OTP;
    private String Transactional;
    private String Promotional;
    // Getter

    public String getPromotional() {
        return Promotional;
    }

    // Transactional setter method
    public void setPromotional(String promotional) {
        this.Promotional = promotional;
    }

    public String getTransactional() {
        return Transactional;
    }

    // Transactional setter method
    public void setTransactional(String transactional) {
        this.Transactional = transactional;
    }

    public String getOTP() {
        return OTP;
    }

    // Setter method
    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getMessageType() {
        return messageType;
    }

    // Setter
    public void setMessageType(String messageType) {
        this.messageType = messageType;
        LOGGER.log(Level.INFO, "Message type set to: {0}", messageType);

    }

    public InputStream getInputStreamimage() {
        return inputStreamimage;
    }

    public void setInputStreamimage(InputStream inputStreamimage) {
        this.inputStreamimage = inputStreamimage;
        LOGGER.info("InputStream image set.");

    }

    // Existing methods...
    public String getSelectedData() {
        return SelectedData;
    }

    public void setSelectedData(String selectedData) {
        SelectedData = selectedData;
        LOGGER.log(Level.INFO, "Selected data set to: {0}", selectedData);

    }

    private String SelectedData1;

    // Existing methods...
    public String getSelectedData1() {
        return SelectedData1;
    }

    public void setSelectedData1(String selectedData1) {
        SelectedData1 = selectedData1;
    }

    // Getter and setter methods for banner image fields...
    public String getBannerWebsiteInput() {
        return bannerWebsiteInput;
    }

    public void setBannerWebsiteInput(String bannerWebsiteInput) {
        this.bannerWebsiteInput = bannerWebsiteInput;
    }

    public String getWebsiteInput() {
        return websiteInput;
    }

    public void setWebsiteInput(String websiteInput) {
        this.websiteInput = websiteInput;
    }
    // Add getter and setter methods for each property

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
        LOGGER.log(Level.INFO, "Phone numbers set.");

    }

    public String getDevelopmentPlatform() {
        return developmentPlatform;
    }

    public void setDevelopmentPlatform(String developmentPlatform) {
        this.developmentPlatform = developmentPlatform;
        LOGGER.log(Level.INFO, "Development platform set to: {0}", developmentPlatform);

    }

    public String getChatbotWebhook() {
        return chatbotWebhook;
    }

    public void setChatbotWebhook(String chatbotWebhook) {
        this.chatbotWebhook = chatbotWebhook;
    }

    public String[] getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(String[] emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String[] getWebsiteAddresses() {
        return websiteAddresses;
    }

    public void setWebsiteAddresses(String[] websiteAddresses) {
        this.websiteAddresses = websiteAddresses;
    }

    public String getTermsOfUseUrl() {
        return termsOfUseUrl;
    }

    public void setTermsOfUseUrl(String termsOfUseUrl) {
        this.termsOfUseUrl = termsOfUseUrl;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public String getLanguagesSupported() {
        return languagesSupported;
    }

    public void setLanguagesSupported(String languagesSupported) {
        this.languagesSupported = languagesSupported;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String[] getBotMessageTypes() {
        return botMessageTypes;
    }

    public void setBotMessageTypes(String[] botMessageTypes) {
        this.botMessageTypes = botMessageTypes;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFavcolors() {
        return favcolors;
    }

    public void setFavcolors(String favcolors) {
        this.favcolors = favcolors;
    }
}

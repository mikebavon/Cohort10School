package com.cohort10.model;

import org.apache.commons.lang3.StringUtils;

public class ContactUs extends BaseEntity{

    private String contactEmail;

    private String phoneNumber;

    private String message;

    private boolean messageAvailable;

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMessageAvailable() {
        return StringUtils.isNotBlank(getMessage());
    }

    public void setMessageAvailable(boolean messageAvailable) {
        this.messageAvailable = messageAvailable;
    }
}

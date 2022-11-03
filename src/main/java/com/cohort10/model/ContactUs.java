package com.cohort10.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "contact_us")
public class ContactUs extends BaseEntity{

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String message;

    @Transient
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

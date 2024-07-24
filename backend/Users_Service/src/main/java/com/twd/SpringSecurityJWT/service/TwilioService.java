package com.twd.SpringSecurityJWT.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.logging.Logger;

@Service
public class TwilioService {

    private static final Logger LOGGER = Logger.getLogger(TwilioService.class.getName());

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    @PostConstruct
    private void init() {
        Twilio.init(accountSid, authToken);
    }

    public void sendSms(String toPhoneNumber, String message) {
        try {
            Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), message).create();
            LOGGER.info("OTP sent to " + toPhoneNumber + " successfully.");
        } catch (Exception e) {
            LOGGER.severe("Failed to send OTP to " + toPhoneNumber + ": " + e.getMessage());
        }
    }
}

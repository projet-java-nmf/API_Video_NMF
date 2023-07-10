package com.wcs.Security.services;

public interface EmailService {
    void sendEmail(String toUser, String subject, String body);
}

package com.stubee.rollingdomains.domain.email.model;

public record SendWelcomeEmailEvent(
        String receiverEmail,
        String title,
        String content) {
    public static SendWelcomeEmailEvent create(final String receiverEmail) {
        return new SendWelcomeEmailEvent(receiverEmail, getTitle(), getContent());
    }

    private static String getTitle() {
        return "Welcome";
    }

    private static String getContent() {
        return "Thank you!!!!!!!!";
    }
}
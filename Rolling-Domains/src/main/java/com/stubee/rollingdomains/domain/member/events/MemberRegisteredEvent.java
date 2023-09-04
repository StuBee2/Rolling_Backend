package com.stubee.rollingdomains.domain.member.events;

public record MemberRegisteredEvent(
        String receiverEmail,
        String title,
        String content) {
    public static MemberRegisteredEvent create(final String receiverEmail) {
        return new MemberRegisteredEvent(receiverEmail, getTitle(), getContent());
    }

    private static String getTitle() {
        return "Welcome";
    }

    private static String getContent() {
        return "Thank you!!!!!!!!";
    }
}
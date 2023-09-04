package com.stubee.rollingdomains.domain.company.consts;

public enum CompanyStatus {
    ACCEPTED, PENDING, DECLINED;

    public static CompanyStatus of(boolean isAccepted) {
        return isAccepted ? ACCEPTED : DECLINED;
    }
}
package com.stubee.rollingdomains.domain.member.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    TEMP("ROLE_TEMP"), MEMBER("ROLE_MEMBER"), ADMIN("ROLE_ADMIN");

    private final String key;

}
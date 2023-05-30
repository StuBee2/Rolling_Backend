package com.stubee.rollingcore.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    MEMBER("ROLE_MEMBER"), ADMIN("ROLE_ADMIN");

    private final String key;

}
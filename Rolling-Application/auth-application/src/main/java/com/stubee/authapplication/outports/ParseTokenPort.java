package com.stubee.authapplication.outports;

public interface ParseTokenPort {

    Long getSubjectFromRefreshToken(String refreshToken);

    Long getSubjectFromAccessToken(String accessToken);

}
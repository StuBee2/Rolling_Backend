package rolling.application.auth.outport;

public interface ParseTokenPort {

    Long getSubjectFromRefreshToken(String refreshToken);

    Long getSubjectFromAccessToken(String accessToken);

}
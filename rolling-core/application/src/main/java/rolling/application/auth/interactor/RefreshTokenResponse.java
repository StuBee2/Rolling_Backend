package rolling.application.auth.interactor;

public record RefreshTokenResponse(
        String accessToken) {
    public static RefreshTokenResponse of(final String accessToken) {
        return new RefreshTokenResponse(accessToken);
    }
}
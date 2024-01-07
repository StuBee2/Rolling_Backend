package rolling.domain.company.consts;

public enum CompanyStatus {
    ACCEPTED, PENDING, DECLINED;

    public static CompanyStatus from(boolean isAccepted) {
        return isAccepted ? ACCEPTED : DECLINED;
    }
}
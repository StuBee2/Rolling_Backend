package rolling.domain.company.model;

public record Address(
        String address,
        String etc) {
    public static Address of(final String address, final String etc) {
        return new Address(address, etc);
    }

    public static Address of(final String address) {
        return new Address(address, null);
    }
}
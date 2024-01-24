package rolling.domain.story.model;

import lombok.Builder;

public record CorporationDetails(
        String position,
        String welfare,
        String commuteTime,
        String meal,
        String pros,
        String cons,
        String etc
) {
    @Builder
    public CorporationDetails {
    }
}
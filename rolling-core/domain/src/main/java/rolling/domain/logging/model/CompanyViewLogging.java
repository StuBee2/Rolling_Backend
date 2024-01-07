package rolling.domain.logging.model;

import lombok.Builder;
import rolling.domain.common.error.Assert;
import rolling.domain.company.model.CompanyId;
import rolling.domain.member.model.MemberId;

import java.time.LocalDateTime;

public record CompanyViewLogging(
        Long id,
        MemberId memberId,
        CompanyId companyId,
        Boolean isAnonymous,
        LocalDateTime createdAt) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public CompanyViewLogging(MemberId memberId, CompanyId companyId) {
        this(null, memberId, companyId, isAnonymous(memberId), null);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public CompanyViewLogging {
        Assert.notNull(memberId, "MemberId must not be null");
        Assert.notNull(companyId, "CompanyId must not be null");
        Assert.notNull(isAnonymous, "IsAnonymous must not be null");
    }

    private static boolean isAnonymous(MemberId memberId) {
        return memberId.getId() == -1;
    }
}
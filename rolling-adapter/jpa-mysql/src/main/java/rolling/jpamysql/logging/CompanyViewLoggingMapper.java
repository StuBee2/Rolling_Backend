package rolling.jpamysql.logging;

import rolling.domain.company.model.CompanyId;
import rolling.domain.logging.model.CompanyViewLogging;
import rolling.domain.member.model.MemberId;

abstract class CompanyViewLoggingMapper {

    static CompanyViewLoggingJPAEntity toEntity(final CompanyViewLogging domain) {
        return CompanyViewLoggingJPAEntity.builder()
                .memberId(domain.memberId().getId())
                .companyId(domain.companyId().getId())
                .isAnonymous(domain.isAnonymous())
                .build();
    }

    static CompanyViewLogging toDomain(final CompanyViewLoggingJPAEntity entity) {
        return CompanyViewLogging.WithIdBuilder()
                .id(entity.getId())
                .memberId(MemberId.of(entity.getMemberId()))
                .companyId(CompanyId.of(entity.getCompanyId()))
                .isAnonymous(entity.getIsAnonymous())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
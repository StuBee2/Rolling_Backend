package rolling.jpamysql.logging;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import rolling.jpamysql.common.entity.base.BaseTSIDEntity;

@Entity
@Table(name = "tbl_company_view_logging")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyViewLoggingJPAEntity extends BaseTSIDEntity {

    private Long memberId;

    @NotNull
    private Long companyId;

    @NotNull
    private Boolean isAnonymous;

}
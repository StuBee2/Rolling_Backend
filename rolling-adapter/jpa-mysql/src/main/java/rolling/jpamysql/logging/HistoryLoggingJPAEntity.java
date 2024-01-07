package rolling.jpamysql.logging;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import rolling.jpamysql.common.entity.base.BaseTSIDEntity;

@Entity
@Table(name = "tbl_history_logging")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HistoryLoggingJPAEntity extends BaseTSIDEntity {

    @NotNull
    @Size(max = 250)
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private String module;

    private Long memberId;

    @NotNull
    private Boolean isAnonymous;

}
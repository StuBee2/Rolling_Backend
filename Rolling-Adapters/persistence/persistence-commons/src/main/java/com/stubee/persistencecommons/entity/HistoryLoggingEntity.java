package com.stubee.persistencecommons.entity;

import com.stubee.persistencecommons.entity.base.BaseTSIDEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_history_logging")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HistoryLoggingEntity extends BaseTSIDEntity {

    @NotNull
    @Size(max = 250)
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private String module;

    @NotNull
    private Long memberId;

    @NotNull
    private Boolean isAnonymous;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
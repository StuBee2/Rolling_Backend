package com.stubee.persistencecommons.entity;

import com.stubee.persistencecommons.entity.base.BaseTSIDEntity;
import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_employment")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EmploymentEntity extends BaseTSIDEntity {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long employerId;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
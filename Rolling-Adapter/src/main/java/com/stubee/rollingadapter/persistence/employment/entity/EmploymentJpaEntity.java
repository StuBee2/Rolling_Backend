package com.stubee.rollingadapter.persistence.employment.entity;

import com.stubee.rollingcore.domain.employment.enums.EmploymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_employment")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EmploymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private UUID employeeId;

    @NotNull
    private UUID employerId;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
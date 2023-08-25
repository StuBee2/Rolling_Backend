package com.stubee.persistencecommons.entity;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
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
public class EmploymentEntity {

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
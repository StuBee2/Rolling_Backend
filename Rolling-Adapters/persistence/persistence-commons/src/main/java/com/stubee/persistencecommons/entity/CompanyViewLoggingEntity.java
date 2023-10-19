package com.stubee.persistencecommons.entity;

import com.stubee.persistencecommons.entity.base.BaseTSIDEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_company_view_logging")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyViewLoggingEntity extends BaseTSIDEntity {

    private UUID memberId;

    private UUID companyId;

    private Boolean isAnonymous;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
package rolling.jpamysql.company;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import rolling.domain.company.consts.CompanyStatus;
import rolling.jpamysql.common.entity.base.BaseTSIDEntity;

@Entity
@Table(name = "tbl_company")
@Getter
@SuperBuilder
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyJPAEntity extends BaseTSIDEntity {

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String address;

    private String addressEtc;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @Size(max = 1000)
    private String imgUrl;

    private String logoUrl;

    private Integer logoRGB;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private CompanyStatus companyStatus;

    @NotNull
    private Double totalGrade;

    @NotNull
    private Double salaryAndBenefits;

    @NotNull
    private Double workLifeBalance;

    @NotNull
    private Double organizationalCulture;

    @NotNull
    private Double careerAdvancement;

    @NotNull
    private Long registrantId;

}
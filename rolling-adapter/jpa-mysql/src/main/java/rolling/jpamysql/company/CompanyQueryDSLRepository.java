package rolling.jpamysql.company;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rolling.application.company.interactor.query.CompanyQueryResponse;
import rolling.domain.common.model.PageRequest;
import rolling.domain.company.consts.CompanyStatus;

import java.util.List;

import static rolling.jpamysql.company.QCompanyJPAEntity.*;
import static rolling.jpamysql.member.QMemberJPAEntity.*;

@Repository
@RequiredArgsConstructor
class CompanyQueryDSLRepository {

    private static final int RANK_LIMIT = 10;

    private final JPAQueryFactory jpaQueryFactory;

    CompanyJPAEntity findById(Long id) {
        return jpaQueryFactory
                .selectFrom(companyJPAEntity)
                .where(companyJPAEntity.id.eq(id))
                .fetchOne();
    }

    CompanyQueryResponse findInfoById(Long id) {
        return jpaQueryFactory
                .select(responseProjection())
                .from(companyJPAEntity)
                .innerJoin(memberJPAEntity)
                .on(companyJPAEntity.registrantId.eq(memberJPAEntity.id))
                .where(companyJPAEntity.id.eq(id))
                .fetchOne();
    }

    boolean existsByCompanyId(Long id) {
        return jpaQueryFactory
                .selectOne()
                .from(companyJPAEntity)
                .where(companyJPAEntity.id.eq(id)
                        .and(companyJPAEntity.companyStatus.eq(CompanyStatus.ACCEPTED))
                ) != null;
    }

    boolean existsByCompanyName(String name) {
        return jpaQueryFactory
                .selectOne()
                .from(companyJPAEntity)
                .where(companyJPAEntity.name.eq(name)
                        .and(companyJPAEntity.companyStatus.eq(CompanyStatus.ACCEPTED))
                ) != null;
    }

    List<CompanyJPAEntity> findByNameContaining(String name, PageRequest pageRequest) {
        return findByOptionWithPaging(
                companyJPAEntity.name.contains(name)
                        .and(companyJPAEntity.companyStatus.eq(CompanyStatus.ACCEPTED)),
                pageRequest);
    }

    List<CompanyJPAEntity> findByRegistrantId(Long registrantId, PageRequest pageRequest) {
        return findByOptionWithPaging(companyJPAEntity.registrantId.eq(registrantId), pageRequest);
    }

    private List<CompanyJPAEntity> findByOptionWithPaging(Predicate option, PageRequest pageRequest) {
        return jpaQueryFactory
                .selectFrom(companyJPAEntity)
                .where(option)
                .orderBy(companyJPAEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    List<CompanyJPAEntity> findAll(PageRequest pageRequest) {
        return jpaQueryFactory
                .selectFrom(companyJPAEntity)
                .where(companyJPAEntity.companyStatus.eq(CompanyStatus.ACCEPTED))
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    List<CompanyJPAEntity> findOrderBy(String gradeType) {
        NumberPath<Double> order = switch (gradeType) {
            case "total" -> companyJPAEntity.totalGrade;
            case "salary-benefits" -> companyJPAEntity.salaryAndBenefits;
            case "balance" -> companyJPAEntity.workLifeBalance;
            case "culture" -> companyJPAEntity.organizationalCulture;
            case "career" -> companyJPAEntity.careerAdvancement;
            default -> throw new IllegalArgumentException("Wrong grade type");
        };

        return jpaQueryFactory
                .selectFrom(companyJPAEntity)
                .orderBy(order.desc())
                .limit(RANK_LIMIT)
                .fetch();
    }

    private ConstructorExpression<CompanyQueryResponse> responseProjection() {
        return Projections.constructor(CompanyQueryResponse.class,
                companyJPAEntity.id.stringValue(),
                companyJPAEntity.name,
                companyJPAEntity.address,
                companyJPAEntity.addressEtc,
                companyJPAEntity.description,
                companyJPAEntity.logoUrl,
                companyJPAEntity.logoRGB,
                companyJPAEntity.totalGrade,
                companyJPAEntity.salaryAndBenefits,
                companyJPAEntity.workLifeBalance,
                companyJPAEntity.organizationalCulture,
                companyJPAEntity.careerAdvancement,
                companyJPAEntity.createdAt,
                companyJPAEntity.modifiedAt,

                memberJPAEntity.id.stringValue(),
                memberJPAEntity.nickName,
                memberJPAEntity.socialLoginId,
                memberJPAEntity.imageUrl);
    }

}
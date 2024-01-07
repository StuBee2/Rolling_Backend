package rolling.jpamysql.company;

import org.springframework.data.jpa.repository.JpaRepository;

interface CompanyJpaRepository extends JpaRepository<CompanyJPAEntity, Long> {
}
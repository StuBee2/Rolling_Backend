package rolling.jpamysql.logging;

import org.springframework.data.jpa.repository.JpaRepository;

interface CompanyViewLoggingRepository extends JpaRepository<CompanyViewLoggingJPAEntity, Long> {
}
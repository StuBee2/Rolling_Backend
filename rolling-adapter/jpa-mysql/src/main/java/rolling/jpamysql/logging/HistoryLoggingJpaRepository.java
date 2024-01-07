package rolling.jpamysql.logging;

import org.springframework.data.repository.CrudRepository;

interface HistoryLoggingJpaRepository extends CrudRepository<HistoryLoggingJPAEntity, Long> {
}
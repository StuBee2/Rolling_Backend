package rolling.jpamysql.story;

import org.springframework.data.repository.CrudRepository;

interface StoryJpaRepository extends CrudRepository<StoryJPAEntity, Long> {
}
package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.question.TopicTag;

public interface TopicTagRepository extends JpaRepository<TopicTag, Long> {
}
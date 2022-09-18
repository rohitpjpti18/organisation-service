package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wisepanda.data.entities.question.TopicTag;

import java.util.List;
import java.util.Optional;

public interface TopicTagRepository extends JpaRepository<TopicTag, Long> {
    @Query("FROM TopicTag t WHERE t.tagName = :tagName")
    List<TopicTag> findByTagName(@Param(value="tagName") String tagName);
}
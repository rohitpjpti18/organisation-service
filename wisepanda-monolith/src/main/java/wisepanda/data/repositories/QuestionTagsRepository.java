package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.question.QuestionTags;

public interface QuestionTagsRepository extends JpaRepository<QuestionTags, Long> {
}
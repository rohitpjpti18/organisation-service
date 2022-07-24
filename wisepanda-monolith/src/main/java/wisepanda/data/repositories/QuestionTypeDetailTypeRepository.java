package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.question.QuestionDetailType;

public interface QuestionTypeDetailTypeRepository extends JpaRepository<QuestionDetailType, Long> {
}
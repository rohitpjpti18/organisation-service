package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.question.QuestionTypeDetailType;

public interface QuestionTypeDetailTypeRepository extends JpaRepository<QuestionTypeDetailType, Long> {
}
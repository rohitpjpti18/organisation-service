package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.QuestionTypeMultipleChoice;

public interface QuestionTypeMultipleChoiceRepository extends JpaRepository<QuestionTypeMultipleChoice, Long> {
}
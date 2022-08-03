package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wisepanda.data.entities.question.QuestionMultipleChoice;

public interface QuestionMultipleChoiceRepository extends JpaRepository<QuestionMultipleChoice, Long> {
    @Query("FROM QuestionMultipleChoice qm WHERE qm.question.id = :id")
    QuestionMultipleChoice findByQuestionId(Long id);
}
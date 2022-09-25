package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wisepanda.data.entities.question.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT nextval('s_question_seq')", nativeQuery = true)
    Long getNextValue();
}
package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.question.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
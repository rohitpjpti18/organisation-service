package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
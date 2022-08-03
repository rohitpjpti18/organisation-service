package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
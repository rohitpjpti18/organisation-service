package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.contact.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
}

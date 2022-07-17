package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wisepanda.data.entities.contact.Address;
import wisepanda.data.entities.contact.Email;

import java.util.List;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query(value = "FROM Email e WHERE e.contact.id = :id")
    List<Email> findByContactId(Long id);
}

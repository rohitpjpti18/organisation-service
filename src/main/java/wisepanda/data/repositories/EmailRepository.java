package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wisepanda.data.entities.contact.Email;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query(value = "FROM Email e WHERE e.contact.id = :id")
    List<Email> findByContactId(@Param(value="id") Long id);
    
    @Query(value = "FROM Email e WHERE e.emailAddress = :emailAddress")
    Email findByEmailId(@Param(value="emailAddress") String emailAddress);
}

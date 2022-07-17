package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wisepanda.data.entities.contact.Address;
import wisepanda.data.entities.contact.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    @Query(value = "FROM PhoneNumber ph WHERE ph.contact.id = :id")
    List<PhoneNumber> findByContactId(Long id);
}
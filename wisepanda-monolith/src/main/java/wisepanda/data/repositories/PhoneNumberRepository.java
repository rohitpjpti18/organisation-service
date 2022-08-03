package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wisepanda.data.entities.contact.PhoneNumber;

import java.util.List;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    @Query(value = "FROM PhoneNumber ph WHERE ph.contact.id = :id")
    List<PhoneNumber> findByContactId(Long id);
    @Query(value = "FROM PhoneNumber ph WHERE ph.countryCode.countryCode = :countryCode AND ph.number = :number")
    PhoneNumber findByNumber(String countryCode, String number);
}
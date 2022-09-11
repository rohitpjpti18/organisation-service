package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wisepanda.data.entities.contact.PhoneNumber;

import java.util.List;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    @Query(value = "FROM PhoneNumber ph WHERE ph.contact.id = :id")
    List<PhoneNumber> findByContactId(@Param(value="id") Long id);
    
    @Query(value = "FROM PhoneNumber ph WHERE ph.countryCode.countryCode = :countryCode AND ph.number = :number")
    PhoneNumber findByNumber(@Param(value="countryCode") String countryCode, @Param(value="number") String number);
}
package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.contact.CountryCode;

import java.util.Optional;

public interface CountryCodeRepository extends JpaRepository<CountryCode, Long> {
    Optional<CountryCode> findByCountryCode(String countryCode);
}

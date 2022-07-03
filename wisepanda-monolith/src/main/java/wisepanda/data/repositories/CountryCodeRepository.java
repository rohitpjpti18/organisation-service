package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.School;
import wisepanda.data.entities.contact.CountryCode;

public interface CountryCodeRepository extends JpaRepository<CountryCode, Long> {
}

package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
}
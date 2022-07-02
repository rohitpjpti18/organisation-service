package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.contact.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wisepanda.data.entities.Organisation;
import wisepanda.data.entities.contact.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}

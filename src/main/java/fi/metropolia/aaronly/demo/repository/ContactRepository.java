package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}

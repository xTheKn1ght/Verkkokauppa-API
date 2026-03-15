package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.Contact;
import fi.metropolia.aaronly.demo.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private ContactRepository contactRepository;
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
    public void deleteContactById(Integer id) {
        if(!contactRepository.existsById(id)){
            throw new RuntimeException("Contact not found with id: "+id);
        }
        contactRepository.deleteById(id);
    }
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }
}

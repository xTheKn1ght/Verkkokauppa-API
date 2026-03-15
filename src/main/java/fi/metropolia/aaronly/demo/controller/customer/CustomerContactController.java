package fi.metropolia.aaronly.demo.controller.customer;

import fi.metropolia.aaronly.demo.entity.Contact;
import fi.metropolia.aaronly.demo.repository.ContactRepository;
import fi.metropolia.aaronly.demo.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/contacts")
public class CustomerContactController {
    private final ContactService contactService;
    public  CustomerContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        Contact saved = contactService.createContact(contact);
        return ResponseEntity.ok(saved);
    }
}

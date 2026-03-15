package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.Contact;
import fi.metropolia.aaronly.demo.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/contacts")
public class AdminContactController {
    private final ContactService contactService;
    public AdminContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @GetMapping
    public List<Contact> findAll() {
        return contactService.findAll();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Integer id) {
        try{
            contactService.deleteContactById(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact saved = contactService.createContact(contact);
        return ResponseEntity.ok(saved);
    }
}

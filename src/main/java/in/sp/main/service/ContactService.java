package in.sp.main.service;

import org.springframework.stereotype.Service;
import in.sp.main.entity.ContactMessage;
import in.sp.main.repository.ContactRepository;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveContact(ContactMessage contactMessage) {
        contactRepository.save(contactMessage);
    }
}

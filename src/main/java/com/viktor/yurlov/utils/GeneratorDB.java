package com.viktor.yurlov.utils;

import com.viktor.yurlov.entity.Contact;
import com.viktor.yurlov.service.ContactService;

import java.util.Random;
/**
 * Created by Viktor on 15.08.2017.
 */
public class GeneratorDB {

    private ContactService contactService;
    private final Random RANDOM = new Random();

    public GeneratorDB(ContactService contactService) {
        this.contactService = contactService;
    }

    public void insertElement(int quantityOfElements) {
        for (int i = 0; i < quantityOfElements; i++) {
            contactService.addContact(genegatorOfElement());
        }
    }

    public Contact genegatorOfElement() {
        return new Contact(nameGenerator((RANDOM.nextInt(300) / 20 + 1)));
    }

    public String nameGenerator(int length) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < length; i++) {
            name.append((char) (RANDOM.nextInt(26) + 97));
        }
        return name.toString();
    }
}

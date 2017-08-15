package com.viktor.yurlov.service;

import com.viktor.yurlov.dao.ContactDAO;
import com.viktor.yurlov.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Viktor on 15.08.2017.
 */
@Service
public class ContactService {
    private long startIndex;

    private long limit;

    @Autowired
    private ContactDAO contactDAO;

    public int addContact(Contact contact) {
        return contactDAO.create(contact);
    }

    public List<Contact> getInRange(String filter, long limit, long page) {
        this.limit=limit;
        startIndex=limit*(page-1);
        return select(filter);
    }

    public List<Contact> select(String filter) {
        List<Contact> res = new ArrayList<>();
        Pattern pattern = Pattern.compile(filter);
        long i= contactDAO.findMaxId()/limit+1;
        for (int j=0; j<i; j++) {
            if (searchInRange(pattern, res, j).size()==limit) {
                return res;
            }
        }

        return res;
    }

    private List<Contact> searchInRange(Pattern pattern, List<Contact> res, int j){

        for (Contact contact : contactDAO.selectionFromRange(j * limit+1, (j + 1) * limit)) {
            if (!(pattern.matcher(contact.getName()).matches())) {
                if (startIndex-- < 0) {
                    res.add(contact);
                    if (res.size() == limit) {
                        return res;
                    }
                }

            }
        }
        return res;
        }



}

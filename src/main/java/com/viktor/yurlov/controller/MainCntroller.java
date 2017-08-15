package com.viktor.yurlov.controller;

import com.viktor.yurlov.entity.Contact;
import com.viktor.yurlov.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Viktor on 15.08.2017.
 */
@RestController
@RequestMapping("/hello/contacts")
public class MainCntroller {
    @Autowired
    private ContactService contactService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getPageOfContactsByFilter(@RequestParam(value = "nameFilter") String filter,
                                                   @RequestParam(value = "limit", required = false) Long limit,
                                                   @RequestParam(value = "page", required = false) Long page) {

        if (limit == null) {
            return contactService.getInRange(filter, Long.MAX_VALUE, 1L);
        }
        return contactService.getInRange(filter, limit, page == null ? 1 : page);
    }


}

package com.viktor.yurlov;



import com.viktor.yurlov.entity.Contact;
import com.viktor.yurlov.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created by Viktor on 15.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

public class ContactTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    ContactService contactService;
    private static MockMvc mockMvc;
    @Before
    public  void init() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        contactService.addContact(new Contact("bbb"));
    }
    @Test
    public void testOk() throws Exception {
        mockMvc.perform(get("/hello/contacts?nameFilter=1&limit=10")).andExpect(status().isOk());
        mockMvc.perform(get("/hello/contacts?nameFilter=^[b-z].*$&limit=2&page=2")).andExpect(status().isOk());
        mockMvc.perform(get("/hello/contacts?nameFilter=^[a-z].*$")).andExpect(status().isOk());

    }


    @Test
    public void testNotFound() throws Exception{
        mockMvc.perform(get("/hello/contacts")).andExpect(status().is(400));
    }
    @Test
    public void testJSON() throws Exception{
        mockMvc.perform(get("/hello/contacts?nameFilter=^[b-z].*$&limit=2")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


}

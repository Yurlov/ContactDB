package com.viktor.yurlov;


import com.viktor.yurlov.service.ContactService;

import com.viktor.yurlov.utils.GeneratorDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by Viktor on 15.08.2017.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private ContactService contactService;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {

        new GeneratorDB(contactService).insertElement(1_000_000);

    }
}

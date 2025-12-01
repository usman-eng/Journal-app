package com.example.demo.service;

import com.example.demo.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void sendEmail() {
        emailService.sendEmail("email@gmail.com","test email","ap kese hain");
    }
}

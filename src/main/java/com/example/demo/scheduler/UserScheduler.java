package com.example.demo.scheduler;

import com.example.demo.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

//    @Scheduled(cron = "0 0 9 * * SUN")
    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendEmail()
    {
        emailService.sendEmail("email@gmail.com","test email","ap kese hain");
    }
}

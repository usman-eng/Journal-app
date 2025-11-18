package com.example.demo.service;

import com.example.demo.Repository.ClientRepository;
import com.example.demo.entry.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {


    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void Add(){
        assertEquals(4,2+2);
    }

    @Test
    public void findByusername(){
        Client client=clientRepository.findByusername("test 27");
        assertTrue(!client.getJournel().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "1,9,10",
            "2,5,7"
    })
    public void resource(int a, int b, int expected){
        assertEquals(expected,a+b);
    }
}

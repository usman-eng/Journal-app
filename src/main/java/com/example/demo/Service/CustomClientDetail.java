package com.example.demo.Service;

import com.example.demo.Repository.ClientRepository;
import com.example.demo.entry.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomClientDetail implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client=clientRepository.findByusername(username);
       if(client != null){
           return org.springframework.security.core.userdetails.User.builder()
                   .username(client.getUsername())
                   .password(client.getPassword())
                   .roles(client.getRoles().toArray(new String[0]))
                   .build();

       }
       throw new UsernameNotFoundException("username not found"+username);
    }
}

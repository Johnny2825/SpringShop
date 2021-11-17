package com.example.springshop.config.security;

import com.example.springshop.entity.Person;
import com.example.springshop.service.PersonService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonService personService;

    public CustomUserDetailsService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personService.findByLogin(s);
        return new CustomUserDetails(person);
    }
}

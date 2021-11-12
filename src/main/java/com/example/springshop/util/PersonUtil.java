package com.example.springshop.util;

import com.example.springshop.config.security.CustomUserDetails;
import com.example.springshop.entity.Person;
import org.springframework.security.core.context.SecurityContextHolder;

public class PersonUtil {
    public static Person getCurrentPerson() {
        var details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (details instanceof CustomUserDetails) {
            return ((CustomUserDetails) details).getPerson();
        }

        return null;
    }
}

package com.example.demo.config.user;

import com.example.demo.entity.User;
import com.example.demo.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userService.getByPhone(phone);
        return CustomUserDetails.fromUserEntityCustomUserDetails(user);
    }
}

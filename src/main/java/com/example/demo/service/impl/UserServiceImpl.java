package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.api.UserService;
import com.example.demo.service.base.BaseServiceImpl;
import com.example.demo.util.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {
    @Override
    public User getByPhone(String phone) {
        return getRepository().getByPhone(phone).orElseThrow(()->new EntityNotFoundException(User.class,"phone",phone));
    }

    @Override
    public Boolean existByPhone(String phone) {
        return getRepository().existsByPhone(phone);
    }

}

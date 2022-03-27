package com.example.demo.service.api;

import com.example.demo.entity.User;
import com.example.demo.service.base.BaseService;

public interface UserService extends BaseService<User,Long> {
    User getByPhone(String phone);
    Boolean existByPhone(String phone);
}

package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class DynamicDto {
    private Map<String, Object> body;

    public DynamicDto(String name, Object obj) {
        this.body = new HashMap<>();
        this.body.put(name, obj);
    }
    public DynamicDto() {}
}

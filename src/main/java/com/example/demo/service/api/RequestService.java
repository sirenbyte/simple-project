package com.example.demo.service.api;

import com.example.demo.dto.DynamicDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.entity.Request;
import com.example.demo.service.base.BaseService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface RequestService extends BaseService<Request, Long> {
    void saved(RequestDto requestDto);
    DynamicDto getOstatok(Long id);
    DynamicDto getPayment(Long id);
    DynamicDto getNextAmount(Long id);
    DynamicDto getNextDate(Long id);
    DynamicDto getEarlyAmount(Long id);

}

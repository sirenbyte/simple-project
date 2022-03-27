package com.example.demo.service.impl;

import com.example.demo.dto.DynamicDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.entity.Request;
import com.example.demo.repository.RequestRepository;
import com.example.demo.service.api.RequestService;
import com.example.demo.service.api.UserService;
import com.example.demo.service.base.BaseServiceImpl;
import com.example.demo.util.EntityNotFoundException;
import com.example.demo.util.UserContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestServiceImpl extends BaseServiceImpl<Request, Long, RequestRepository> implements RequestService {

    private final UserService userService;
    private final EntityManager entityManager;

    @Override
    public void saved(RequestDto requestDto) {
        Optional.ofNullable(requestDto)
                .map(this::mapDtoToRequest)
                .ifPresent(it -> getRepository().save(it));
    }

    @Override
    public DynamicDto getOstatok(Long id) {
        return new DynamicDto("ostatok", getRepository().getSumOstatok(id).orElseThrow(()->new EntityNotFoundException(Request.class,"user",id)));
    }

    @Override
    public DynamicDto getPayment(Long id) {
        return new DynamicDto("payment", getRepository().getPayment(id));
    }

    @Override
    public DynamicDto getNextAmount(Long id) {
        return new DynamicDto("nextAmount",
                (convertToBigDecimal(getOstatok(id).getBody().get("ostatok"))
                        .divide(convertToBigDecimal(getPayment(id).getBody().get("payment")), RoundingMode.HALF_UP)));
    }

    private BigDecimal convertToBigDecimal(Object object) {
        return new BigDecimal(String.valueOf(object));
    }

    @Override
    public DynamicDto getNextDate(Long id) {
        return new DynamicDto("nextDate", getRepository().getDates(id).stream()
                .map(Timestamp::toLocalDateTime)
                .collect(Collectors.toList()));
    }

    @Override
    public DynamicDto getEarlyAmount(Long id) {
        return new DynamicDto("earlyAmount",
                getRepository().getByUser(userService.findOrThrowNotFound(id)).stream()
                        .map(it -> BigDecimal.valueOf((it.getDateClose().getMonthValue() - it.getDateOpen().getMonthValue()) / it.getProcent().doubleValue() * it.getPeriod()))
                        .collect(Collectors.toList()));
    }

    private Request mapDtoToRequest(RequestDto dto) {
        Request request = new Request();
        request.setAmount(dto.getAmount());
        request.setPeriod(dto.getPeriod());
        request.setProcent(dto.getProcent());
        request.setUser(userService.getByPhone(UserContext.getUserPhone()));
        request.setDateOpen(LocalDateTime.now());
        request.setDateClose(LocalDateTime.now().plusMonths(dto.getPeriod()));
        return request;
    }
}
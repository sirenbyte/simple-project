package com.example.demo.controller;

import com.example.demo.dto.DynamicDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.service.api.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Api(value = "API для бизнес процессов")
@RestController
@AllArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @ApiOperation(value = "Создания заявки", notes = "Создания заявки")
    @PostMapping("/request")
    public ResponseEntity<Void> createRequest(@RequestBody @Valid RequestDto requestDto){
        requestService.saved(requestDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Сумма остатка на текущую дату", notes = "Сумма остатка на текущую дату")
    @GetMapping("/ostatok/{id}")
    public ResponseEntity<DynamicDto> getOstatok(@PathVariable Long id){
        return ResponseEntity.ok(requestService.getOstatok(id));
    }

    @ApiOperation(value = "Количество оставшихся платежей на текущую дату", notes = "Количество оставшихся платежей на текущую дату")
    @GetMapping("/payment/{id}")
    public ResponseEntity<DynamicDto> getPayment(@PathVariable Long id){
        return ResponseEntity.ok(requestService.getPayment(id));
    }

    @ApiOperation(value = "Следующая сумма погашения", notes = "Следующая сумма погашения")
    @GetMapping("/amount/{id}")
    public ResponseEntity<DynamicDto> getNextAmount(@PathVariable Long id){
        return ResponseEntity.ok(requestService.getNextAmount(id));
    }

    @ApiOperation(value = "Дата следующего погашения", notes = "Дата следующего погашения")
    @GetMapping("/date/{id}")
    public ResponseEntity<DynamicDto> getNextDate(@PathVariable Long id){
        return ResponseEntity.ok(requestService.getNextDate(id));
    }

    @ApiOperation(value = "Сумма досрочного погашения", notes = "Сумма досрочного погашения")
    @GetMapping("/early-payment/{id}")
    public ResponseEntity<DynamicDto> getEarlyAmount(@PathVariable Long id){
        return ResponseEntity.ok(requestService.getEarlyAmount(id));
    }
}

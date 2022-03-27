package com.example.demo.entity;

import com.example.demo.entity.id.IdentifiedId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "request")
public class Request implements IdentifiedId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "request_seq")
    @SequenceGenerator(name = "request_seq",allocationSize = 1,sequenceName = "request_seq")
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "period")
    private Integer period;

    @Column(name = "procent")
    private BigDecimal procent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id",updatable = false)
    private User user;

    @Column(name = "date_open")
    private LocalDateTime dateOpen;

    @Column(name = "date_close")
    private LocalDateTime dateClose;
}

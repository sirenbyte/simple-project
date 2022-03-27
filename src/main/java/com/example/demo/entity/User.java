package com.example.demo.entity;

import com.example.demo.entity.id.IdentifiedId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements IdentifiedId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "users_seq")
    @SequenceGenerator(name = "users_seq",allocationSize = 1,sequenceName = "users_seq")
    private Long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "iin")
    private String iin;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;
}

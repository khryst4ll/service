package org.example.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "dat_address")
@Getter
@RequiredArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dat_addr_id")
    private Long userId;

    @Column(name = "dat_addr_ddtaddress")
    @Setter(AccessLevel.PUBLIC)
    private String pureAddress;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
}

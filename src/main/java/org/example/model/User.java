package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dat_user")
@Getter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dat_user_id")
    private Long userId;

    @Column(name = "dat_user_str_id")
    @Setter
    private String userStringId;

    @Column(name = "dat_user_address")
    @Setter(AccessLevel.PUBLIC)
    private String userAddress;

    @Column(name = "dat_user_ddtaddress")
    @Setter(AccessLevel.PUBLIC)
    private String ddtAddress;

    @Column(name = "dat_user_status")
    @Setter(AccessLevel.PUBLIC)
    private Boolean userStatus;
}

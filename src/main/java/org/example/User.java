package org.example;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dat_user")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dat_user_id")
    private Long userId;

    @Column(name = "dat_user_str_id")
    private String userStringId;

    @Column(name = "dat_user_address")
    @Setter(AccessLevel.PROTECTED)
    private String userAddress;

    @Column(name = "dat_user_ddtaddress")
    @Setter(AccessLevel.PROTECTED)
    private String ddtAddress;

    @Column(name = "dat_user_status")
    @Setter(AccessLevel.PROTECTED)
    private Boolean userStatus;

    public User() {}

    public User(String userStringId, String userAddress, String ddtAddress, Boolean userStatus) {
        this.userStringId = userStringId;
        this.userAddress = userAddress;
        this.userStatus = userStatus;
        this.ddtAddress = ddtAddress;
    }
}

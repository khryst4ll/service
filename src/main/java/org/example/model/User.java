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

    @Column(name = "dat_user_status")
    @Setter(AccessLevel.PUBLIC)
    private Boolean userStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dat_user_dat_addr_id", referencedColumnName = "dat_addr_id")
    @Setter
    private Address address;
}

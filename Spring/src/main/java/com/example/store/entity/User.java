package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    private Boolean is_active; // подтвердил ли почту

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="role_id")
    private Role role;

}

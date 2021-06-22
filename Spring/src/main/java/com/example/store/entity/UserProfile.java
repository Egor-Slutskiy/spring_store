package com.example.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private String city;
    private String street;
    private String house;
    private String floor;
    private String flat;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userProfile")
    private User user;

}

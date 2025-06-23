package com.restaurant.api.domain.person;

import com.restaurant.api.domain.person.dto.CreatePersonDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "Person")
@Table(name = "people")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private PersonRoleENUM role;
    private String username;
    private String email;
    private String password;

    public PersonEntity(String name, String username, String email, String password, PersonRoleENUM role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

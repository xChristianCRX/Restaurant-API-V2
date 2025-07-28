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

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(PersonRoleENUM role) {
        this.role = role;
    }
}

package com.restaurant.api.domain.person;

import com.restaurant.api.domain.person.dto.CreatePersonDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "people")
@Entity(name = "Person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PersonRoleENUM role;

    private String username;

    private String email;

    private String password;

    public PersonEntity(CreatePersonDTO data){
        this.name = data.name();
        this.role = data.role();
        this.username = data.username();
        this.email = data.email();
        this.password = data.password();
    }

}

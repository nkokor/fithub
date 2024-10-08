package com.fithub.services.membership.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(updatable = false)
    private String uuid;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "The first name must not be blank.")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "The last name must not be blank.")
    private String lastName;

    @OneToOne(mappedBy = "user")
    private ClientEntity client;

    @OneToOne(mappedBy = "user")
    private CoachEntity coach;

}
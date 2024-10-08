package com.fithub.services.training.dao.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "coaches")
public class CoachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(name = "client_capacity")
    @Min(value = 1, message = "The maximum number of client must be at least 1.")
    @Max(value = 25, message = "The maximum number of client must be up to 25.")
    @NotNull(message = "The maximum number of client must be specified.")
    private Integer clientCapacity;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<ClientEntity> clients;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<ProgressionStatsEntity> progressionStats;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointments;

}
package com.footballzone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String surname;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    private String gender;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    private String birthdate;

    @ManyToOne
    @JoinColumn(columnDefinition = "INT NOT NULL", name = "team_id")
    @JsonBackReference
    private Team team;

    @ManyToOne
    @JoinColumn(columnDefinition = "INT NOT NULL", name = "country_id")
    @JsonBackReference
    private Country country;

    public Player() {
    }

    public Player(String name, String surname, String gender, String birthdate, Team team, Country country) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.team = team;
        this.country = country;
    }
}


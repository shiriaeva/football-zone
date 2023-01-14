package com.footballzone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.footballzone.util.Trimmable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;


@Getter
@Setter
public class PlayerDto implements Trimmable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Nullable
    private Long id;

    private String name;

    private String surname;

    private String gender;

    private String birthdate;

    private String teamName;

    private String countryName;

    public PlayerDto() {
    }

    public PlayerDto(@Nullable Long id, String name, String surname, String gender, String birthdate, String teamName, String countryName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.teamName = teamName;
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", teamName='" + teamName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}

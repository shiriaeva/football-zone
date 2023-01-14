package com.footballzone.service;

import com.footballzone.dto.PlayerDto;
import com.footballzone.entity.Team;
import com.footballzone.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team getTeam(PlayerDto playerDto){

        if (teamRepository.existsByName(playerDto.getTeamName())) {
            return teamRepository.getTeamByName(playerDto.getTeamName());
        }
        else{
            Team newTeam = new Team(playerDto.getTeamName());
            teamRepository.save(newTeam);
            return newTeam;
        }
    }
}

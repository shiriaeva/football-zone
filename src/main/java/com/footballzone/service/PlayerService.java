package com.footballzone.service;

import com.footballzone.entity.Player;
import com.footballzone.dto.PlayerDto;
import com.footballzone.repository.CountryRepository;
import com.footballzone.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    @Autowired
    private final TeamService teamService;

    @Autowired
    private final CountryRepository countryRepository;


    public PlayerService(PlayerRepository playerRepository,
                         TeamService teamService,
                         CountryRepository countryRepository) {

        this.playerRepository = playerRepository;
        this.teamService = teamService;
        this.countryRepository = countryRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public void save(PlayerDto playerDto) {
        try {
            if(Objects.nonNull(playerDto)) {
                playerDto.trim();
                if (playerDto.getId() == null) {
                    Player player = new Player(
                            playerDto.getName(),
                            playerDto.getSurname(),
                            playerDto.getGender(),
                            playerDto.getBirthdate(),
                            teamService.getTeam(playerDto),
                            countryRepository.getCountryByName(playerDto.getCountryName())
                    );

                    playerRepository.save(player);

                } else if (playerRepository.existsById(playerDto.getId())) {
                    update(playerDto);
                } else {
                    throw new RuntimeException("Player not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(PlayerDto playerDto) {
        Player playerToUpdate = getById(playerDto.getId());
        playerToUpdate.setName(playerDto.getName());
        playerToUpdate.setSurname(playerDto.getSurname());
        playerToUpdate.setGender(playerDto.getGender());
        playerToUpdate.setBirthdate(playerDto.getBirthdate());
        playerToUpdate.setTeam(teamService.getTeam(playerDto));
        playerToUpdate.setCountry(countryRepository
                .getCountryByName(playerDto.getCountryName()));
        playerRepository.save(playerToUpdate);
    }

    public Player getById(Long id) {
        Player player = null;
        if (Objects.nonNull(id)) {
            Optional<Player> optionalPlayer = playerRepository.findById(id);
            if(optionalPlayer.isPresent()){
                player = optionalPlayer.get();
            }else{
                throw new RuntimeException("Player not found with the id: "+ id);
            }
        }
        return player;
    }

    public void deleteById(Long id) {
        if(Objects.nonNull(id)){
            playerRepository.deleteById(id);
        }
    }
}

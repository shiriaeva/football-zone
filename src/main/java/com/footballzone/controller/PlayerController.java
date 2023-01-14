package com.footballzone.controller;

import com.footballzone.entity.Player;
import com.footballzone.dto.PlayerDto;
import com.footballzone.repository.CountryRepository;
import com.footballzone.repository.TeamRepository;
import com.footballzone.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PlayerController {

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final TeamRepository teamRepository;

    @Autowired
    private final CountryRepository countryRepository;

    public PlayerController(PlayerService playerService,
                            TeamRepository teamRepository,
                            CountryRepository countryRepository) {

        this.playerService = playerService;
        this.teamRepository = teamRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("allPlayersList", playerService.getAllPlayers());
        return "index";
    }

    @GetMapping("/add")
    public String addNewPlayer(Model model){
        PlayerDto playerDto = new PlayerDto();
        model.addAttribute("allTeamsList", teamRepository.findAll());
        model.addAttribute("allCountriesList", countryRepository.findAll());
        model.addAttribute("playerDto", playerDto);
        return "addPlayer";
    }

    @PostMapping("/save")
    public String savePlayer(PlayerDto playerDto){
        playerService.save(playerDto);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam long id, Model model){
        Player player = playerService.getById(id);
        PlayerDto playerDto = new PlayerDto(player.getId(), player.getName(), player.getSurname(), player.getGender(),
                player.getBirthdate(), player.getTeam().getName(), player.getCountry().getName());
        model.addAttribute("allTeamsList", teamRepository.findAll());
        model.addAttribute("allCountriesList", countryRepository.findAll());
        model.addAttribute("playerDto", playerDto);
        return "update";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(value = "id") long id){
        playerService.deleteById(id);
        return "redirect:/";
    }

    @MessageMapping("/get-player")
    @SendTo("/topic/send-player")
    public PlayerDto save(PlayerDto playerDto) {
        playerService.save(playerDto);
        return playerDto;
    }

}

package com.example.dslist.controllers;

import com.example.dslist.dto.GameInfo;
import com.example.dslist.services.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/games")
public class GamesController {
    @Autowired
    private GameService service;

    @GetMapping
    public String findAllGames(Model model){
        model.addAttribute("games", service.findAll());
        return "games";
    }

    @GetMapping("/game")
    public String showForm(Model model){
        model.addAttribute("game", new GameInfo());
        return "addGame";
    }

    @PostMapping("/game")
    public String addGame(@ModelAttribute("game") GameInfo gameDTO){
        service.addGame(gameDTO);
        return "redirect:/games";
    }
}

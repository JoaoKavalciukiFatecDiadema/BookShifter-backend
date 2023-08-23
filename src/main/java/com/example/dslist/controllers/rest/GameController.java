/*
package com.example.dslist.controllers.rest;

import com.example.dslist.dto.GameInfo;
import com.example.dslist.dto.MinimalGameInfo;

import com.example.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService service;

    @GetMapping
    public ResponseEntity<List<MinimalGameInfo>> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameInfo> findById(@PathVariable Long id){
        return service.findById(id);
    }
    @PostMapping
    public ResponseEntity<GameInfo> addGame(@RequestBody GameInfo dto)  {
       return service.addGame(dto);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteGame(@PathVariable Long id){
        service.deleteGame(id);
        return "Jogo deletado com sucesso!";
    }
}*/

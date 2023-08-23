package com.example.dslist.controllers.rest;

import com.example.dslist.dto.GameListInfo;
import com.example.dslist.dto.MinimalGameInfo;
import com.example.dslist.projections.GameMinProjection;
import com.example.dslist.services.GameListService;
import com.example.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListController {
    @Autowired
    private GameListService gameListService;
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameListInfo>> findAll(){
        return gameListService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<GameListInfo> findById(@PathVariable Long id){
        return gameListService.findById(id);
    }

    @GetMapping("/{listID}/games")
    public ResponseEntity<List<MinimalGameInfo>> findByList(@PathVariable Long listID){
        return gameService.findByList(listID);
    }

    @PostMapping
    public ResponseEntity<GameListInfo> addGameList(@RequestBody GameListInfo dto){
        return gameListService.addGameList(dto);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<GameListInfo> deleteGameList(@PathVariable Long id){
        return gameListService.deleteGameList(id);
    }
}

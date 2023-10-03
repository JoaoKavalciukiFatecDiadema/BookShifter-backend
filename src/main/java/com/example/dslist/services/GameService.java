package com.example.dslist.services;

import com.example.dslist.dto.GameInfo;
import com.example.dslist.dto.MinimalGameInfo;
import com.example.dslist.entities.Game;
import com.example.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GameService implements com.example.dslist.services.interfaces.GameService {
    @Autowired
    private GameRepository repository;

    @Transactional(readOnly = true)
    public ResponseEntity<GameInfo> findById(Long id){

        Optional<Game> gameInfo = repository.findById(id);
        if(gameInfo.isEmpty()){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Game game = gameInfo.get();
        GameInfo gameDTO = new GameInfo(game);
        return ResponseEntity.ok(gameDTO);
    }
    @Transactional(readOnly = true)
    public List<MinimalGameInfo> findAll(){
        var result = repository.findAll();
        List<MinimalGameInfo> dto = result.stream().map(MinimalGameInfo::new).toList();
        return dto;
    }

    public GameInfo addGame(GameInfo dto) {
        Game newGame = new Game(dto.getTitle(), dto.getPlatforms(), dto.getShortDescription(),
                dto.getLongDescription(), dto.getGenre(), dto.getImageUrl(), dto.getYear(), dto.getScore());
        /*List<Game> allGames = repository.findAll();
        for(Game game : allGames){
            if(game.getTitle() .equalsIgnoreCase(newGame.getTitle())){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }*/
        repository.save(newGame);
        return new GameInfo(newGame);
    }

    public ResponseEntity<GameInfo> deleteGame(Long id){
        Optional<Game> gameInfo = repository.findById(id);
        if(gameInfo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Game game = gameInfo.get();

        GameInfo dto = new GameInfo(game);
        repository.delete(game);
        return  ResponseEntity.ok(dto);
    }
}

package com.example.dslist.services;

import com.example.dslist.dto.GameInfo;
import com.example.dslist.dto.GameListInfo;
import com.example.dslist.entities.GameList;
import com.example.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GameListService {

    @Autowired
    private GameListRepository repository;

    @Transactional(readOnly = true)
    public ResponseEntity<List<GameListInfo>> findAll(){
        List<GameList> gameLists = repository.findAll();
        List<GameListInfo> gameListDTO = gameLists.stream().map(GameListInfo::new).toList();
        return ResponseEntity.ok(gameListDTO);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<GameListInfo> findById(Long id){
        Optional<GameList> gameList = repository.findById(id);
        if(gameList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        GameListInfo gameListDTO = new GameListInfo(gameList.get());
        return ResponseEntity.ok(gameListDTO);
    }

    public ResponseEntity<GameListInfo> addGameList(GameListInfo gameListDTO){
        GameList newGameList = new GameList(gameListDTO.getName());
        List<GameList> allGamesList = repository.findAll();

        for(GameList gameList : allGamesList){
            if(gameList.getName().equals(newGameList.getName())){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }

        repository.save(newGameList);
        return new ResponseEntity<>(gameListDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<GameListInfo> deleteGameList(Long id){
        Optional<GameList> gameListInfo = repository.findById(id);
        if(gameListInfo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        GameList gameList = gameListInfo.get();
        GameListInfo dto = new GameListInfo(gameList);
        repository.delete(gameList);
        return ResponseEntity.ok(dto);
    }
}

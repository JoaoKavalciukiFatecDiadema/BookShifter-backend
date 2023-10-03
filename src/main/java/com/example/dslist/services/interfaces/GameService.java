package com.example.dslist.services.interfaces;

import com.example.dslist.dto.GameInfo;
import com.example.dslist.dto.MinimalGameInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GameService {
    ResponseEntity<GameInfo> findById(Long id);

    List<MinimalGameInfo> findAll();

    GameInfo addGame(GameInfo dto);

    ResponseEntity<GameInfo> deleteGame(Long id);


}

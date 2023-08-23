package com.example.dslist.dto;

import com.example.dslist.entities.GameList;

public class GameListInfo {
    private Long id;
    private String name;

    public GameListInfo(){
    }
    public GameListInfo(GameList list){
        this.id = list.getId();
        this.name = list.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

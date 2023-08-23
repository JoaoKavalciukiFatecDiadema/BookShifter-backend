package com.example.dslist.dto;

import com.example.dslist.entities.Game;

public class GameInfo {
    private Long id;
    private String title;
    private String platforms;
    private String shortDescription;
    private String longDescription;
    private String genre;
    private String imageUrl;
    private Integer year;
    private Double score;

    public GameInfo(){
    }

    public GameInfo(Game game){
        this.id = game.getId();
        this.title = game.getTitle();
        this.platforms = game.getPlatforms();
        this.shortDescription = game.getShortDescription();
        this.longDescription = game.getLongDescription();
        this.genre = game.getGenre();
        this.imageUrl = game.getImageUrl();
        this.year = game.getYear();
        this.score = game.getScore();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPlatforms() {
        return platforms;
    }


    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getGenre() {
        return genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getYear() {
        return year;
    }
    public Double getScore() {
        return score;
    }
}

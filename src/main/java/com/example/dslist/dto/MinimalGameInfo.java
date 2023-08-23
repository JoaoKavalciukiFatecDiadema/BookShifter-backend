package com.example.dslist.dto;

import com.example.dslist.entities.Game;
import com.example.dslist.projections.GameMinProjection;

public class MinimalGameInfo {
    private Long id;
    private String title;
    private String imageUrl;
    private String shortDescription;
    private Integer year;

    public MinimalGameInfo(){
    }

    public MinimalGameInfo(Game entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.imageUrl = entity.getImageUrl();
        this.shortDescription = entity.getShortDescription();
        this.year = entity.getYear();
    }

    public MinimalGameInfo(GameMinProjection projection){
        this.id = projection.getId();
        this.title = projection.getTitle();
        this.imageUrl = projection.getImageUrl();
        this.shortDescription = projection.getShortDescription();
        this.year = projection.getGameYear();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Integer getYear() {
        return year;
    }
}

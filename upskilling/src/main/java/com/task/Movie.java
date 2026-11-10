package com.task;
import java.time.LocalDate;


public class Movie implements watchable {
    private int id;
    private String title;
    private String genre;
    private Double rating;
    private Boolean watched;
    private LocalDate addedDate;

    
    public Movie() {
    }

    public Movie(int id, String title, String genre, Double rating, Boolean watched, LocalDate addedDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.watched = watched;
        this.addedDate = addedDate;
    }
    public int getId() {
        return id;
    }
    @Override
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
    @Override
    public Double getRating() {
        return rating;
    }

    @Override
    public Boolean getWatched() {
        return watched;
    }


    public LocalDate getAddedDate() {
        return addedDate;
    }

    @Override
    public void setwatched(Boolean watched) {
        this.watched = watched;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", watched=" + watched +
                ", addedDate=" + addedDate +
                '}';
    }



}


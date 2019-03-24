package com.shaheen.msr.repository;

import com.shaheen.msr.model.Movie;

import java.util.List;
import java.util.Optional;

public interface DataRepository {
    Optional<Movie> getMovie(String movieName,int releaseYear);
    List<Movie> getAllMovies();
}

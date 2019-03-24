package com.shaheen.msr.service;

import com.shaheen.msr.model.Movie;
import com.shaheen.msr.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InitService {
    private final DataRepository dataRepository;

    public InitService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }
    @PostConstruct
    public void checkDynamodbData(){
       // getMovie();
        getMovieList();


    }

    private void getMovieList() {
        List<Movie> movieList = dataRepository.getAllMovies();
        movieList.forEach(movie -> {
            log.info("Movie : {}",movie.toString());
        });
    }

    private void getMovie(){
        Optional<Movie> optionalMovie = dataRepository.getMovie("Thor",2011);
        if(optionalMovie.isPresent()){
            log.info("Movie Found : {}",optionalMovie.get().toString());
        }
    }
}

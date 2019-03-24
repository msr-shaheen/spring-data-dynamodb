package com.shaheen.msr.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.shaheen.msr.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class DataRepositoryImpl implements DataRepository{

    private final DynamoDBRepository dynamoDBRepository;

    public DataRepositoryImpl(DynamoDBRepository dynamoDBRepository) {
        this.dynamoDBRepository = dynamoDBRepository;
    }


    @Override
    public Optional<Movie> getMovie(String movieName, int releaseYear) {
        return dynamoDBRepository.load(movieName,releaseYear,Movie.class);
    }

    @Override
    public List<Movie> getAllMovies() {
        return dynamoDBRepository.scan(Movie.class,new DynamoDBScanExpression());
    }
}

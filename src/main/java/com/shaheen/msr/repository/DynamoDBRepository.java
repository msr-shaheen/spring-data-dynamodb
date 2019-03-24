package com.shaheen.msr.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class DynamoDBRepository {
    private final DynamoDBMapper mapper;

    public DynamoDBRepository(DynamoDBMapper dynamoDBMapper) {
        this.mapper = dynamoDBMapper;
    }

    public <T> Optional<T> load(Object hashKey, Class<T> tClass) {
        try {
            return Optional.ofNullable(mapper.load(tClass, hashKey));
        } catch (Exception ex) {
            log.error("Data retrieval failed from DynamoDB using hashKey: {}", hashKey, ex);
        }
        return Optional.empty();
    }

    public <T> Optional<T> load(Object hashKey, Object rangeKey, Class<T> tClass) {
        try {
            return Optional.ofNullable(mapper.load(tClass, hashKey, rangeKey));
        } catch (Exception ex) {
            log.error("Data retrieval failed from DynamoDB using hashKey : {} , rangeKey : {}", hashKey, rangeKey, ex);
        }
        return Optional.empty();
    }

    public <T> boolean save(T model) {
        try {
            mapper.save(model);
            return true;
        } catch (Exception ex) {
            log.error("Couldn't save {} to DynamoDB", model, ex);
            throw new RuntimeException("Couldn't save {} to DynamoDB", ex);
        }
    }

    public <T> List<T> scan(Class<T> clazz, DynamoDBScanExpression expression) {
        return mapper.scan(clazz, expression);
    }

    public <T> List<T> parallelScan(Class<T> clazz, DynamoDBScanExpression expression, int numberOfThreads) {

        return mapper.parallelScan(clazz, expression, numberOfThreads);
    }

    public <T> List<T> query(Class<T> clazz, DynamoDBQueryExpression<T> expression) {
        PaginatedQueryList<T> paginatedQueryList = mapper.query(clazz, expression);
        return new ArrayList<>(paginatedQueryList);
    }

    public <T> List<T> queryPage(Class<T> clazz, DynamoDBQueryExpression<T> expression) {
        QueryResultPage<T> queryResultPage = mapper.queryPage(clazz, expression);
        return queryResultPage.getResults();
    }
}

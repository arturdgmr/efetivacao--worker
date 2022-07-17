package com.itau.efetivacaoworker.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.efetivacaoworker.domain.Investments;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class RedisInvestmentRepository implements InvestmentRepository {

    private static final String INVESTMENT_KEY = "INVT";
    private final ReactiveRedisComponent reactiveRedisComponent;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Investments> save(Investments investments) {
        return reactiveRedisComponent.set(INVESTMENT_KEY, investments.getId(), investments).map(i -> investments);
    }

    @Override
    public Mono<Investments> get(String key) {
        return reactiveRedisComponent.get(INVESTMENT_KEY, key)
                .flatMap(d -> Mono.just(objectMapper.convertValue(d, Investments.class)));
    }

    @Override
    public Flux<Investments> getAll() {
        return reactiveRedisComponent.get(INVESTMENT_KEY)
                .map(b -> objectMapper.convertValue(b, Investments.class))
                .collectList()
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Long> delete(String id) {
        return reactiveRedisComponent.remove(INVESTMENT_KEY, id);
    }
}

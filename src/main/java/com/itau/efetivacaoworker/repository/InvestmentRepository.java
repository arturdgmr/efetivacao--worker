package com.itau.efetivacaoworker.repository;

import com.itau.efetivacaoworker.domain.Investments;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InvestmentRepository {
    Mono<Investments> save(Investments investments);
    Mono<Investments> get(String key);
    Flux<Investments> getAll();
    Mono<Long> delete(String id);
}

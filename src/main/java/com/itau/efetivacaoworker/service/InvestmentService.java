package com.itau.efetivacaoworker.service;

import com.itau.efetivacaoworker.domain.Investments;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InvestmentService {
    Mono<Investments> create(Investments investments);
    Flux<Investments> getAll();
    Mono<Investments> getOne(String id);
    Mono<Long> deleteById(String id);
}

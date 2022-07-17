package com.itau.efetivacaoworker.service;

import com.itau.efetivacaoworker.domain.Investments;
import com.itau.efetivacaoworker.repository.RedisInvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService{

    private final RedisInvestmentRepository redisInvestmentRepository;

    @Override
    public Mono<Investments> create(Investments investments) {
        return redisInvestmentRepository.save(investments);
    }

    @Override
    public Flux<Investments> getAll() {
        return redisInvestmentRepository.getAll();
    }

    @Override
    public Mono<Investments> getOne(String id) {
        return redisInvestmentRepository.get(id);
    }

    @Override
    public Mono<Long> deleteById(String id) {
        return redisInvestmentRepository.delete(id);
    }
}

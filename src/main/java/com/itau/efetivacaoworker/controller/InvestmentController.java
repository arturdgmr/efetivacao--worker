package com.itau.efetivacaoworker.controller;

import com.itau.efetivacaoworker.domain.Investments;
import com.itau.efetivacaoworker.service.InvestmentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class InvestmentController {

    private final InvestmentServiceImpl investmentService;

    @PostMapping("/investment")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Investments> addBook(@RequestBody Investments book) {
        return investmentService.create(book);
    }

    @PostMapping("/investment/atualiza/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Investments> atualizaBook(@RequestBody Investments book, @PathVariable(value = "id") String id) {
        return Mono.just(book)
                .flatMap(f -> investmentService.getOne(id))
                .doOnNext(i -> log.info("Investimento recuperado {}", i))
                .map(m -> {
                    m.setName(book.getName());
                    return m;
                })
                .doOnNext(i -> log.info("Investimento convertido {}", i))
                .flatMap(investmentService::create)
                .doOnNext(i -> log.info("Investimento salvo {}", i));
    }

    @GetMapping("/investments")
    public Flux<Investments> getAllBooks() {
        return investmentService.getAll();
    }

    @GetMapping("/investment/{id}")
    public Mono<Investments> getBook(@PathVariable String id) {
        return investmentService.getOne(id);
    }

    @DeleteMapping("/investment/{id}")
    public Mono<Long> deleteBook(@PathVariable String id) {
        return investmentService.deleteById(id);
    }

}

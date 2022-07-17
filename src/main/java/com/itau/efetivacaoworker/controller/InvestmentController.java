package com.itau.efetivacaoworker.controller;

import com.itau.efetivacaoworker.domain.Investments;
import com.itau.efetivacaoworker.service.InvestmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentServiceImpl investmentService;

    @PostMapping("/investment")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Investments> addBook(@RequestBody Investments book) {
        return investmentService.create(book);
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

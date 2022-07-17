package com.itau.efetivacaoworker.repository;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReactiveRedisComponent {

    private final ReactiveRedisOperations<String, Object> redisOperations;

    public ReactiveRedisComponent(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    public Mono<Object> set(String key, String hashKey, Object value) {
        return redisOperations.opsForHash().put(key, hashKey, value).map(b -> value);
    }

    public Flux<Object> get(String key){
        return redisOperations.opsForHash().values(key);
    }

    public Mono<Object> get(String key, Object hashKey) {
        return redisOperations.opsForHash().get(key, hashKey);
    }

    public Mono<Long> remove(String key, Object hashKey) {
        return redisOperations.opsForHash().remove(key, hashKey);
    }

}

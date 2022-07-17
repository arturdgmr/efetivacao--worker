package com.itau.efetivacaoworker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investments {
    private String id;
    private String name;
}

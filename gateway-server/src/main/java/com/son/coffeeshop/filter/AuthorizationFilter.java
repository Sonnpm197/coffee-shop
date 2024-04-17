package com.son.coffeeshop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    public AuthorizationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String bearerToken = request.getHeaders().getFirst("Authorization");
            if (!StringUtils.hasText(bearerToken)) {
//                return unauthorized(exchange, HttpStatus.UNAUTHORIZED);
                request = request.mutate()
                        .header("Authorization", bearerToken)
                        .build();
            }

            logger.info("Authorization filter accept bearer: {}", bearerToken);
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange, HttpStatus httpStatus) {
        serverWebExchange
                .getResponse()
                .setStatusCode(httpStatus);
        DataBuffer buffer = serverWebExchange
                .getResponse()
                .bufferFactory()
                .wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    public static class Config {
    }

}

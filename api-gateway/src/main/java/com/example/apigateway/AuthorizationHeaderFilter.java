package com.example.apigateway;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.config> {

    @Autowired
    Environment env;

    public AuthorizationHeaderFilter(){
        super(config.class);
    }

    public static class config {

    }

    /**
     * check if authorization header present if present validate jwt
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }
            //read authorization header
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer","");

            if(! isJwtValid(jwt)){
                return onError(exchange,"jwt token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, String no_authorization_header, HttpStatus unauthorized) {

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(unauthorized);
        return response.setComplete();
    }

    /**
     * check if jwt toekn is valid
     * @param jwt
     * @return
     */
    private boolean isJwtValid(String jwt){
        boolean returnValue = true;
        String subject = null;
        try {
            subject = Jwts.parser().
                    setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt).getBody().getSubject();
        }catch (Exception ex){
            returnValue=false;
        }
        if(subject ==null || subject.isEmpty()) {
            returnValue=false;
        }
        return returnValue;
    }

}

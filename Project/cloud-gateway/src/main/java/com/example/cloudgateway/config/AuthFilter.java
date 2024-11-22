package com.example.cloudgateway.config;

import com.example.cloudgateway.dtos.UserWithFeaturesReadDto;
import com.netflix.discovery.EurekaClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    private final WebClient.Builder webClientBuilder;

    private final EurekaClient serviceRegistry;

    public AuthFilter(WebClient.Builder webClientBuilder,EurekaClient serviceRegistry) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
        this.serviceRegistry = serviceRegistry;

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            String path = exchange.getRequest().getPath().toString();
            String method = exchange.getRequest().getMethodValue();

            /**Ignore this paths**/
            if(path.contains("/api/auth/login") || (path.equals("/api/auth/users") && method.equals("POST"))
                    || (path.equals("/api/users/clients") && method.equals("POST")
            )){
                return chain.filter(exchange);
            }

            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,  "Miss Token");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = authHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,  "Incorrect authorization structure");
            }

            String url = serviceRegistry.getNextServerFromEureka("AUTH-SERVICE", false).getHomePageUrl();
            if(url == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  "AUTH-SERVICE: unknown service");
            }

            return webClientBuilder.build()
                    .post()
                    .uri(url+"api/auth/token/validateToken?token=" + parts[1] + "&method="+method + "&path="+path)
                    .retrieve().bodyToMono(UserWithFeaturesReadDto.class)
                    .map(userDto -> {
                        System.out.println("Response " + userDto.getId() + " " +  userDto.getUsername());
                        exchange.getRequest()
                                .mutate()
                                .header("X-auth-user-id", String.valueOf(userDto.getId()));
                        return exchange;
                    }).flatMap(chain::filter);
        };


    }

    public static class Config {
        // empty class as I don't need any particular configuration
    }
}
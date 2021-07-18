package ru.itis.apigateway.fiters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * created: 17-07-2021 - 12:59
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class AuthenticationGlobalPreFilter implements GlobalFilter {

    private final RestTemplate restTemplate;

    private final LoadBalancerClient balancerClient;

    private static final String AUTHORIZATION = "Authorization";

    private static final String AUTH_SERVICE = "AUTH-SERVICE";

    final Logger logger =
            LoggerFactory.getLogger(AuthenticationGlobalPreFilter.class);

    public AuthenticationGlobalPreFilter(RestTemplate restTemplate,
                                         @Qualifier("loadBalancerClient")
                                                 LoadBalancerClient balancerClient) {
        this.restTemplate = restTemplate;
        this.balancerClient = balancerClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServiceInstance authService = balancerClient.choose(AUTH_SERVICE);
        String uri = String.format("http://%s:%s/auth/api/1.0/authorize",
                authService.getHost(),
                authService.getPort());

        try {
            String token = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION);
            if (token != null) {
                RequestEntity<Void> request = RequestEntity.get(URI.create(uri))
                        .header(AUTHORIZATION, token)
                        .build();
                ResponseEntity<?> response = restTemplate.exchange(request, Void.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    String newToken = response.getHeaders().getFirst(AUTHORIZATION);
                    exchange.getRequest().mutate()
                            .header(AUTHORIZATION, newToken);

                    logger.info(String.format("Authoriztion header was changed. From [%s] To [%s]", token, newToken));
                }
            }
        } catch (HttpClientErrorException e) {
            // ignore
        }


        return chain.filter(exchange);
    }

}

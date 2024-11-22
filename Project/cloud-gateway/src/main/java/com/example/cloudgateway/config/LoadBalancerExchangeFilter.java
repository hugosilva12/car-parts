package com.example.cloudgateway.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.net.URI;

public class LoadBalancerExchangeFilter implements ExchangeFilterFunction {

    private final LoadBalancerClient loadBalancerClient;

    public LoadBalancerExchangeFilter(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    @Override
    public Mono filter(ClientRequest request, ExchangeFunction next) {
        URI originalUrl = request.url();
        String serviceId = originalUrl.getHost();
        if (serviceId == null) {
            String msg = String.format(
                    "Request URI does not contain a valid hostname: %s",
                    originalUrl.toString());
            return Mono.just(
                    ClientResponse.create(HttpStatus.BAD_REQUEST).body(msg).build());
        }
        ServiceInstance instance = this.loadBalancerClient.choose(serviceId);
        if (instance == null) {
            String msg = String.format(
                    "Load balancer does not contain an instance for the service %s",
                    serviceId);
            return Mono.just(ClientResponse.create(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(msg).build());
        }
        URI uri = this.loadBalancerClient.reconstructURI(instance, originalUrl);
        ClientRequest newRequest = ClientRequest.method(request.method(), uri)
                .headers(headers -> headers.addAll(request.headers()))
                .cookies(cookies -> cookies.addAll(request.cookies()))
                .attributes(attributes -> attributes.putAll(request.attributes()))
                .body(request.body()).build();
        return next.exchange(newRequest);
    }

}

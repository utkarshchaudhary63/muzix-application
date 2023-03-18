package com.niit.GatewayAPI.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIConfig {

    @Bean
    public RouteLocator routing(RouteLocatorBuilder rlb){
        return rlb.routes().
                        route(i->
                                i.path("/user/api/v1/**").
                                        uri("lb://authenticationService/*")). //authentication
                        route(p->
                                p.path("/movie/api/v1/**").
                                        uri("lb://FavMovieService/*")). //fav-movie-ser
                        route(q->
                                q.path("/viewMovie/api/v1/**")
                                        .uri("lb://ViewMovieService/*")). //movie-view-service
                        route(k->
                                k.path("/search/api/v1/service/**")
                                        .uri("lb://search-service/*")). //search-movie-service
                        route(a->
                                a.path("/mail-app/**")
                                .uri("lb://EmailAppService/*")). //email-app-service
                        build();
    }
}

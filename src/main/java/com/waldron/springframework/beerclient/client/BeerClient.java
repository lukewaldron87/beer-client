package com.waldron.springframework.beerclient.client;

import com.waldron.springframework.beerclient.model.BeerDto;
import com.waldron.springframework.beerclient.model.BeerPagedList;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BeerClient {

    Mono<BeerDto> getBeerById(UUID beerId, Boolean showInventoryOnHand);

    Mono<BeerPagedList> listBeer(Integer pageNumber,
                                 Integer pageSize,
                                 String beerName,
                                 String beerStyle,
                                 Boolean showInventoryOnHand);

    Mono<ResponseEntity<Void>> createBeer(BeerDto beerDto);

    Mono<ResponseEntity<Void>> updateBeerById(UUID beerId, BeerDto beerDto);

    Mono<ResponseEntity<Void>> deleteBeerById(UUID beerId);

    Mono<BeerDto> getBeerByUPC(String upc);
}
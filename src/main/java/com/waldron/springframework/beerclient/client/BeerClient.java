package com.waldron.springframework.beerclient.client;

import com.waldron.springframework.beerclient.model.BeerDto;
import com.waldron.springframework.beerclient.model.BeerPagedList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BeerClient {

    Mono<BeerDto> getBeerById(UUID beerId, Boolean showInventoryOnHand);

    Mono<BeerPagedList> listBeer(Integer pageNumber,
                                 Integer pageSize,
                                 String beerName,
                                 String beerStyle,
                                 Boolean showInventoryOnHand);

    Mono<ResponseEntity> createNreBeer(BeerDto beerDto);

    Mono<ResponseEntity> updateBeerById(UUID beerId, BeerDto beerDto);

    Mono<ResponseEntity> deleteBeerById(UUID beerId);

    Mono<BeerDto> getBeerByUpc(UUID beerId);
}
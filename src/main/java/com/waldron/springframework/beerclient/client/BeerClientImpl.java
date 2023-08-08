package com.waldron.springframework.beerclient.client;

import com.waldron.springframework.beerclient.model.BeerDto;
import com.waldron.springframework.beerclient.model.BeerPagedList;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class BeerClientImpl implements BeerClient {
    @Override
    public Mono<BeerDto> getBeerById(UUID beerId, Boolean showInventoryOnHand) {
        return null;
    }

    @Override
    public Mono<BeerPagedList> listBeer(Integer pageNumber,
                                        Integer pageSize,
                                        String beerName,
                                        String beerStyle,
                                        Boolean showInventoryOnHand) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> createNreBeer(BeerDto beerDto) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> updateBeerById(UUID beerId, BeerDto beerDto) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> deleteBeerById(UUID beerId) {
        return null;
    }

    @Override
    public Mono<BeerDto> getBeerByUpc(UUID beerId) {
        return null;
    }
}

package com.waldron.springframework.beerclient.controller;

import com.waldron.springframework.beerclient.entity.Beer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface BeerController {

    //GET /beer List Beers
    Mono<Beer> listBeer();

    //POST Create New Beer
    Mono<Beer> createNreBeer(@RequestBody Beer beer);

    //GET /beer/{beerId} Get Beer by Id
    Mono<Beer> getBeerById(@PathVariable Long beerId);

    //PUT /beer/{beerId} Update Beer by Id
    Mono<Beer> updateBeerById(@PathVariable Long beerId, @RequestBody Beer beer);

    //DEL /beer/{beerId} Delete Beer
    Mono<Void> deleteBeerById(@PathVariable Long beerId);

    //GET /beerUpc/{upc} Get Beer by UPC
    Mono<Beer> getBeerBy(@PathVariable Long beerId);
}
git
package com.waldron.springframework.beerclient.client;

import com.waldron.springframework.beerclient.config.WebClientConfig;
import com.waldron.springframework.beerclient.model.BeerDto;
import com.waldron.springframework.beerclient.model.BeerPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BeerClientImplTest {

    BeerClientImpl beerClient;

    @BeforeEach
    void setUp() {
        beerClient = new BeerClientImpl(new WebClientConfig().webClient());
    }

    @Test
    void getBeerById() {
        // get list of beers to get an existing id

        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeer(1,
                                                                    10,
                                                                    null,
                                                                    null,
                                                                    null);

        BeerPagedList pagedList = beerPagedListMono.block();
        UUID beerId = pagedList.getContent().stream()
                .findFirst().get().getId();

        Mono<BeerDto> beerDtoMono = beerClient.getBeerById(beerId, false);

        BeerDto beerDto = beerDtoMono.block();

        assertThat(beerDto.getId()).isEqualTo(beerId);
        System.out.println(beerDto);
    }

    @Test
    void listBeer() {

        Integer pageNumber = null;
        Integer pageSize = null;
        String beerName = null;
        String beerStyle = null;
        Boolean showInventoryOnHand = null;

        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeer(pageNumber,
                                                                    pageSize,
                                                                    beerName,
                                                                    beerStyle,
                                                                    showInventoryOnHand);

        BeerPagedList pagedList = beerPagedListMono.block();

        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isGreaterThan(0);
        pagedList.forEach(System.out::println);
    }

    @Test
    void listBeerPageSize10() {

        Integer pageNumber = 1;
        Integer pageSize = 10;
        String beerName = null;
        String beerStyle = null;
        Boolean showInventoryOnHand = null;

        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeer(pageNumber,
                                                                    pageSize,
                                                                    beerName,
                                                                    beerStyle,
                                                                    showInventoryOnHand);

        BeerPagedList pagedList = beerPagedListMono.block();

        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isEqualTo(10);
        pagedList.forEach(System.out::println);
    }

    @Test
    void listBeerNoRecords() {

        Integer pageNumber = 10;
        Integer pageSize = 20;
        String beerName = null;
        String beerStyle = null;
        Boolean showInventoryOnHand = null;

        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeer(pageNumber,
                pageSize,
                beerName,
                beerStyle,
                showInventoryOnHand);

        BeerPagedList pagedList = beerPagedListMono.block();

        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isEqualTo(0);
        pagedList.forEach(System.out::println);
    }

    @Test
    void createNreBeer() {
    }

    @Test
    void updateBeerById() {
    }

    @Test
    void deleteBeerById() {
    }

    @Test
    void getBeerByUpc() {
    }
}
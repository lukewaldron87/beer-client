package com.waldron.springframework.beerclient.client;

import com.waldron.springframework.beerclient.config.WebClientConfig;
import com.waldron.springframework.beerclient.model.BeerDto;
import com.waldron.springframework.beerclient.model.BeerPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BeerClientImplTest {

    BeerClientImpl beerClient;

    @BeforeEach
    void setUp() {
        beerClient = new BeerClientImpl(new WebClientConfig().webClient());
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

    @Disabled("Bug in API")
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
        assertThat(beerDto.getQuantityOnHand()).isNull();
        System.out.println(beerDto);
    }

    @Test
    void getBeerByIdShowInventoryTrue() {
        // get list of beers to get an existing id

        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeer(1,
                10,
                null,
                null,
                null);

        BeerPagedList pagedList = beerPagedListMono.block();
        UUID beerId = pagedList.getContent().stream()
                .findFirst().get().getId();

        Mono<BeerDto> beerDtoMono = beerClient.getBeerById(beerId, true);

        BeerDto beerDto = beerDtoMono.block();

        assertThat(beerDto.getId()).isEqualTo(beerId);
        assertThat(beerDto.getQuantityOnHand()).isNotNull();
        System.out.println(beerDto);
    }

    @Test
    void getBeerByUPC() {

        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeer(1,
                10,
                null,
                null,
                null);

        BeerPagedList pagedList = beerPagedListMono.block();
        String upc = pagedList.getContent().stream()
                .findFirst().get().getUpc();

        Mono<BeerDto> beerDtoMono = beerClient.getBeerByUPC(upc);

        BeerDto beerDto = beerDtoMono.block();

        assertThat(beerDto.getUpc()).isEqualTo(upc);
        System.out.println(beerDto);
    }

    @Test
    void createBeer() {
        BeerDto beerDto = BeerDto.builder()
                .beerName("Dogfish")
                .beerStyle("IPA")
                .upc("132465789")
                .price(new BigDecimal("10.99"))
                .build();

        Mono<ResponseEntity<Void>> responseEntityMono = beerClient.createBeer(beerDto);

        ResponseEntity responseEntity = responseEntityMono.block();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void updateBeerById() {

        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeer(1, 10, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        BeerDto beerDto = pagedList.getContent().stream().findFirst().get();

        BeerDto updateBeerDto = BeerDto.builder()
                .beerName("updated name")
                .beerStyle(beerDto.getBeerStyle())
                .price(beerDto.getPrice())
                .upc(beerDto.getUpc())
                .build();

        Mono<ResponseEntity<Void>> responseEntityMono = beerClient.updateBeerById(beerDto.getId(), updateBeerDto);

        ResponseEntity<Void> responseEntity = responseEntityMono.block();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Mono<BeerDto> beerAfterUpdate = beerClient.getBeerById(beerDto.getId(), false);
        assertThat(beerAfterUpdate.block().getBeerName()).isEqualTo(updateBeerDto.getBeerName());
    }

    @Test
    void deleteBeerById() {
    }
}
package com.digitalinnovationone.heroesapi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.MediaType;
import static com.digitalinnovationone.heroesapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;

import com.digitalinnovationone.heroesapi.repository.HeroesRepository;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesapiApplicationTests 
{	  @Autowired
	  WebTestClient webTestClient;

	  @Autowired
	  HeroesRepository heroesRepository;


	  @Test
	  public void getOneHeroeById()
	  {	webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"10")
	      .exchange()
	      .expectStatus().isOk()
	      .expectBody();
	  }

	  @Test
	  public void getOneHeronotFound()
	  {	webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"10")
	      .exchange()
	      .expectStatus().isNotFound();
	  }


	  @Test
	  public void deleteHero()
	  {	webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"1")
	      .accept(MediaType.APPLICATION_JSON)
	      .exchange()
	      .expectStatus().isNotFound()
	      .expectBody(Void.class);
	  }
}
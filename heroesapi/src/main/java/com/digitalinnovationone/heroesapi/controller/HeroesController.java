package com.digitalinnovationone.heroesapi.controller;
import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovationone.heroesapi.repository.HeroesRepository;
import com.digitalinnovationone.heroesapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static com.digitalinnovationone.heroesapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;
@RestController
@Slf4j
public class HeroesController 
{	@Autowired private HeroesService service;
	@Autowired private HeroesRepository repository;
	
	private static final org.slf4j.Logger log = 
			org.slf4j.LoggerFactory.getLogger(HeroesController.class);
	
	public HeroesController(HeroesService service, HeroesRepository repository)
	{	this.repository = repository;
		this.service = service;
	}
	
	@GetMapping(HEROES_ENDPOINT_LOCAL)
	public Flux<Heroes> getAllItems()
	{	log.info("requesting the list off all heroes");
		return this.service.findAll();
	}
	
	@GetMapping(HEROES_ENDPOINT_LOCAL+"/id")
	public Mono<ResponseEntity<Heroes>> findById(@PathVariable String id)
	{	log.info("requesting the heroe with id {}", id);
		return this.service.findById(id).map((item) -> 
			new ResponseEntity<>(item, HttpStatus.OK))
			.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping(HEROES_ENDPOINT_LOCAL)
	@ResponseStatus(code=HttpStatus.CREATED)
	public Mono<Heroes> create(@RequestBody Heroes heroes)
	{	log.info("a new heroe was created");
		return this.service.save(heroes);
	}
	
	@DeleteMapping(HEROES_ENDPOINT_LOCAL+"/id")
	@ResponseStatus(code=HttpStatus.CONTINUE)
	public Mono<HttpStatus> deleteByid(@PathVariable String id)
	{	this.service.deleteById(id);
		log.info("deleting a hero with id {}", id);
		return Mono.just(HttpStatus.CONTINUE);
	}
}
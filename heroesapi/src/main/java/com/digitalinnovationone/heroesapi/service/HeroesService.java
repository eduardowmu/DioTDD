package com.digitalinnovationone.heroesapi.service;
import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovationone.heroesapi.repository.HeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class HeroesService 
{	@Autowired private HeroesRepository repository;

	public HeroesService(HeroesRepository repository) 
	{this.repository = repository;}
	
	//método que busca todos os heroes
	public Flux<Heroes> findAll()
	{return Flux.fromIterable(this.repository.findAll());}
	
	//como irá retornar apenas um heroe, terá que receber
	//como parâmetro o ID.
	public Mono<Heroes> findById(String id)
	{return Mono.justOrEmpty(this.repository.findById(id));}
	
	public Mono<Heroes> save(Heroes heroes)
	{return Mono.justOrEmpty(this.repository.save(heroes));}
	
	public Mono<Boolean> deleteById(String id)
	{	this.repository.deleteById(id);
		return Mono.justOrEmpty(this.findById(id) != null);
	}
}
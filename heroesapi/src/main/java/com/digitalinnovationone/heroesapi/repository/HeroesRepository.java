package com.digitalinnovationone.heroesapi.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import com.digitalinnovationone.heroesapi.document.Heroes;
@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String>{}
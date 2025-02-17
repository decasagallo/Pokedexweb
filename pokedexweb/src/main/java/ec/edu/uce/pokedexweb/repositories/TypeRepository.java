package ec.edu.uce.pokedexweb.repositories;

import ec.edu.uce.pokedexweb.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    Type findByName(String typeName);
}
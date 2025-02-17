package ec.edu.uce.pokedexweb.repositories;


import ec.edu.uce.pokedexweb.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    List<Pokemon> findByType_Id(Integer typeId);
    List<Pokemon> findByGeneration(String generation);
    List<Pokemon> findByType_IdAndGeneration(Integer typeId, String generation);
}
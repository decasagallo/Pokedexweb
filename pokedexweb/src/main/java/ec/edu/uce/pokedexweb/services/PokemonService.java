package ec.edu.uce.pokedexweb.services;

import ec.edu.uce.pokedexweb.entities.Pokemon;
import ec.edu.uce.pokedexweb.repositories.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private static final Logger logger = LoggerFactory.getLogger(PokemonService.class);

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getAllPokemons() {
        logger.info("Obteniendo todos los Pokémon...");
        List<Pokemon> pokemons = pokemonRepository.findAll();
        if (pokemons.isEmpty()) {
            logger.warn("No se encontraron Pokémon.");
        } else {
            logger.info("Se encontraron {} Pokémon.", pokemons.size());
        }
        return pokemons;
    }

    public List<Pokemon> getByType(Integer typeId) {
        logger.info("Consultando Pokémon con tipoId: {}", typeId);
        List<Pokemon> pokemons = pokemonRepository.findByType_Id(typeId);
        if (pokemons.isEmpty()) {
            logger.warn("No se encontraron Pokémon con tipoId: {}", typeId);
        } else {
            logger.info("Se encontraron {} Pokémon con tipoId: {}", pokemons.size(), typeId);
        }
        return pokemons;
    }

    public List<Pokemon> getByGeneration(String generation) {
        logger.info("Consultando Pokémon con generación: {}", generation);
        List<Pokemon> pokemons = pokemonRepository.findByGeneration(generation);
        if (pokemons.isEmpty()) {
            logger.warn("No se encontraron Pokémon con generación: {}", generation);
        } else {
            logger.info("Se encontraron {} Pokémon con generación: {}", pokemons.size(), generation);
        }
        return pokemons;
    }

    public List<Pokemon> getByTypeAndGeneration(Integer typeId, String generation) {
        logger.info("Consultando Pokémon con tipoId: {} y generación: {}", typeId, generation);
        List<Pokemon> pokemons = pokemonRepository.findByType_IdAndGeneration(typeId, generation);
        if (pokemons.isEmpty()) {
            logger.warn("No se encontraron Pokémon con tipoId: {} y generación: {}", typeId, generation);
        } else {
            logger.info("Se encontraron {} Pokémon con tipoId: {} y generación: {}", pokemons.size(), typeId, generation);
        }
        return pokemons;
    }
}

package ec.edu.uce.pokedexweb.controllers;

import ec.edu.uce.pokedexweb.entities.Pokemon;
import ec.edu.uce.pokedexweb.entities.Type;
import ec.edu.uce.pokedexweb.repositories.PokemonRepository;
import ec.edu.uce.pokedexweb.repositories.TypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PokemonController {

    private static final Logger logger = LoggerFactory.getLogger(PokemonController.class);

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/pokemons")
    public String listPokemons(
            @RequestParam(required = false) Integer tipoId,
            @RequestParam(required = false) String generation,
            Model model) {

        List<Pokemon> pokemons;
        logger.info("Recibiendo solicitud para listar Pokémon con tipoId: {} y generación: {}", tipoId, generation);

        // Si no se selecciona un tipo ni una generación, muestra todos los Pokémon
        if (tipoId == null && generation == null) {
            logger.info("No se han especificado filtros, mostrando todos los Pokémon.");
            pokemons = pokemonRepository.findAll();
        }
        // Si solo se selecciona un tipo
        else if (tipoId != null && generation == null) {
            logger.info("Filtrando por tipoId: {}", tipoId);
            pokemons = pokemonRepository.findByType_Id(tipoId);
        }
        // Si solo se selecciona una generación
        else if (tipoId == null && generation != null) {
            logger.info("Filtrando por generación: {}", generation);
            pokemons = pokemonRepository.findByGeneration(generation);
        }
        // Si se seleccionan tanto tipo como generación
        else {
            logger.info("Filtrando por tipoId: {} y generación: {}", tipoId, generation);
            pokemons = pokemonRepository.findByType_IdAndGeneration(tipoId, generation);
        }

        if (pokemons.isEmpty()) {
            logger.warn("No se encontraron Pokémon con los filtros especificados.");
        } else {
            logger.info("Se encontraron {} Pokémon con los filtros especificados.", pokemons.size());
        }

        List<Type> tipos = typeRepository.findAll();

        List<String> generaciones = List.of("generation-1", "generation-2", "generation-3", "generation-4", "generation-5", "generation-6", "generation-7", "generation-8", "generation-9");

        model.addAttribute("pokemons", pokemons);
        model.addAttribute("tipos", tipos);
        model.addAttribute("generaciones", generaciones);

        return "pokemons";  // Nombre de la plantilla Thymeleaf
    }
}
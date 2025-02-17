package ec.edu.uce.pokedexweb.services;


import ec.edu.uce.pokedexweb.entities.Type;
import ec.edu.uce.pokedexweb.repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    // Método para obtener todos los tipos de Pokémon
    public List<Type> getAllTipos() {
        return typeRepository.findAll();
    }

    // Método para obtener un tipo por su ID (opcional)
    public Type getTypeById(int id) {
        return typeRepository.findById(id).orElse(null);
    }
}
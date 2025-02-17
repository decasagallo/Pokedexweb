package ec.edu.uce.pokedexweb.entities;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Pokemon {
    @Id
    private Integer id;
    private String name;
    private Integer height;
    private Integer weight;
    private String sprite;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "pokemon_type",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<Type> type;

    private String generation;

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public List<Type> getType() {
        return type;
    }

    public void setType(List<Type> type) {
        this.type = type;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    // 🔹 Método para obtener los tipos como String separado por comas
    public String getTypeNames() {
        if (type == null || type.isEmpty()) {
            return "N/A";
        }
        return type.stream()
                .map(Type::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }


}

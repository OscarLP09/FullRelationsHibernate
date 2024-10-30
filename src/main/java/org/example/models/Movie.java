package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data // Nos ahorra todos los getters, setters, constructores vacios, etc.
@Entity


public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.EAGER)
    private List<Actor> actors =  new ArrayList<>();

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", actors=" + actors +
                ", studio=" + studio.getName() +
                '}';
    }

    public void addActor(Actor actor1) {
        actor1.getMovies().add(this);
        actors.add(actor1);
    }
}

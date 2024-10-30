package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity


public class Studio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "studio",
               fetch = FetchType.EAGER,
               cascade = CascadeType.PERSIST
    )

    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movie.setStudio(this);
        this.movies.add(movie);
    }


}

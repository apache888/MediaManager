package com.manager.model;

import javax.persistence.*;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 *
 * Class describes entity Movie
 */

@Entity
@Table(name = "movies", catalog = "media_storage")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status_id")
    @Enumerated(EnumType.ORDINAL)
    private MovieStatus status = MovieStatus.WANT_TO_WATCH;

    public Movie() {
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

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Movie " + "\'" + name + "\'," + " status \'" + status + "\'," + " id-" + id;
    }
}

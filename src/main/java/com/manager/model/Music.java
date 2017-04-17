package com.manager.model;

import javax.persistence.*;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 *
 * Class describes entity Music
 */

@Entity
@Table(name = "musics", catalog = "media_storage")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MusicStatus status = MusicStatus.WANT_TO_LISTEN;

    public Music() {
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

    public MusicStatus getStatus() {
        return status;
    }

    public void setStatus(MusicStatus status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Music " + "\'" + name + "\'," + " status \'" + status + "\'," + " id-" + id;
    }
}

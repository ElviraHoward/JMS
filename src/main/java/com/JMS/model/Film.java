package com.JMS.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "film",  schema = "spring")
@XmlRootElement(name = "film")
@XmlAccessorType(XmlAccessType.FIELD)
public class Film implements Serializable {

    @Id
    @GenericGenerator(name = "num", strategy = "increment")
    @GeneratedValue(generator = "num")
    @Column(name = "id_film")
    @JacksonXmlProperty(localName = "id")
    private Long id;

    @Column(name = "name")
    @JacksonXmlProperty(localName = "name")
    private String name;

    @Column(name = "release_date")
    @JacksonXmlProperty(localName = "release_date")
    private int releaseDate;

    @Column(name = "rating")
    @JacksonXmlProperty(localName = "rating")
    private double rating;

    public Film() {
    }

    public Film(Film film) {
        name = film.getName();
        releaseDate = film.getReleaseDate();
        rating = film.getRating();
    }

    @NotNull
    public Film createEntity() {
        Film film = new Film();
        film.setId(id);
        film.setName(name);
        film.setReleaseDate(releaseDate);
        film.setRating(rating);

        return film;
    }

    @Override
    public String toString() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}

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
@Table(name = "director", schema = "spring")
@XmlRootElement(name = "director")
@XmlAccessorType(XmlAccessType.FIELD)
public class Director implements Serializable {

    @Id
    @GenericGenerator(name = "num", strategy = "increment")
    @GeneratedValue(generator = "num")
    @Column(name = "id_director")
    @JacksonXmlProperty(localName = "id")
    private Long id;

    @Column(name = "name")
    @JacksonXmlProperty(localName = "name")
    private String name;

    @Column(name = "count_of_oscars")
    @JacksonXmlProperty(localName = "countOfOscars")
    private int countOfOscars;

    public Director() {
    }

    public Director(Director director) {
        name = director.getName();
        countOfOscars = director.getCountOfOscars();
    }

    @NotNull
    public Director createEntity() {
        Director director = new Director();
        director.setId(id);
        director.setName(name);
        director.setCountOfOscars(countOfOscars);

        return director;
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

    public int getCountOfOscars() {
        return countOfOscars;
    }

    public void setCountOfOscars(int countOfOscars) {
        this.countOfOscars = countOfOscars;
    }

}

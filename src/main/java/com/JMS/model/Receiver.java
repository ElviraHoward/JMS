package com.JMS.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Elvira on 02.06.2018.
 */
@Entity
@Table(name = "receiver")
public class Receiver implements Serializable {

    @Id
    @GenericGenerator(name = "num", strategy = "increment")
    @GeneratedValue(generator = "num")
    @Column(name = "id_receiver")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "class")
    private String classEntity;

    @Column(name = "type")
    private Change.ChangeEnum type;

    public Receiver() {
    }

    public Receiver(Long id, String email, String classEntity, Change.ChangeEnum type) {
        this.id = id;
        this.email = email;
        this.classEntity = classEntity;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", classEntity=" + classEntity +
                ", type='" + type + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(String classEntity) {
        this.classEntity = classEntity;
    }

    public Change.ChangeEnum getType() {
        return type;
    }

    public void setType(Change.ChangeEnum type) {
        this.type = type;
    }
}

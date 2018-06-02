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

    @Column(name = "values")
    private String values;

    public Receiver() {
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", classEntity=" + classEntity +
                ", values='" + values + '\'' +
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

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}

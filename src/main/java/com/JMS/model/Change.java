package com.JMS.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Elvira on 02.06.2018.
 */

@Entity
@Table(name = "change")
public class Change implements Serializable{

    @Id
    @GenericGenerator(name = "num", strategy = "increment")
    @GeneratedValue(generator = "num")
    @Column(name = "id_change")
    private Long id;

    @Column(name = "change_type")
    @Enumerated(EnumType.STRING)
    private ChangeEnum changeType;

    @Column(name = "change_class")
    private String changeClass;

    @Column(name = "id_entity")
    private Long id_entity;

    @Column(name = "new_values")
    private String newValues;

    public Change() {
    }

    public Change(Long id, ChangeEnum changeType, String changeClass, Long id_entity, String newValues) {
        this.id = id;
        this.changeType = changeType;
        this.changeClass = changeClass;
        this.id_entity = id_entity;
        this.newValues = newValues;
    }

    @Override
    public String toString() {
        return "Change{" +
                "id=" + id +
                ", changeType='" + changeType + '\'' +
                ", changeClass=" + changeClass +
                ", id_entity=" + id_entity +
                ", newValues='" + newValues + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Change)) return false;

        Change change = (Change) o;

        if (id != null ? !id.equals(change.id) : change.id != null) return false;
        if (changeType != change.changeType) return false;
        if (changeClass != null ? !changeClass.equals(change.changeClass) : change.changeClass != null) return false;
        if (id_entity != null ? !id_entity.equals(change.id_entity) : change.id_entity != null) return false;
        return newValues != null ? newValues.equals(change.newValues) : change.newValues == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (changeType != null ? changeType.hashCode() : 0);
        result = 31 * result + (changeClass != null ? changeClass.hashCode() : 0);
        result = 31 * result + (id_entity != null ? id_entity.hashCode() : 0);
        result = 31 * result + (newValues != null ? newValues.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChangeEnum getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeEnum changeType) {
        this.changeType = changeType;
    }

    public String getChangeClass() {
        return changeClass;
    }

    public void setChangeClass(String changeClass) {
        this.changeClass = changeClass;
    }

    public Long getId_entity() {
        return id_entity;
    }

    public void setId_entity(Long id_entity) {
        this.id_entity = id_entity;
    }

    public String getNewValues() {
        return newValues;
    }

    public void setNewValues(String newValues) {
        this.newValues = newValues;
    }

    public enum ChangeEnum {
        CREATE, UPDATE, DELETE
    }

}

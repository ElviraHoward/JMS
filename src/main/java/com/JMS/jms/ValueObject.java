package com.JMS.jms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Elvira on 04.06.2018.
 */
public interface ValueObject<T>  extends Serializable{
    @NotNull
    T createEntity();

    @Nullable
    default String toJson() {
        try {
            return new CustomObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @NotNull
    @JsonIgnore
    @XmlTransient
    default String getEntityClassName() {
        Type t = getClass().getGenericInterfaces()[0];
        ParameterizedType pt = (ParameterizedType) t;
        return ((Class) pt.getActualTypeArguments()[0]).getSimpleName();
    }
}

package com.JMS.service;

import com.JMS.model.Change;
import com.JMS.repository.ChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elvira on 02.06.2018.
 */
@Service
public class ChangeService {

    @Autowired
    ChangeRepository changeRepository;

    public Change save(Change change) {
        return changeRepository.save(change);
    }

    public Change getById(Long id_change) {
        return changeRepository.findById(id_change).get();
    }

    public List<Change> findAll() {
        List<Change> changes = changeRepository.findAll();
        return changes != null ? changes : new ArrayList<>();
    }

    public void delete(Long id_change) {
        changeRepository.deleteById(id_change);
    }
}

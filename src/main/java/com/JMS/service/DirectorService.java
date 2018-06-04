package com.JMS.service;

import com.JMS.jms.ChangesDTO;
import com.JMS.jms.Sender;
import com.JMS.model.Director;
import com.JMS.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectorService {

    private Sender sender;
    @Autowired
    private DirectorRepository directorRepository;

    public Director save(Director director) {
        sender.send(director.getId() == null ? ChangesDTO.createCreateMessage("director", director.getId(), director.getName()) : ChangesDTO.createUpdateMessage("director", director.getId(), director.getName()));
        return directorRepository.save(director);
    }

    public Director getById(Long id_director) {
        return directorRepository.findById(id_director).get();
    }

    public List<Director> findAll() {
        List<Director> directors = directorRepository.findAll();
        return directors != null ? directors : new ArrayList<>();
    }

    public void delete(Long id_director) {
        sender.send(ChangesDTO.createDeleteMessage("director", id_director, Director.class.getName()));
        directorRepository.deleteById(id_director);
    }
}

package com.JMS.service;

import com.JMS.jms.ChangesDTO;
import com.JMS.jms.Sender;
import com.JMS.model.Film;
import com.JMS.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {

    private Sender sender;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    public FilmService(Sender sender) {
        this.sender = sender;
    }

    public Film save(Film film) {
        sender.send(ChangesDTO.createCreateMessage("film", film.getId(), film.getName()));
        return filmRepository.save(film);
    }

    public Film getById(Long id_film) {
        return filmRepository.findById(id_film).get();
    }

    public List<Film> findAll() {
        List<Film> films = filmRepository.findAll();
        return films != null ? films : new ArrayList<>();
    }

    public void delete(Long id_film) {
        sender.send(ChangesDTO.createDeleteMessage("film", id_film, Film.class.getName()));
        filmRepository.deleteById(id_film);
    }
}

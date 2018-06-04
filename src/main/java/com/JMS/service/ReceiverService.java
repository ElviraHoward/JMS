package com.JMS.service;

import com.JMS.model.Change;
import com.JMS.model.Receiver;
import com.JMS.repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Elvira on 02.06.2018.
 */
@Service
public class ReceiverService {

    @Autowired
    ReceiverRepository receiverRepository;

    public Receiver getById(Long id_receiver) {
        return receiverRepository.findById(id_receiver).get();
    }

    public List<Receiver> findByTypeAndClassEntity(Change.ChangeEnum type, String classEntity) {
        return receiverRepository.findByTypeAndClassEntity(type, classEntity);
    }
}

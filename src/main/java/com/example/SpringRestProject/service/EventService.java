package com.example.SpringRestProject.service;

import com.example.SpringRestProject.model.Event;
import com.example.SpringRestProject.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event){
        return eventRepository.save(event);
    }

    public Event findById(long id){
        return eventRepository.getById(id);
    }

    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    public void delete(long id){
        eventRepository.deleteById(id);
    }
}

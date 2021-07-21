package com.example.SpringRestProject.rest;

import com.example.SpringRestProject.model.Event;
import com.example.SpringRestProject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "{id}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('events:read')")
    public ResponseEntity<Event> getEvent(@PathVariable("id") Long eventId){
        if(eventId == null){
            return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
        }
        Event event = eventService.findById(eventId);
        if(event == null){
            return  new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Event>(event , HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('events:read')")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = eventService.findAll();
        if(events.isEmpty()){
            return new ResponseEntity<List<Event>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('events:write')")
    public ResponseEntity<Event> saveEvent(@RequestBody @Valid Event event){
        HttpHeaders headers = new HttpHeaders();
        if(event == null){
            return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
        }
        eventService.save(event);
        return new ResponseEntity<Event>(event,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('events:write')")
    public ResponseEntity<Event> updateEvent(@RequestBody @Valid Event event , UriComponentsBuilder builder){
        HttpHeaders headers = new HttpHeaders();
        if(event == null){
            return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
        }
        eventService.save(event);
        return new ResponseEntity<Event>(event,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}" , method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('events:write')")
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") Long eventId){
        Event event = eventService.findById(eventId);
        if(event == null){
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
        eventService.delete(eventId);
        return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
    }
}

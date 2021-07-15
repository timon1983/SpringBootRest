package com.example.SpringRestProject.controller;

import com.example.SpringRestProject.model.User;
import com.example.SpringRestProject.service.EventService;
import com.example.SpringRestProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private EventService eventService;

    @Autowired
    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @RequestMapping(value = "{id}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long userId){
        if(userId == null){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.findById(userId);
        if(user == null){
            return  new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        user.setEvents(eventService.findAll());

        return new ResponseEntity<User>(user , HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.findAll();

        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        List<User> allUusers = users.stream().
                map(x -> {x.setEvents(eventService.findAll());
                    return x;}).
                collect(Collectors.toList());

        return new ResponseEntity<List<User>>(allUusers, HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user){
        HttpHeaders headers = new HttpHeaders();
        if(user == null){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        userService.save(user);
        user.setEvents(eventService.findAll());

        return new ResponseEntity<User>(user,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user , UriComponentsBuilder builder){
        HttpHeaders headers = new HttpHeaders();
        if(user == null){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        userService.save(user);
        user.setEvents(eventService.findAll());

        return new ResponseEntity<User>(user,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}" , method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId){
        User user = userService.findById(userId);
        if(user == null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.delete(userId);

        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}

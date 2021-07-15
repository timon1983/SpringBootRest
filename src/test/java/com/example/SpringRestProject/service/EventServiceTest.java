package com.example.SpringRestProject.service;

import com.example.SpringRestProject.model.Event;
import com.example.SpringRestProject.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository = Mockito.mock(EventRepository.class);
    @Mock
    private EventService eventService = Mockito.mock(EventService.class);

    @Test
    void checkSaveService_Should_Return_Event() {
        Event event = new Event();
        when(eventRepository.save(event)).thenReturn(event);
    }

    @Test
    void checkGetByIdService_Should_Return_Event_By_Id() {
        Event event = new Event();
        when(eventRepository.getById(2L)).thenReturn(event);
    }

    @Test
    void checkGetAllService_Should_Show_All_of_Events() {
        List<Event> eventList = new ArrayList<>();
        when(eventRepository.findAll()).thenReturn(eventList);
    }

    @Test
    void checkDeleteByIdService() {
        eventService.delete(2L);
        Mockito.verify(eventService).delete(2L);
    }
}
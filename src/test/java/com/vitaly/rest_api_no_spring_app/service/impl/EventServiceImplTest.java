package com.vitaly.rest_api_no_spring_app.service.impl;

import com.vitaly.rest_api_no_spring_app.dto.EventDto;
import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.model.User;
import com.vitaly.rest_api_no_spring_app.repository.EventRepository;
import com.vitaly.rest_api_no_spring_app.service.EventService;
import com.vitaly.rest_api_no_spring_app.util.mappers.EventMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//  04-Feb-24
// gh crazym8nd

class EventServiceImplTest {

    private final EventRepository eventRepMock = mock(EventRepository.class);
    private final EventService eventService = new EventServiceImpl(eventRepMock);

    File mockFile = new File(1,"fakename1", "fakepath1", Status.ACTIVE);
    User mockUser = new User(1, "username1", null, Status.ACTIVE);
    private Event mockEvent = new Event(1, mockUser, mockFile, Status.ACTIVE);
    private List<Event> mockEventsList = List.of(mockEvent);



    //positive scenario
    @Test
    void getAllSuccess() {

        when(eventRepMock.getAll()).thenReturn(mockEventsList);
        List<EventDto> result = eventService.getAll();

        assertNotNull(result);
        assertEquals(mockEventsList.size(), result.size());

        for(int i=0;i<mockEventsList.size();i++){
            Event event = mockEventsList.get(i);
            EventDto eventDto = result.get(i);
            assertEquals(event.getId(), eventDto.getId());
            assertEquals(event.getUser().getId(), eventDto.getUser().getId());
            assertEquals(event.getFile().getId(), eventDto.getFile().getId());
            assertEquals(event.getStatus(), eventDto.getStatus());
        }

    }

    @Test
    void getByIdSuccess() {
        when(eventRepMock.getById(1)).thenReturn(mockEvent);
        eventService.getById(1);

        assertEquals(mockEvent.getId(), eventService.getById(1).getId());
        assertEquals(mockEvent.getUser().getId(), eventService.getById(1).getUser().getId());
        assertEquals(mockEvent.getFile().getId(), eventService.getById(1).getFile().getId());
        assertEquals(mockEvent.getStatus(), eventService.getById(1).getStatus());

    }

    @Test
    void createSuccess() {
        when(eventRepMock.create(mockEvent)).thenReturn(mockEvent);

        EventDto eventDto = eventService.create(EventMapper.convertEntityToDto(mockEvent));

        assertEquals(mockEvent.getId(), eventDto.getId());
        assertEquals(mockEvent.getUser().getId(), eventDto.getUser().getId());
        assertEquals(mockEvent.getFile().getId(), eventDto.getFile().getId());
        assertEquals(mockEvent.getStatus(), eventDto.getStatus());
    }

    @Test
    void updateSuccess() {
        when(eventRepMock.update(mockEvent)).thenReturn(mockEvent);

        EventDto eventDto = eventService.update(EventMapper.convertEntityToDto(mockEvent));

        assertEquals(mockEvent.getId(), eventDto.getId());
        assertEquals(mockEvent.getUser().getId(), eventDto.getUser().getId());
        assertEquals(mockEvent.getFile().getId(), eventDto.getFile().getId());
        assertEquals(mockEvent.getStatus(), eventDto.getStatus());
    }

    @Test
    void deleteByIdSuccess() {
        eventService.deleteById(1);
        verify(eventRepMock).deleteById(1);

    }


    //negative scenario

    @Test
    void getAllNegative() {
        when(eventRepMock.getAll()).thenReturn(emptyList());
        List<EventDto> result = eventService.getAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void getByIdNegative() {
        int nonExistingId = 999;
        when(eventRepMock.getById(nonExistingId)).thenThrow(new IllegalArgumentException("No such event"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            eventService.getById(nonExistingId); });
    }

    @Test
    void createNegative() {
        Event event = null;
        EventDto result =eventService.create(EventMapper.convertEntityToDto(event));
        assertNull(result.getId());
        assertNull(result.getUser());
        assertNull(result.getFile());
        assertNull(result.getStatus());
    }

    @Test
    void updateNegative() {
        EventDto eventDto = null;
        EventDto result = eventService.update(eventDto);
        assertNull(result);
    }

    @Test
    void deleteByIdNegative() {
        eventService.deleteById(999);
        verify(eventRepMock).deleteById(999);
    }
}
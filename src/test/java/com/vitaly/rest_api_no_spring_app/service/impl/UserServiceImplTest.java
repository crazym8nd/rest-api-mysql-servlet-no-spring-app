package com.vitaly.rest_api_no_spring_app.service.impl;


//  02-Feb-24
// gh crazym8nd

import com.vitaly.rest_api_no_spring_app.dto.UserDto;
import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.model.User;
import com.vitaly.rest_api_no_spring_app.repository.UserRepository;
import com.vitaly.rest_api_no_spring_app.repository.impl.UserRepositoryImpl;
import com.vitaly.rest_api_no_spring_app.service.UserService;
import com.vitaly.rest_api_no_spring_app.util.mappers.UserMapper;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private final UserRepository userRepMock = mock(UserRepositoryImpl.class);
    private final UserService userService = new UserServiceImpl(userRepMock);
    private final User mockUser = new User(1, "Vitaly", null, Status.ACTIVE);
    private final User mockUser2 = new User(2, "George", null, Status.ACTIVE);

    private final List<User> mockUsersList = List.of(mockUser);

//positive tests
    @Test
    void getAllSuccess() {
        when(userRepMock.getAll()).thenReturn(mockUsersList);

        List<UserDto> result = userService.getAll();

        assertNotNull(result);
        assertEquals(mockUsersList.size(), result.size());

        for (int i = 0; i < mockUsersList.size(); i++) {
            User user = mockUsersList.get(i);
            UserDto userDto = result.get(i);

            assertEquals(user.getId(), userDto.getId());
            assertEquals(user.getName(), userDto.getName());
            assertEquals(user.getStatus(), userDto.getStatus());
        }
    }

@Test
    void getByIdSuccess() {
        when(userRepMock.getById(1)).thenReturn(mockUser);

        UserDto result = userService.getById(1);

        assertNotNull(result);
        assertEquals(mockUser.getId(), result.getId());
        assertEquals(mockUser.getName(), result.getName());
        assertEquals(mockUser.getStatus(), result.getStatus());
    }

@Test
    void createSuccess() {
        User newUser = new User(1, "Vitaly", null, Status.ACTIVE);
        when(userRepMock.create(newUser)).thenReturn(newUser);

        UserDto result = userService.create(UserMapper.convertEntityToDtoWithoutEvents(newUser));

        assertNotNull(result);
        assertEquals(newUser.getId(), result.getId());
        assertEquals(newUser.getName(), result.getName());
        assertEquals(newUser.getStatus(), result.getStatus());

    }

@Test
    void updateSuccess() {
        User updatedUser = mockUser;
        when(userRepMock.update(updatedUser)).thenReturn(updatedUser);

        UserDto result = userService.update(UserMapper.convertEntityToDtoWithoutEvents(updatedUser));
        assertNotNull(result);
        assertEquals(updatedUser.getId(), result.getId());
        assertEquals(updatedUser.getName(), result.getName());
        assertEquals(updatedUser.getStatus(), result.getStatus());
    }

@Test
    void deleteByIdSuccess() {
        userService.deleteById(1);
        verify(userRepMock,times(1)).deleteById(1);
    }

    //negative scenario
    @Test
    void getAllNegative() {
        when(userRepMock.getAll()).thenReturn(emptyList());

        List<UserDto> result = userService.getAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void getByIdNegative() {
        int nonExistingId = 999;
        when(userRepMock.getById(nonExistingId)).thenReturn(new User(-1, "NO SUCH USER", null, Status.ACTIVE));

        UserDto result = userService.getById(nonExistingId);
        assertNotNull(result);
        assertEquals(-1, result.getId());
        assertEquals("NO SUCH USER", result.getName());
        assertEquals(Status.ACTIVE, result.getStatus());
    }

    @Test
    void createNegative() {
        UserDto userDtoToSave = new UserDto();

        assertNull(userRepMock.create(UserMapper.convertDtoToEntity(userDtoToSave)));

    }

    @Test
    void updateNegative() {
        UserDto nonExistentUser = new UserDto();
        when(userRepMock.update(UserMapper.convertDtoToEntity(nonExistentUser))).thenReturn(null);
        assertNull(userRepMock.update(UserMapper.convertDtoToEntity(nonExistentUser)));
    }

    @Test
    void deleteByIdNegative() {
        userService.deleteById(999);
        verify(userRepMock).deleteById(999);
    }

}
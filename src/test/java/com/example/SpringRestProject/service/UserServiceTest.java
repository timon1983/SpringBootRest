package com.example.SpringRestProject.service;

import com.example.SpringRestProject.model.User;
import com.example.SpringRestProject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    @Mock
    private UserService userServiceMock = Mockito.mock(UserService.class);

    @Test
    void checkSaveService_Should_Return_User() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
    }

    @Test
    void checkGetByIdService_Should_Return_User_By_Id() {
        User user = new User();
        when(userRepository.getById(2L)).thenReturn(user);
    }

    @Test
    void checkGetAllService_Should_Show_All_of_Users() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
    }


    @Test
    void checkDeleteByIdService_Should_Run_DeleteUser() {
        userServiceMock.delete(2L);
        Mockito.verify(userServiceMock).delete(2L);
    }

}
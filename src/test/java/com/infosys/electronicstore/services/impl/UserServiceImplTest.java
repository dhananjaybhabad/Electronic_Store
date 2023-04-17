package com.infosys.electronicstore.services.impl;import com.infosys.electronicstore.dtos.UserDto;import com.infosys.electronicstore.entities.User;import com.infosys.electronicstore.exceptions.ResourceNotFoundException;import com.infosys.electronicstore.repositories.UserRepository;import com.infosys.electronicstore.services.UserServiceI;import org.junit.jupiter.api.Assertions;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.Test;import org.mockito.Mockito;import org.modelmapper.ModelMapper;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.test.mock.mockito.MockBean;import java.util.Optional;import static org.junit.jupiter.api.Assertions.*;@SpringBootTestclass UserServiceImplTest {    @Autowired    private UserServiceI userServiceI;    @Autowired    private ModelMapper modelMapper;    @MockBean    private UserRepository userRepository;    User user;    UserDto userDto;    @BeforeEach    public void init() {        userDto = UserDto.builder().name("Dhananjay").email("dhananjaybhabad01@gmail.com").about("software Engineer").gender("Male").imageName("abc.png").password("abc@123").build();        user = User.builder().name("Dhananjay").email("dhananjaybhabad01@gmail.com").about("software Engineer").gender("Male").imageName("abc.png").password("abc@123").build();    }    @Test    void createUserTest() {        //arrange        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);        //act        UserDto user1 = userServiceI.createUser(modelMapper.map(user, UserDto.class));        System.out.println(user1.getName());        //assert        Assertions.assertNotNull(user1);        Assertions.assertEquals("Dhananjay", user1.getName());    }    //update user test    @Test    public void updateUserTest() {        Long userId = 1l;        //arrange        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);        //act        UserDto updatedUser = userServiceI.updateUser(userDto, userId);       // UserDto updatedUser = modelMapper.map(user, UserDto.class);        System.out.println(updatedUser.getName());        //assert        Assertions.assertNotNull(updatedUser);        Assertions.assertEquals(userDto.getName(), updatedUser.getName(), "Name is not Validate");       // Assertions.assertThrows(ResourceNotFoundException.class, () -> userServiceI.updateUser(userDto, 11l));        //multiple assertion are valid    }    //delete user test case//    @Test//    public void deleteUserTest() {//////            Long userId = 1l;////            //arrange//            Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user));////            //act//            userServiceI.deleteUser(userId);////            //assert//            Mockito.verify(userRepository, Mockito.times(1)).delete(user );//            //Assertions.assertThrows(ResourceNotFoundException.class, () -> userServiceI.deleteUser(11l));////        }    }
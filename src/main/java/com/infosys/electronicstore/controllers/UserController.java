package com.infosys.electronicstore.controllers;import com.infosys.electronicstore.constant.AppConstant;import com.infosys.electronicstore.dtos.ApiResponse;import com.infosys.electronicstore.dtos.UserDto;import com.infosys.electronicstore.services.UserServiceI;import lombok.extern.slf4j.Slf4j;import org.slf4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;import java.util.List;@Slf4j@RestController@RequestMapping("/users")public class UserController {    @Autowired    private UserServiceI userService;    @PostMapping("/create")    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {        log.info("Initiating save request for create user ");        UserDto user = this.userService.createUser(userDto);        log.info("Completed save request for create user ");        return new ResponseEntity<>(user, HttpStatus.CREATED);    }    @PutMapping("/{userId}")    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId) {        log.info("Initiating request for update user ");        UserDto updatedUser = this.userService.updateUser(userDto, userId);        log.info("Completed request for update user "+userId);        return new ResponseEntity<>(updatedUser, HttpStatus.OK);    }    @DeleteMapping("/{userId}")    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {        log.info("Initiating request for delete user ");        this.userService.deleteUser(userId);        ApiResponse msg = ApiResponse.builder().message(AppConstant.DELETE_SUCCESS)                .success(true).status(HttpStatus.OK).build();        log.info("Completed request for delete user "+userId);        return new ResponseEntity<>(msg, HttpStatus.OK);    }    @GetMapping("/getById/{userId}")    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {        log.info("Initiating request for get user by Id ");        UserDto userById = this.userService.getUserById(userId);        log.info("Completed request for get user by Id "+userId);        return new ResponseEntity<>(userById, HttpStatus.OK);    }    @GetMapping("/getAll")    public ResponseEntity<List<UserDto>> getAllUsers            (@RequestParam (value="pageNumber",defaultValue = "0",required = false)int PageNumber,    @RequestParam (value="pageSize",defaultValue = "10",required = false)int PageSize)    {        log.info("Initiating request for get All users ");        List<UserDto> allUsers = this.userService.getAllUsers(PageNumber,PageSize);        log.info("Completed request for get All users ");        return new ResponseEntity<>(allUsers, HttpStatus.OK);    }    @GetMapping("/{email}")    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {        log.info("Initiating get request for UserByEmail ");        UserDto userByEmail = this.userService.getUserByEmail(email);        log.info("Completed get request for UserByEmail "+email);        return new ResponseEntity<>(userByEmail, HttpStatus.OK);    }    @GetMapping("/search/{keyword}")    public ResponseEntity<List<UserDto>> searchUserByKeyword(@PathVariable String keyword) {        log.info("Initiating request for Search User ");        List<UserDto> searchedUsers = this.userService.searchUser(keyword);        log.info("Completed request for Search User "+keyword);        return new ResponseEntity<>(searchedUsers, HttpStatus.OK);    }}
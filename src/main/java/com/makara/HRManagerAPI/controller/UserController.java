package com.makara.HRManagerAPI.controller;

import com.makara.HRManagerAPI.dto.request.CreateUserRequest;
import com.makara.HRManagerAPI.dto.request.UpdateUserRequest;
import com.makara.HRManagerAPI.dto.response.UserResponse;
import com.makara.HRManagerAPI.model.User;
import com.makara.HRManagerAPI.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User create(@RequestBody CreateUserRequest request){
        return userService.create(request);
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAll(Authentication auth){
        System.out.println(auth.getAuthorities());
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponse update( @PathVariable Long id, @Valid @RequestBody UpdateUserRequest request){
        return userService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
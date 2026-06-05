package com.makara.HRManagerAPI.service;

import com.makara.HRManagerAPI.dto.request.CreateUserRequest;
import com.makara.HRManagerAPI.dto.request.UpdateUserRequest;
import com.makara.HRManagerAPI.dto.response.UserResponse;
import com.makara.HRManagerAPI.model.Department;
import com.makara.HRManagerAPI.model.Role;
import com.makara.HRManagerAPI.model.User;
import com.makara.HRManagerAPI.repo.DepartmentRepository;
import com.makara.HRManagerAPI.repo.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;

    public User create(CreateUserRequest request){
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password());
        return userRepository.save(user);
    }

    public List<UserResponse> findAll(){
        List<User> users =userRepository.findAll();
        return users.stream()
                .map(this::mapUserResponse)
                .toList();
    }

    public UserResponse findById(Long id){
           User user =  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
           return mapUserResponse(user);

    }

    public UserResponse update(Long id, UpdateUserRequest request){
        User user =  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(request.username());
        user.setEnabled(request.enabled());
        user.setFullName(request.fullName());
        user.setPhone(request.phone());

        Department department = departmentRepository.findById(request.departmentId()).orElseThrow(() -> new RuntimeException("Not found department"));
        user.setDepartment(department);

        if (!List.of(Role.ADMIN, Role.HR, Role.USER).contains(request.role())) {
            throw new RuntimeException("Role incorrect");
        }
        user.setRole(request.role());
        return mapUserResponse(userRepository.save(user));
    }

    public void delete(Long id){
        User user =  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    private UserResponse mapUserResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getPhone(),
                user.getEnabled(),
                user.getRole(),
                user.getDepartment() != null ? user.getDepartment().getId() : null,
                user.getDepartment() != null ? user.getDepartment().getName() : null,
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
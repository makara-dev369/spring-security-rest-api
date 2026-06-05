package com.makara.HRManagerAPI.service;

import com.makara.HRManagerAPI.dto.request.DepartmentRequest;
import com.makara.HRManagerAPI.dto.response.DepartmentResponse;
import com.makara.HRManagerAPI.model.Department;
import com.makara.HRManagerAPI.model.User;
import com.makara.HRManagerAPI.repo.DepartmentRepository;
import com.makara.HRManagerAPI.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentResponse create(DepartmentRequest request){
        User manager = userRepository.findById(request.managerId()).orElseThrow(()->new RuntimeException("Manager not found"));
        Department department = new Department();
        department.setName(request.name());
        department.setManager(manager);
        Department saved = departmentRepository.save(department);
        return map(saved);
    }

    public List<DepartmentResponse> findAll(){
        return departmentRepository
                .findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public DepartmentResponse findById(Long id){
        Department department = departmentRepository.findById(id).orElseThrow(()->new RuntimeException("Department not found"));
        return map(department);
    }

    public DepartmentResponse update(Long id, DepartmentRequest request){

        Department department = departmentRepository.findById(id).orElseThrow(()->new RuntimeException("Department not found"));
        User manager = userRepository.findById(request.managerId()).orElseThrow(()->new RuntimeException("Manager not found"));
        department.setName(request.name());
        department.setManager(manager);

        return map(departmentRepository.save(department));
    }

    public void delete(Long id){

        Department department = departmentRepository.findById(id).orElseThrow(()->new RuntimeException("Department not found"));
        if (!department.getUsers().isEmpty()) {
            throw new RuntimeException("Cannot delete department with users");
        }
        departmentRepository.delete(department);
    }
    private DepartmentResponse map(Department d){
        return new DepartmentResponse(
                d.getId(),
                d.getName(),
                d.getManager()!=null ? d.getManager().getUsername() : null
        );
    }

}

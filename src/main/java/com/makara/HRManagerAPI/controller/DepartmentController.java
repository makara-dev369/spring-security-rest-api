package com.makara.HRManagerAPI.controller;

import com.makara.HRManagerAPI.dto.request.DepartmentRequest;
import com.makara.HRManagerAPI.dto.response.DepartmentResponse;
import com.makara.HRManagerAPI.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public DepartmentResponse create( @Valid  @RequestBody DepartmentRequest request){
        return departmentService.create(request);
    }

    @GetMapping
    public List<DepartmentResponse> getAll(){
        return departmentService.findAll();
    }
    @GetMapping("/{id}")
    public DepartmentResponse getById(@PathVariable Long id){
        return departmentService.findById(id);
    }

    @PutMapping("/{id}")
    public DepartmentResponse update(@PathVariable Long id,  @Valid @RequestBody DepartmentRequest request){
        return departmentService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        departmentService.delete(id);
    }

}

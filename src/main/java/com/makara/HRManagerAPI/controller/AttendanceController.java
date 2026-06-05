package com.makara.HRManagerAPI.controller;

import com.makara.HRManagerAPI.dto.request.AttendanceRequest;
import com.makara.HRManagerAPI.dto.request.DepartmentRequest;
import com.makara.HRManagerAPI.dto.response.AttendanceResponse;
import com.makara.HRManagerAPI.dto.response.DepartmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    @PostMapping
    public AttendanceResponse create(@Valid @RequestBody AttendanceRequest request){
        return null;
    }

}

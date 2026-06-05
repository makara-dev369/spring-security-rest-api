package com.makara.HRManagerAPI.dto.response;

public record DepartmentResponse(
        Long id,
        String name,
        String managerName
) {}
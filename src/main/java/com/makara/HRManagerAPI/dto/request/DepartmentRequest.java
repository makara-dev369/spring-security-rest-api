package com.makara.HRManagerAPI.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentRequest(
        @NotNull(message = "Name is required")
        String name,

        @NotNull(message = "Manager is required")
        Long managerId
) {}
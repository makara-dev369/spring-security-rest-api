package com.makara.HRManagerAPI.dto.request;

import com.makara.HRManagerAPI.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50)
        String username,

        @NotBlank(message = "Full name is required")
        String fullName,

        @Pattern(
                regexp = "^[0-9+]{8,15}$",
                message = "Invalid phone"
        )
        String phone,

        Boolean enabled,

        Role role,

        @NotNull(message = "Department is required")
        Long departmentId
) {

}

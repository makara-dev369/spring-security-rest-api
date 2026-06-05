package com.makara.HRManagerAPI.dto.request;
import com.makara.HRManagerAPI.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50,
                message = "Username must be 3-50 characters")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100,
                message = "Password must be at least 6 characters")
        String password,

        @NotBlank(message = "Full name is required")
        @Size(max = 100,
                message = "Full name max 100 characters")
        String fullName,

        @NotBlank(message = "Phone is required")
        @Pattern(
                regexp = "^[0-9+]{8,15}$",
                message = "Invalid phone number"
        )
        String phone,

        @NotNull(message = "Enabled status is required")
        Boolean enabled,

        @NotNull(message = "Role is required")
        Role role,

        Long departmentId

) {
}
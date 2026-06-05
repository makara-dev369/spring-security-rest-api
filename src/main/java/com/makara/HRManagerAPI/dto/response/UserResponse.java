package com.makara.HRManagerAPI.dto.response;
import com.makara.HRManagerAPI.model.Role;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,

        String username,

        String fullName,

        String phone,

        Boolean enabled,

        Role role,

        Long departmentId,

        String departmentName,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}

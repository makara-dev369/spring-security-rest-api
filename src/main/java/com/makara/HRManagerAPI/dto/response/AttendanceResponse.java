package com.makara.HRManagerAPI.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AttendanceResponse(
        Long id,
        String userId,
        String userName,
        LocalDate workDate,
        LocalDateTime checkInTime,
        LocalDateTime checkOutTime,
        String checkInType,
        String status,
        BigDecimal lat,
        BigDecimal lng,
        String qrCode,
        LocalDateTime createdAt
) {

}

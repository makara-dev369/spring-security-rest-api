package com.makara.HRManagerAPI.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AttendanceRequest(
         Long userId,
         LocalDate workDate,
         LocalDateTime checkInTime,
         LocalDateTime checkOutTime,
         String checkInType,
         BigDecimal lat,
         BigDecimal lng,
         String status,
         String qrCode
) {

}

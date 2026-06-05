package com.makara.HRManagerAPI.service;

import com.makara.HRManagerAPI.dto.request.AttendanceRequest;
import com.makara.HRManagerAPI.dto.response.AttendanceResponse;
import com.makara.HRManagerAPI.model.Attendance;
import com.makara.HRManagerAPI.repo.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceResponse create(AttendanceRequest request){
        Attendance attendance = new Attendance();
        attendance.setLat(request.lat());
        attendance.setLng(request.lng());
        Attendance saved = attendanceRepository.save(attendance);
        return mapAttendanceReponse(saved);
    }

    private AttendanceResponse mapAttendanceReponse(Attendance attendance){
        return new AttendanceResponse(
                attendance.getId(),
                attendance.getUser().getUsername(),
                attendance.getCheckInType(),
                attendance.getWorkDate(),
                attendance.getCheckInTime(),
                attendance.getCheckOutTime(),
                attendance.getCheckInType(),
                attendance.getStatus(),
                attendance.getLat(),
                attendance.getLng(),
                attendance.getQrCode(),
                attendance.getCreatedAt()
        );
    };

}

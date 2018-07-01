package com.sise.dao;

import com.sise.po.Appointment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 11:06 2018/2/15
 */
@Repository
public interface AppointmentDao {

    public List<Appointment> findAppointmentAllByRegistrationId(@Param("registrationId") Integer registrationId);

    public Integer insertAppointment(Appointment appointment);

    public Integer updateAppointment(Appointment appointment);


    public Integer deleteAppointment(@Param("appointmentId") Integer appointmentId);

    public Appointment findAppointmentById(@Param("appointmentId") Integer appointmentId);


}

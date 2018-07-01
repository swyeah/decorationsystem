package com.sise.service;

import com.sise.po.Appointment;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 17:43 2018/2/16
 */
public interface AppointmentService{

    public List<Appointment> searchAppointmentByRegistrationId(Integer registrationId);

    public Integer addAppointment(Appointment appointment);

    public Integer changeAppointment(Appointment appointment);


    public Integer dropAppointmentById(Integer appointmentId);

    public Appointment findAppointmentById(Integer appointmentId);
}

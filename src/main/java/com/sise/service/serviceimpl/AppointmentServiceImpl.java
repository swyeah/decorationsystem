package com.sise.service.serviceimpl;

import com.sise.dao.AppointmentDao;
import com.sise.po.Appointment;
import com.sise.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 17:45 2018/2/16
 */
@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentDao appointmentDao;


    public List<Appointment> searchAppointmentByRegistrationId(Integer registrationId) {
        return appointmentDao.findAppointmentAllByRegistrationId(registrationId);
    }

    public Integer addAppointment(Appointment appointment) {
        return appointmentDao.insertAppointment(appointment);
    }

    public Integer changeAppointment(Appointment appointment) {
        return appointmentDao.updateAppointment(appointment);
    }


    public Integer dropAppointmentById(Integer appointmentId) {
        return appointmentDao.deleteAppointment(appointmentId);
    }

    public Appointment findAppointmentById(Integer appointmentId) {
        return appointmentDao.findAppointmentById(appointmentId);
    }
}

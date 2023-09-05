package com.codewithdulan.thejobs.service;

import com.codewithdulan.thejobs.dao.appointmentDao;
import com.codewithdulan.thejobs.model.appoinment;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class appoinmentServiceTest {

    @Mock
    private appointmentDao appointmentDao;

    @Spy
    @InjectMocks
    private appoinmentService appoinmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAppoinmentByID() throws Exception {
        appoinment mockAppointment = new appoinment();
        mockAppointment.setUserID(1);
        when(appointmentDao.getTheAppoinmentsById(7)).thenReturn(mockAppointment);
        appoinment result = appoinmentService.getAppoinmentByID(7);
        assertNotNull(result);
        assertEquals(1, result.getUserID());
    }

    @Test
    public void testGetAllAppoinments() throws Exception {
        // Create mock data
        List<appoinment> mockAppointments = new ArrayList<>();
        mockAppointments.add(new appoinment());
        mockAppointments.add(new appoinment());
        when(appointmentDao.getAllAppoinments()).thenReturn(mockAppointments);

        List<appoinment> result = appoinmentService.getAllAppoinments();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllAppoinmentsJobSeeker() throws Exception {
        // Create mock data
        List<appoinment> mockAppointments = new ArrayList<>();
        mockAppointments.add(new appoinment());
        mockAppointments.add(new appoinment());
        when(appointmentDao.getAllAppoinmentsJobSeeker(1)).thenReturn(mockAppointments);

        List<appoinment> result = appoinmentService.getAllAppoinmentsJobSeeker(1);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllAppoinmentsConsultant() throws Exception {
        // Create mock data
        List<appoinment> mockAppointments = new ArrayList<>();
        mockAppointments.add(new appoinment());
        mockAppointments.add(new appoinment());
        when(appointmentDao.getAllAppoinmentsConsultant(1)).thenReturn(mockAppointments);

        List<appoinment> result = appoinmentService.getAllAppoinmentsConsultant(1);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteAppointment() throws Exception {
        when(appointmentDao.deleteAppointment(1)).thenReturn(true);

        boolean result = appoinmentService.deleteAppointment(1);

        assertTrue(result);
    }

    @Test
    public void testUpdateAppointment() throws Exception {
        appoinment mockAppointment = new appoinment();
        when(appointmentDao.updateAppoinment(mockAppointment)).thenReturn(true);

        boolean result = appoinmentService.updateAppointment(mockAppointment);

        assertTrue(result);
    }

    @Test
    public void testUpdateAdminAppointment() throws Exception {
        appoinment mockAppointment = new appoinment();
        when(appointmentDao.updateAdminAppoinment(mockAppointment)).thenReturn(true);

        boolean result = appoinmentService.updateAdminAppointment(mockAppointment);

        assertTrue(result);
    }

    @Test
    public void testAcceptAppointment() throws Exception {
        appoinment mockAppointment = new appoinment();
        when(appointmentDao.acceptAppoinment(mockAppointment)).thenReturn(true);

        boolean result = appoinmentService.acceptAppointment(mockAppointment);

        assertTrue(result);
    }
}

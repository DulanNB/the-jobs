package com.codewithdulan.thejobs.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;


import com.codewithdulan.thejobs.model.appoinment;

public class appoinmentServiceTest {

    private appoinmentService appoinmentService;
    private List<appoinment> fakeAppointments;

    @Before
    public void setUp() {
        fakeAppointments = new ArrayList<>();
        appoinmentService = new appoinmentService();
    }

    @Test
    public void testGetAppoinmentByID() throws ClassNotFoundException, SQLException {
        appoinment fakeAppointment = new appoinment();
        fakeAppointment.setAppoinmentID(7);
        fakeAppointment.setUserID(123);
        fakeAppointments.add(fakeAppointment);
        appoinment result = appoinmentService.getAppoinmentByID(123);
        assertNotNull(result);
        assertEquals(7, result.getAppoinmentID());
    }

    @Test
    public void testGetAllAppoinments() throws ClassNotFoundException, SQLException {
        appoinment fakeAppointment1 = new appoinment();
        fakeAppointment1.setAppoinmentID(1);
        fakeAppointment1.setUserID(123);
        appoinment fakeAppointment2 = new appoinment();
        fakeAppointment2.setAppoinmentID(2);
        fakeAppointment2.setUserID(456);
        fakeAppointments.add(fakeAppointment1);
        fakeAppointments.add(fakeAppointment2);
        List<appoinment> result = appoinmentService.getAllAppoinments();
        assertNotNull(result);
        
    }

    @Test
    public void testGetAllAppoinmentsJobSeeker() throws ClassNotFoundException, SQLException {
        appoinment fakeAppointment1 = new appoinment();
        fakeAppointment1.setAppoinmentID(1);
        fakeAppointment1.setUserID(123);
        appoinment fakeAppointment2 = new appoinment();
        fakeAppointment2.setAppoinmentID(2);
        fakeAppointment2.setUserID(123); // Same user ID
        fakeAppointments.add(fakeAppointment1);
        fakeAppointments.add(fakeAppointment2);
        
        List<appoinment> result = appoinmentService.getAllAppoinmentsJobSeeker(123); // Pass the user ID
        assertNotNull(result);
       
    }

    @Test
    public void testGetAllAppoinmentsConsultant() throws ClassNotFoundException, SQLException {
        appoinment fakeAppointment1 = new appoinment();
        fakeAppointment1.setAppoinmentID(1);
        fakeAppointment1.setConsultantId(456);
        appoinment fakeAppointment2 = new appoinment();
        fakeAppointment2.setAppoinmentID(2);
        fakeAppointment2.setConsultantId(456); // Same consultant ID
        fakeAppointments.add(fakeAppointment1);
        fakeAppointments.add(fakeAppointment2);
        
        List<appoinment> result = appoinmentService.getAllAppoinmentsConsultant(456); // Pass the consultant ID
        assertNotNull(result);
       
    }

    @Test
    public void testAddAppoinment() throws ClassNotFoundException, SQLException {
        appoinment fakeAppointment = new appoinment();
        assertTrue(appoinmentService.addAppoinment(fakeAppointment));
    }

//    @Test
//    public void testDeleteAppointment() throws ClassNotFoundException, SQLException {
//        int appoinmentIDToDelete = 1;
//        assertTrue(appoinmentService.deleteAppointment(appoinmentIDToDelete));
//    }

    @Test
    public void testUpdateAppointment() throws ClassNotFoundException, SQLException {
        appoinment fakeAppointment = new appoinment();
        assertTrue(appoinmentService.updateAppointment(fakeAppointment));
    }

    @Test
    public void testUpdateAdminAppointment() throws ClassNotFoundException, SQLException {
        appoinment fakeAppointment = new appoinment();
        assertTrue(appoinmentService.updateAdminAppointment(fakeAppointment));
    }

    @Test
    public void testAcceptAppointment() throws ClassNotFoundException, SQLException, MessagingException {
        appoinment fakeAppointment = new appoinment();
        try {
			assertTrue(appoinmentService.acceptAppointment(fakeAppointment));
		} catch (ClassNotFoundException | SQLException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
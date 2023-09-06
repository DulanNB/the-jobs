package com.codewithdulan.thejobs.service;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codewithdulan.thejobs.model.User;

public class userServiceTest {

    private userService userService;
    private List<User> fakeUsers;

    @Before
    public void setUp() {
        fakeUsers = new ArrayList<>();
        userService = new userService();
    }

    @Test
    public void testGetSpecificUserByUserId() throws ClassNotFoundException, SQLException {
       
        User fakeUser = new User("TestUser", "test@example.com", "123456789", "password", 1);
      
        User result = userService.getSpecifiUserByUserId(fakeUser.getUserID());
        
        assertNotNull(result);
        assertEquals(fakeUser.getUserID(), result.getUserID());
        assertEquals(fakeUser.getUserName(), result.getUserName());
        assertEquals(fakeUser.getEmail(), result.getEmail());
        assertEquals(fakeUser.getContactNo(), result.getContactNo());
        assertEquals(fakeUser.getUserPassword(), result.getUserPassword());
        assertEquals(fakeUser.getRoleID(), result.getRoleID());
    }

    @Test
    public void testGetAllUsers() throws ClassNotFoundException, SQLException {  
        List<User> result = userService.getAllUsers();
        assertNotNull(result);
   
    }

    @Test
    public void testAddUser() throws ClassNotFoundException, SQLException {

        User fakeUser = new User("TestUser", "test@example.com", "123456789", "password", 1);
              assertTrue(userService.addUser(fakeUser));
        
        User result = userService.getSpecifiUserByUserId(fakeUser.getUserID());
        assertNotNull(result);
        assertEquals(fakeUser.getUserID(), result.getUserID());
        assertEquals(fakeUser.getUserName(), result.getUserName());
        assertEquals(fakeUser.getEmail(), result.getEmail());
        assertEquals(fakeUser.getContactNo(), result.getContactNo());
        assertEquals(fakeUser.getUserPassword(), result.getUserPassword());
        assertEquals(fakeUser.getRoleID(), result.getRoleID());
    }

    @Test
    public void testUpdateUser() throws ClassNotFoundException, SQLException {
        User fakeUser = new User("TestUser", "test@example.com", "123456789", "password", 1);
        int updatedRoleID = 2;
        assertTrue(userService.updateUser(fakeUser.getUserID(), updatedRoleID));
        User result = userService.getSpecifiUserByUserId(fakeUser.getUserID());
        assertNotNull(result);
        assertEquals(updatedRoleID, result.getRoleID());
    }

    @Test
    public void testDeleteUser() throws ClassNotFoundException, SQLException {
        User fakeUser = new User("TestUser", "test@example.com", "123456789", "password", 1);
        assertTrue(userService.deleteUser(fakeUser.getUserID())); 
        User result = userService.getSpecifiUserByUserId(fakeUser.getUserID());
        assertNull(result);
    }


    @Test
    public void testGetAllConsultants() throws ClassNotFoundException, SQLException {
        List<User> result = userService.getAllConsultants();
        assertNotNull(result);
      
    }
}

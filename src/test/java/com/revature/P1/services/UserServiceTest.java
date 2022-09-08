package com.revature.P1.services;

import com.revature.P1.utils.custom_exceptions.AuthenticationException;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import com.revature.P1.services.*;
import com.revature.P1.daos.*;
import com.revature.P1.servlets.*;
import com.revature.P1.dtos.requests.*;
import com.revature.P1.models.*;

public class UserServiceTest {
    private UserService user;
    private final UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {
        user = new UserService(mockUserDao);
    }

    @Test
    public void test_IsValidPassword_GivenGoodPassword() {
        //Arrange
        String validPass = "Revature90@";

        //Act
        boolean flag = user.isValidPassword(validPass);

        //Assert
        Assert.assertTrue(flag);

    }

    @Test
    public void test_isValidUsername_GivenGood() {
        String valid = "Matthew88";
        boolean flag = user.isValidUsername(valid);
        Assert.assertTrue(flag);
    }

    @Test
    public void test_isSamePassword_GivenGood() {
        String pass = "Revature123@";
        String pass2 = "Revature123@";
        boolean flag = user.isSamePassword(pass, pass2);
        Assert.assertTrue(flag);
    }

    @Test
    public void test_isValidPassword_GivenBadPass() {
        String notValid = "badPass3@";
        boolean flag = user.isValidPassword(notValid);
        Assert.assertTrue(flag);
    }

    @Test
    public void test_isValidUsername_GivenBad() {
        String bad = "davebb38";
        boolean flag = user.isValidUsername(bad);
        Assert.assertTrue(flag);
    }

    @Test
    public void test_isSamePassword_GivenBad() {
        String pass = "Revature123@";
        String pass2 = "Revature123@";
        boolean flag = user.isSamePassword(pass, pass2);
        Assert.assertTrue(flag);
    }

    @Test
    public void test_isValidRegistration_givenValidCredentials() {
        NewUserRequest request = new NewUserRequest(
                "Userrrr999",
                "myemail@gmail.com",
                "P!ssw0rd",
                "P!ssw0rd",
                "David",
                "Burr",
                "User"
        );
        user.register(request);
    }

    @Test(expected = AuthenticationException.class)
    public void test_isInvalidLogin_givenInvalidCredentials() {

        UserService spiedSut = Mockito.spy(user);
        LoginRequest invalidReq = new LoginRequest("username1", "P@ssw0rd");
        when(spiedSut.isValidUsername(invalidReq.getUsername())).thenReturn(true);
        when(spiedSut.isValidPassword(invalidReq.getPassword())).thenReturn(true);
        when(mockUserDao.getUserByUsernameAndPassword(invalidReq.getUsername(), invalidReq.getPassword())).thenReturn(null);
        user.login(invalidReq);
    }

    @Test(expected = AuthenticationException.class)
    public void test_isInvalidLogin_givenInactive() {
        String username = "Userrrr999";
        String password = "P!ssw0rd";
        ERSUsers inactiveUser = new ERSUsers(
                "5748fdhs9",
                "User999",
                "user99@gmail.com",
                "P!ssw0rd",
                "Dave",
                "Burr",
                false,
                null
        );
        when(mockUserDao.getUserByUsernameAndPassword(username, password)).thenReturn(inactiveUser);
        LoginRequest invalidReq = new LoginRequest(username, password);

        user.login(invalidReq);


    }
    @Test(expected = AuthenticationException.class)
    public void test_isInvalidLogin_givenActive() {
        String username = "Userrrr999";
        String password = "P!ssw0rd";
        ERSUsers inactiveUser = new ERSUsers(
                "5748fdhs9",
                "User999",
                "user99@gmail.com",
                "P!ssw0rd",
                "Dave",
                "Burr",
                false,
                null
        );
        when(mockUserDao.getUserByUsernameAndPassword(username, password)).thenReturn(inactiveUser);
        LoginRequest invalidReq = new LoginRequest(username, password);

        user.login(invalidReq);


    }
    @Test(expected = AuthenticationException.class)
    public void test_isInvalidLogin_givenValidCredentials() {
        UserService spiedSut = Mockito.spy(user);
        LoginRequest invalidReq = new LoginRequest("davidb123", "P$ssw0rd");
        when(spiedSut.isValidUsername(invalidReq.getUsername())).thenReturn(false);
        when(spiedSut.isValidPassword(invalidReq.getPassword())).thenReturn(false);
        when(mockUserDao.getUserByUsernameAndPassword(invalidReq.getUsername(), invalidReq.getPassword())).thenReturn(null);
        user.login(invalidReq);
    }
}
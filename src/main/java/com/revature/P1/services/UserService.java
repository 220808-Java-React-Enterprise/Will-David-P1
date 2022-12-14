package com.revature.P1.services;

import com.revature.P1.daos.UserDAO;
import com.revature.P1.dtos.requests.LoginRequest;
import com.revature.P1.dtos.requests.NewUserRequest;
import com.revature.P1.dtos.requests.ResetPasswordReq;
import com.revature.P1.dtos.responses.PrincipalResponse;
import com.revature.P1.models.ERSUsers;
import com.revature.P1.utils.custom_exceptions.AuthenticationException;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;
import com.revature.P1.utils.custom_exceptions.InvalidUserException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.math.*;
import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;

    }

    public ERSUsers register(NewUserRequest request) {
        ERSUsers user = null;

        if (isValidUsername(request.getUsername())) {
            if (!isDuplicateUsername(request.getUsername())) {
                if (isValidPassword(request.getPassword1())) {
                    if (isSamePassword(request.getPassword1(), request.getPassword2())) {
                        user = new ERSUsers(UUID.randomUUID().toString(), request.getUsername(), request.getEmail(), request.getPassword1(), request.getFirst(), request.getLast(), false, request.getRole());
                        userDAO.save(user);
                    }
                }
            }
        }

        return user;
    }

    public PrincipalResponse login(LoginRequest request) {
        ERSUsers account = userDAO.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (account == null) throw new AuthenticationException("\nIncorrect username or password.");
        if (!account.isActive()) throw new AuthenticationException("\nAccount not yet approved.");
        return new PrincipalResponse(account.getuID(), account.getuName(), account.getEmail(), account.getFirst(), account.getLast(), account.isActive(), account.getRole());
    }

    public boolean isDuplicateUsername(String username) {
        if (userDAO.getUsername(username) != null) throw new InvalidUserException("\nSorry, " + username + " already been taken :(");
        return false;
    }

    public boolean isSamePassword(String pass, String pass2) {
        if (!pass.equals(pass2)) throw new InvalidUserException("\nPassword do not match");
        return true;
    }

    public boolean isValidUsername(String username) {
        if (!username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$"))
            throw new InvalidUserException("\nInvalid username! Username must be 8-20 characters long. no _ or . at the beginning. no __ or _. or ._ or .. inside!");
        return true;
    }

    public boolean isValidPassword(String pass) {
        if (pass.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) throw new InvalidUserException("\nInvalid password! Minimum eight characters, at least one letter, one number, and a special character include a special character(@,$,#.etc).");
        return true;
    }

    public ERSUsers getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public void saveAcc(ERSUsers account) {
        userDAO.save(account);
    }

    public List<ERSUsers> getAllAccounts() {
        return userDAO.getAll();
    }

    public void activate(String id, boolean status) {
        userDAO.setStatus(id, status);
    }

    public void resetPassword(ResetPasswordReq r) {
        if(isValidPassword(r.getPassword1())){
            if(isSamePassword(r.getPassword1(),r.getPassword2())){
                userDAO.resetP(r.getId(), r.getPassword1());
            }
        }

    }

    public static byte[] getSHA(String input){
        try{
            MessageDigest data = MessageDigest.getInstance("SHA-256");

            return data.digest(input.getBytes(StandardCharsets.UTF_8));
            //digest called to calculate message digest of input
            //returns an array of byte
        }
        catch (Exception e){
            return null; //catches exceptions
        }
    }

    public static String toHexString(byte[] hash){
        BigInteger num = new BigInteger(1,hash);

        StringBuilder hexString = new StringBuilder(num.toString(16));
        //converts message into hex value
        while(hexString.length()<32){
            hexString.insert(0,'0'); //pads with 0s
        }
        return hexString.toString();
    }
}





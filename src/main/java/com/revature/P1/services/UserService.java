package com.revature.P1.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.math.*;

public class UserService {
    private final UserDAO userDAO;


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;

    }


    public User login(String username, String pass) {
        User account = userDAO.getUserByUsernameAndPassword(username, pass);

        if (account == null) throw new InvalidUserException("\nIncorrect username or password.");
        return account;
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

    public void saveAcc(User account) {
        userDAO.saveUser(account);
    }

    public List<User> getAllAccounts() {
        return userDAO.getAll();
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





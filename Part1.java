/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part1;

import java.util.Scanner;

/**
 *
 * @author Reabetswe Sepeng
 */
public class Part1 {

    public static void main(String[] args) {
        // making a scanner to get input from the user
        Scanner input = new Scanner(System.in);
    
        // making variables to store the users info
        String userName;      // this will hold the username
        String passWord;      // this will hold the password
        String phoneNum = "";    // this will hold the phone number
        
        // printing a title for the registration
        System.out.println("------Register------");
        
        // PHONE NUMBER PART
        // making a boolean to check if phone number is valid or not
        boolean validPhone = false;
        
        // keep asking for phone number until user gets it right
        while (!validPhone) {   
            // ask user to type their phone number
            System.out.println("Please enter your cellphone number");
            // store what user types in the phoneNum variable
            phoneNum = input.next();
              
            // checking if phone number is valid
            // first option: 10 numbers and starts with 0 (like 0812345678)
            // second option: 12 numbers and starts with +27 (like +27812345678)
            if((phoneNum.length() == 10 && phoneNum.startsWith("0")) || 
               (phoneNum.length() == 12 && phoneNum.startsWith("+27")))
            {
                // tell user it worked
                System.out.println("Cell phone number successfully added.");
                // change the boolean to true so we can exit the loop
                validPhone = true;
            }      
            else 
            {
                // tell user they messed up and loop will ask again
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }
        
        input.nextLine(); //this is going to fix the scanner buffer
        
        // USERNAME PART
        // calling the username method to check if username is valid
        userName = chkUserName(input);
        // tell user it worked
        System.out.println("Username successfully captured.");
        
        // PASSWORD PART
        // calling the password method to check if password is valid
        passWord = chkPassWord(input);
        // tell user it worked
        System.out.println("Password successfully captured.");
        
        // telling user registration is done
        System.out.println("------Registration Complete!------");
        System.out.println("Phone: " + phoneNum);
        System.out.println("Username: " + userName);
        System.out.println("Password: " + "********"); // hiding password for safety
        
        login(input, userName, passWord, phoneNum);
        
        input.close();
    }
    
    
    // this method checks if the password is valid
    // it takes a scanner as input and gives back a String (the password)
    public static String chkPassWord(Scanner input) {
        // variable to store the password
        String passWord;
        // boolean to check if password is valid
        boolean validPassWord = false;
        
        // keep asking until user gets it right
        while (!validPassWord) {
            // ask for password
            System.out.print("Enter your Password: ");
            // get what user types
            passWord = input.nextLine();
            
            // check if password is long enough (needs at least 8 characters)
            if(passWord.length() < 8) {
                // tell user its too short
                System.out.println("Password must be at least 8 characters long.");
                // go back and ask again
                //continue;
            }
            
            // making booleans to check different requirements
            boolean hasUpper = false;     // checks for capital letters
            boolean hasNum = false;       // checks for numbers
            boolean hasSpecial = false;   // checks for symbols like !@#$%
            
            // loop through each letter in the password to check it
            for (int i = 0; i < passWord.length(); i++) {
                // get one character from the password
                char ch = passWord.charAt(i);
                
                // check if this character is a capital letter
                if (Character.isUpperCase(ch)){
                    hasUpper = true;  // found at least one capital letter
                }
                
                // check if this character is a number
                if (Character.isDigit(ch)){
                    hasNum = true;    // found at least one number
                }
                
                // check if this character is NOT a letter AND NOT a number
                // that means it must be a special character
                if(Character.isLetterOrDigit(ch) == false){
                    hasSpecial = true; // found at least one special character
                }
            }
            
            // check if ALL requirements are met
            if (hasUpper == true && hasNum == true && hasSpecial == true) {
                // everything is good
                validPassWord = true;
                // send back the valid password
                return passWord;
            } else {
                // tell user what they missed
                if (hasUpper == false) {
                    System.out.println("Password must contain at least one uppercase letter (A-Z).");
                }
                if (hasNum == false) {
                    System.out.println("Password must contain at least one number (0-9).");
                }
                if (hasSpecial == false) {
                    System.out.println("Password must contain at least one special character (!@#$%^&* etc.).");
                }
                // loop will ask for password again
            }
        }
        
        // java needs this even though it never runs
        return null;
    }
    //Login part to 
    public static void login(Scanner input, String userName, String passWord, String phoneNum){
        
        //Store data
        String storeduserName = userName;
        String storedpassWord = passWord;
        String storedphoneNum = phoneNum;
        
    
    boolean loginSuccess = false;
    System.out.println("------LOGIN------");
    
    while(!loginSuccess){
        
        // Ask for login credentials
        System.out.print("Enter your username: ");
        String enteredUsername = input.nextLine();
        
        // Fix scanner buffer
        if (enteredUsername.isEmpty()) {
            enteredUsername = input.nextLine();
        }
        
        System.out.print("Enter your password: ");
        String enteredPassword = input.nextLine();
        
        //Check whether the entered information matches the info that is stored
        
        if (enteredUsername.equals(storeduserName) && enteredPassword.equals(storedpassWord)) {
            System.out.println("LOGIN SUCCESSFUL!");
            System.out.println("Welcome " + storeduserName + " it is great to see you again.");
            loginSuccess = true; //The loop must stop, because the user entered the correct username and password
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
        
     }
    }
    // this method checks if the username is correct
    public static String chkUserName(Scanner input) {
        
        String userName;
        boolean validUserName = false;
        
        while (!validUserName) {
            System.out.print("Create your Username: ");
            userName = input.nextLine();
            
            if (userName.isEmpty()) {
                userName = input.nextLine();
            }
            
            // Username must be at least 5 characters
            if (userName.length() < 5) {
                System.out.println("Username must be at least 5 characters long.");
                continue;
            }
            
            // Username must contain underscore
            if (userName.contains("_") == false) {
                System.out.println("Username must contain an underscore (_)");
                continue;
            }
            
            validUserName = true;
            return userName;
        }
        
        return null;
    }
        
}


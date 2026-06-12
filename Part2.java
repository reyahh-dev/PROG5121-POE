/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Reabetswe Sepeng
 */
public class Part2 {
    
    static ArrayList<Message> allMessages = new ArrayList<>();
    static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> disregardedMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();
    
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();
    
    static String currentUser = "";
        
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
        
    }
    
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
                continue;
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
    
    public static void login(Scanner input, String userName, String passWord, String phoneNum){
        
        //Store data
        String storeduserName = userName;
        String storedpassWord = passWord;
    
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
            
            currentUser = storeduserName;
            loginSuccess = true; //The loop must stop, because the user entered the correct username and password
        
            if (loginSuccess){
            quickChatMenu(input);
            
            input.close();
        }
            
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
        
     }
    }
    
    public static void quickChatMenu(Scanner input){
    
         //The welcome message
        System.out.println("Welcome to QuickChat.");
        
        //How many messages that the user wants to send on this session
        
        int numMessages = 0;
        while (numMessages <=0){
            System.out.println("How many messages would you like to send?");
            try{
                numMessages = Integer.parseInt(input.nextLine());
                if (numMessages <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        //this is used to track messsages composed this session
        HashMap<String, Message> sessionMessages = new HashMap<>();
        int messagesSentThisSession = 0;
        boolean running = true;
        
        while (running){
            System.out.println("-----Menu-----");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");
            System.out.println("Choose an option: ");
        
            String option = input.nextLine();
            
            
        
               switch (option) {
                   
            //Option 1 is for sending messages
                   case "1":
                       if (messagesSentThisSession >= numMessages){
                           System.out.println("You have reached your message limit of " + numMessages + ".");
                        break;
                       }
                       boolean composing = true;
                       while (composing){
                           System.out.println("-----New Message (" + (messagesSentThisSession + 1) + "/" + numMessages + ") -----");
            //The recipient
            String recipient = "";
            boolean validRecipient = false;
            while (!validRecipient){
                System.out.println("Enter recipient cell number (international code, max 13 chars): ");
            recipient = input.nextLine();
            //Validate without adding to static counter
            if (recipient.matches("\\+\\d{10,13}")){
                System.out.println("Cell phone number successfully captured.");
                validRecipient = true;
            }else{
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code.");        
    }
}
            //The message text
            String messageText ="";
            boolean validMessage = false;
            while (!validMessage){
                System.out.println("Enter your message (max 250 characters): ");
                messageText = input.nextLine();
                if (messageText.length() <= 250){
                    System.out.println("Message sent.");
                    validMessage = true;
                }else{
                    System.out.println("Please enter a message of less than 250 characters.");
                }
            }
            
            Message msg = new Message(recipient, messageText);
            
            //This is where the results are going to be displayed
                           System.out.println("MessageID: " + msg.getMessageID());
                           System.out.println("Message Hash: " + msg.getMessageHash());
                           System.out.println("Recipient: " + msg.getRecipient());
                           System.out.println("Message: " + msg.getMessageText());
                           
            //Options whereby you decide what to to with the message
            
                           System.out.println("What would you like to do?");
                           System.out.println("1) Send Message");
                           System.out.println("2) Disregard Message");
                           System.out.println("3) Store Message to send later");
                           System.out.println("Choose: ");
                           
                           int sendOption = -1;
                           
                           try {
                               sendOption = Integer.parseInt(input.nextLine());
                           } catch (NumberFormatException e) {
                               System.out.println("Invalid input, defaulting to disregard.");
                           }
            String result = msg.sentMessage(sendOption);
                           System.out.println(result);
            if (sendOption == 3){
                saveMessageToJSON(msg);
            }               
                           
            messageHashes.add(msg.getMessageHash());
            messageIDs.add(msg.getMessageID());
            
            if (sendOption == 1){
                sentMessages.add(msg);
            }
            else if (sendOption == 2){
                disregardedMessages.add(msg);
            }
            else if (sendOption == 3){
                storedMessages.add(msg);
            }
                           
            //This adds every composed message to the local session HashMap
            sessionMessages.put(msg.getMessageID(), msg);
            allMessages.add(msg);
            if (sendOption == 1 || sendOption == 3){
            messagesSentThisSession++;
            }
            
            //The input for deleting the message
            if (sendOption == 2){
                System.out.println("Press 0 to delete message:");
                String deleteChoice = input.nextLine();
                
            if (deleteChoice.equals("0")){
                System.out.println("Message deleted.");
                continue;
            } else {
                System.out.println("Message was not deleted.");
            }
         }          
            if (messagesSentThisSession >= numMessages){
                System.out.println("You have used all " + numMessages + " message(s).");
               composing = false;
            break;    
            }
          }
                 System.out.println("Total messages successfully sent: " + Message.returnTotalMessages());
            break;       
            
            //Option 2 it shows recently sent messages
                    case "2":
                        if (allMessages.isEmpty()){
                       System.out.println("No messages available.");
               }else{
                   System.out.println("-----Recently Sent Messages-----");
                   
                   for(Message m : allMessages){
                       System.out.println("Message ID: "+ m.getMessageID());
                       System.out.println("Recipient: "+ m.getRecipient());
                       System.out.println("Message: "+ m.getMessageText());
                       System.out.println("Status: "+ m.getStatus());
                       System.out.println("-------------------------");
                       
                       }
                   }
                       break;
                       
            //Option 3 is the quit option
                    case "3":
                        running = false;
                        System.out.println("Goodbye, " + currentUser + "!");
                       
                        System.out.println("-----Session Summary-----");
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());
                        
                        if (sessionMessages.isEmpty()){
                            System.out.println("No messages composed this session.");
                        }else{
                            System.out.println("-----All composed messages-----");
                            for (Map.Entry<String, Message> entry: sessionMessages.entrySet()){
                                Message m = entry.getValue();
                                System.out.println("MessageID: " + m.getMessageID());
                                System.out.println("Message Hash: " + m.getMessageHash());
                                System.out.println("Recipient: " + m.getRecipient());
                                System.out.println("Message: " + m.getMessageText());
                                System.out.println("Status: " + m.getStatus());
                                System.out.println("-----------------------");   
                            }
                        }
                       break;
                    default:
                        System.out.println("Invalid option. Please choose 1,2, or 3.");
               
                    case "4":
                        storedMessagesMenu(input);
                        break;
               
               }
        }
    }
            public static void storedMessagesMenu(Scanner input){
                
                boolean running = true;
                
                while(running){
                    
                    System.out.println("-----Stored Messages-----");
                    System.out.println("1. Display sender and recipient");
                    System.out.println("2. Display longest stored message");
                    System.out.println("3. Search by Message ID");
                    System.out.println("4. Search by Recipient");
                    System.out.println("5. Delete by Message Hash");
                    System.out.println("6. Full report");
                    System.out.println("7. Back");
                    
                    String choice = input.nextLine();
                    
                    switch(choice){
                        
                        case "1":
                            displayStoredSendersRecipients();
                            break;
                        
                        case "2":
                            displayLongestStoredMessages();
                            break;
                            
                        case "3":
                            searchByMessageID(input);
                            break;
                            
                        case "4":
                            searchByRecipient(input);
                            break;
                            
                        case "5":
                            deleteByHash(input);
                            break;
                            
                        case "6":
                            displayReport();
                            break;
                            
                        case "7":
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                                
                    }
                }
            }
                
                    public static void displayStoredSendersRecipients(){
                        
                        for(Message msg : storedMessages){
                            
                            System.out.println("Sender: "+ currentUser);
                            System.out.println("Recipient: "+ msg.getRecipient());
                            System.out.println("----------------------");
                        }
                    }
                    
        public static void displayLongestStoredMessages(){
                        
                        if(storedMessages.isEmpty()){
                            System.out.println("No stored messages.");
                            return;
                        }
                  Message longest = storedMessages.get(0);
                  
                  for(Message msg : storedMessages){
                      
                      if(msg.getMessageText().length()>
                              longest.getMessageText().length()){
                          
                          longest = msg;
                      }
                }
                  
                        System.out.println("Longest Stored Message");
                        System.out.println(longest.getMessageText());
            }
        
        public static void searchByMessageID(Scanner input){

                    System.out.println("Enter Message ID:");
                    String id = input.nextLine();

                for(Message msg : storedMessages){
                 if(msg.getMessageID().equals(id)){
                   System.out.println("Recipient: " + msg.getRecipient());
                   System.out.println("Message: " + msg.getMessageText());
                return;
        }
    }

            System.out.println("Message not found.");
}
        
        public static void searchByRecipient(Scanner input){
            
            System.out.println("Enter recipient: ");
            String recipient = input.nextLine();
            
            boolean found = false;
            
            for(Message msg : storedMessages){
                
                if(msg.getRecipient().equals(recipient)){
                    System.out.println(msg.getMessageText());
                    
                    found = true;
                }
            }
            if(!found){
                System.out.println("No messages found.");
            }
        }
        
        public static void deleteByHash(Scanner input){
            
            System.out.println("Enter Message Hash: ");
            String hash = input.nextLine();
            
            for(int i = 0; i < storedMessages.size(); i++){
                
                if(storedMessages.get(i).getMessageHash().equals(hash)){
                    storedMessages.remove(i);
                    
                    System.out.println("Message deleted.");
                    return;
                }
            }
            System.out.println("Hash not found.");
        }
        
        public static void displayReport(){
            
            System.out.println("-----Stored Message Report-----");
            
            for(Message msg : storedMessages){
                
                System.out.println("Message ID: "+ msg.getMessageID());
                System.out.println("Message Hash: "+ msg.getMessageHash());
                System.out.println("Sender: "+ currentUser);
                System.out.println("Recipient: "+ msg.getRecipient());
                System.out.println("Message: "+ msg.getMessageText());
                System.out.println("----------------------------------");
            }
        }
        
        public static void saveMessageToJSON(Message msg){
        
        try {
            FileWriter writer = new FileWriter("messages.json", true);
            writer.write(
            "{\n" + "\"messageID\":\"" + msg.getMessageID() + "\",\n" + "\"messageHash\":\"" + msg.getMessageHash() + "\",\n" + "\"recipient\":\"" + msg.getRecipient() + "\",\n" + "\"messageText\":\"" + msg.getMessageText() + "\",\n" + "\"status\":\"" + msg.getStatus() + "\"\n" + "}\n");
        writer.close();
            System.out.println("Message saved to JSON file.");
        } catch (IOException e){
            System.out.println("Error saving message.");
        }
       }
        
        }
    
       




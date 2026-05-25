/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Reabetswe Sepeng
 */
public class Part2 {
    
    static HashMap<String, String> userStore = new HashMap<>();
    static boolean loggedIn = false;
    static String currentUser = "";
        
        static {
            //A pre-registered user from part 1
            userStore.put("kyl_1", "Ch&&sec@ke99!");
        }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String userName;      // this will hold the username
        String passWord;      // this will hold the password
        
        //Login part
        
        while (!loggedIn){
        System.out.println("-----QuickChat Login-----");
        System.out.println("Username: ");
        userName = input.nextLine();
        System.out.println("Password: ");
        passWord = input.nextLine();
        
        if (userStore.containsKey(userName)&& userStore.get(userName).equals(passWord)){
            loggedIn = true;
            currentUser = userName;
            System.out.println("Login successful! Welcome, " + currentUser + ".");
        }else{
            System.out.println("Invalid credentials. Please try again.");
            
        }
     }
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
            //This adds every composed message to the local session HashMap
            sessionMessages.put(msg.getMessageID(), msg);
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
                       System.out.println("Coming Soon.");
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
                    }    
                   
               }
    }
}






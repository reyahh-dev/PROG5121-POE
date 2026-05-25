/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part2;

import java.util.Random;

/**
 *
 * @author Reabetswe Sepeng
 */
public class Message {
    
    //Fields
    String messageID;
    String messageHash;
    String recipient;
    String messageText;
    String status;
    
    //Counter for the total messages
    public static int totalMessages = 0;
    
    //Constructor
    public Message(String recipient, String messageText){
        
        this.recipient = recipient;
        this.messageText = messageText;
        
    //This generates random Message ID
    Random random = new Random();
    this.messageID = String.valueOf(100000 + random.nextInt(900000));
    
    //Generate message Hash
    this.messageHash = createMessageHash();
    
    //Default status
    this.status = "Created";
    }
    
    String createMessageHash(){
        
        String[] words = messageText.split(" ");
        
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        return messageID.substring(0, 2) + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    } 
     
    //Send message method
    public String sentMessage(int option){
        
        switch(option){
            case 1:
                status = "Sent";
                totalMessages++;
                return "Message successfully sent.";
                
            case 2:
                status = "Disregarded";
                return "Message disregarded.";
               
            case 3:
                status = "Stored";
                return "Message successfully stored.";
                
            default:
                status = "Unknown";
                return "Invalid option selected.";
        }
    }
    
    //This returns the total messages sent
    public static int returnTotalMessages(){
        return totalMessages;
    }
    //Getters
    public String getMessageID(){
        return messageID;
    }
    
    public String getMessageHash(){
        return messageHash;
    }
    
    public String getRecipient(){
        return recipient;
    }
    
    public String getMessageText(){
        return messageText;
    }
    public String getStatus(){
        return status;
    }
}

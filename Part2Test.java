/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.part2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Reabetswe Sepeng
 */
public class Part2Test {
    
    public Part2Test() {
    }
        public static boolean isValidUsername(String username){
            return username.length() >= 5 && username.contains("_");
        }
        
        @Test
        public void testValidUsername(){
            assertTrue(Part2Test.isValidUsername("kyl_1"));
        }
        
        @Test
        public void testInvalidUsernameNoUnderscore(){
            assertFalse(Part2Test.isValidUsername("kyle1"));
        }
        
        @Test
        public void testInvalidUsernameTooShort(){
            assertFalse(Part2Test.isValidUsername("ky_"));
        }
        
        public static boolean isValidPassword(String password){
            
            if(password.length() <8)
                return false;
            
            boolean hasUpper = false;
            boolean hasNum = false;
            boolean hasSpecial = false;
            
            for(char ch : password.toCharArray()){
                
                if(Character.isUpperCase(ch))
                    hasUpper = true;
                
                if(Character.isDigit(ch))
                    hasNum = true;
                
                if(!Character.isLetterOrDigit(ch))
                    hasSpecial = true;
            }
            return hasUpper && hasNum && hasSpecial;
        }
        
        @Test
        public void testValidPassword(){
            assertTrue(Part2Test.isValidPassword("Ch&&sec@ke99!"));
        }
        
        @Test
        public void testPasswordTooShort(){
            assertFalse(Part2Test.isValidPassword("Ab1!"));
        }
        
        @Test
        public void testPasswordMissingUppercase(){
            assertFalse(Part2Test.isValidPassword("password1!"));
        }
        
        @Test
        public void testPasswordMissingNumber(){
            assertFalse(Part2Test.isValidPassword("Password!"));
        }
        
        @Test
        public void testPasswordMissingSpecialCharacter(){
            assertFalse(Part2Test.isValidPassword("Password1"));
        }
        
        @Test
        public void testMessageCreated(){
            Message msg = new Message("+27836594589", "Hello");
            assertEquals("+27836594589", msg.getRecipient());
            assertEquals("Hello", msg.getMessageText());
        }
        
        @Test
        public void testMessageLengthValid(){
            String msg = "This is a message for testing";
            assertTrue(msg.length() <= 250);
        }
        
        @Test
        public void testSendMessage(){
            Message msg = new Message("+27836594589", "Hello");
            String result = msg.sentMessage(1);
            assertEquals("Message successfully sent.",result);
        }
        
        @Test
        public void testDisregardMessage(){
            Message msg = new Message("+27836594589", "Hello");
            String result = msg.sentMessage(2);
            assertEquals("Message disregarded.",result);
        }
        
        @Test
        public void testStoreMessage(){
            Message msg = new Message("+27836594589", "Hello");
            String result = msg.sentMessage(3);
            assertEquals("Message successfully stored.",result);
        }
        
        @Test
        public void testTotalMessages(){
            int before = Message.returnTotalMessages();
            Message msg = new Message("+27836594589", "Hello");
            msg.sentMessage(1);
            
            int after = Message.returnTotalMessages();
            assertEquals(before + 1, after);
        }
        
        @Test
        public void testMessageHashCreated(){
            Message msg = new Message("+27836594589", "Hello");
            assertNotNull(msg.getMessageHash());
        }
        
        @Test
        public void testMessageIDCreated(){
            Message msg = new Message("+27836594589", "Hello");
            assertNotNull(msg.getMessageID());
        }
        
        @Test
        public void testDefaultStatus(){
            Message msg = new Message("+27836594589", "Hello");
            assertEquals("Created",msg.getStatus());
        }
        
        @Test
        public void testSentStatus(){
            Message msg = new Message("+27836594589", "Hello");
            msg.sentMessage(1);
            assertEquals("Sent",msg.getStatus());
        }
        
        @Test
        public void testStoredStatus(){
            Message msg = new Message("+27836594589", "Hello");
            msg.sentMessage(3);
            assertEquals("Stored",msg.getStatus());
        }
        
        
    }
    


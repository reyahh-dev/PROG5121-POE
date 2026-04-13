/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.part1;

import java.io.StringReader;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Reabetswe Sepeng
 */
public class Part1Test {
    
    public Part1Test() {
    }

    @Test
    public void testMain() {
        Part1.main(new String[]{});
        assertTrue(true); 
       
    }

    @Test
    public void testChkPassWord() {
         Scanner input = new Scanner (new StringReader("Ch&&sec@ke99!\n")); 
         String result = Part1.chkPassWord(input);
         assertEquals("Ch&&sec@ke99!", result);
    }

    @Test
    public void testLogin() {
         Scanner input = new Scanner (new StringReader("kyl_1\nCh&&sec@ke99!\n"));
         Part1.login(input, "kyl_1", "Ch&&sec@ke99!", "0838968976");
         assertTrue(true);
         
    }

    @Test
    public void testChkUserName() {
        Scanner input = new Scanner(new StringReader("kyl_1\n"));
        String result = Part1.chkUserName(input);
        assertEquals("kyl_1", result);
    }
    
}

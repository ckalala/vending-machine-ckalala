/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.FileWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ckala
 */
public class VendingMachineDaoFileImplTest {
    
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
    }
    
    
    
    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testroster.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    
   
    
    }
    
    @Test
    public void testGetItem() throws Exception{
        // Create our method test inputs
    String itemName = "0001";
    Item item = new Item(itemName);
    item.setItemCost("1.85");
    item.setItemInv("9");
    

    
    // Get the item from the DAO
    Item retrievedItem = testDao.getItem(itemName);

    // Check the data is equal
    assertEquals(item.getItemName(),
                retrievedItem.getItemName(),
                "Checking item name.");
    assertEquals(item.getItemCost(),
                retrievedItem.getItemCost(),
                "Checking item cost.");
    assertEquals(item.getItemInv(), 
                retrievedItem.getItemInv(),
                "Checking item inventory.");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author ckala
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{
    
    private final String INVENTORY_FILE;

    public static final String DELIMITER = "::";
    
    public VendingMachineDaoFileImpl(){
        INVENTORY_FILE = "inventory.txt";
    }

    public VendingMachineDaoFileImpl(String inventoryTextFile){
        INVENTORY_FILE = inventoryTextFile;
    }
    
    private Map<String, Item> items = new HashMap<>();

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadItems();
        return new ArrayList(items.values());
    }
    
    @Override
    public List<Item> getAvailableItems() throws VendingMachinePersistenceException {
        List <Item> available = getAllItems().stream().filter((p) -> Integer.parseInt(p.getItemInv()) > 0).collect(Collectors.toList());
        return new ArrayList(available);
    }
    
    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        loadItems();
        return items.get(itemName);
    }

    @Override
    public void buyItem(String name) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item boughtItem = getItem(name);
        int currentInv = Integer.parseInt(boughtItem.getItemInv());
        String newInv = Integer.toString(currentInv - 1);
        boughtItem.setItemInv(newInv);
        writeItems();
        
        
    }
    
    
    
    private Item unmarshallItem(String itemAsText){
        // itemAsText is expecting a line read in from our file.
        
       
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in itemTokens.
        // Which should look like this:
        // ___________________
        // |    |       |    |                 
        // |Twix|$1.25  | 8  |
        // |    |       |    |  
        // -------------------
        //  [0]  [1]    [2]         [3]
        String[] itemTokens = itemAsText.split(DELIMITER);

        // Given the pattern above, the student Id is in index 0 of the array.
        String itemName = itemTokens[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        Item itemFromFile = new Item(itemName);

        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        // Index 1 - Price
        itemFromFile.setItemCost(itemTokens[1]);

        // Index 2 - Inventory
        itemFromFile.setItemInv(itemTokens[2]);

        

        // We have now created a student! Return it!
        return itemFromFile;
    }
    
    // Turning a Item object into a line of text for the inventory file.
    private String marshallItem(Item anItem){
        
        

        

        //Item name
        String itemAsText = anItem.getItemName() + DELIMITER;

        

        //Release date
        itemAsText += anItem.getItemCost() + DELIMITER;

        // MPAA Rating
        itemAsText += anItem.getItemInv();
        
        
        
       

        // Now to return the item which has been turned into text.
        return itemAsText;
    }
    
    private void loadItems() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
            
        } catch (FileNotFoundException e) {
            
            throw new VendingMachinePersistenceException("-_- Could not load inventory data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Item currentItem;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentItem = unmarshallItem(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            items.put(currentItem.getItemName(), currentItem);
        }
        // close scanner
        scanner.close();
    }
    
    private void writeItems() throws VendingMachinePersistenceException {
        
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save Item data.", e);
        }

        // Write out the DVD objects to the library file.
        
        String itemAsText;
        List <Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            // turn an item into a String
            itemAsText = marshallItem(currentItem);
            // write the Item object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    
    
    
    
}

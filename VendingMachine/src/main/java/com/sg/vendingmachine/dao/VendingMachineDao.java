/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.util.List;

/**
 *
 * @author ckala
 */
public interface VendingMachineDao {
    
    

    /**
     * Returns a List of all items in the vending machine.
     *
     * @return List containing all items in the vending machine.
     * @throws com.sg.vendingmachine.dao.VendingMachinePersistenceException
     */
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    public Item getItem(String itemName) throws VendingMachinePersistenceException;

    /**
     * Returns the student object associated with the given student id.Returns null if no such student exists
     *
     * @param name
     * @throws com.sg.vendingmachine.dao.VendingMachinePersistenceException
     */
    void buyItem(String name) throws VendingMachinePersistenceException, NoItemInventoryException;

    //Retreive items which still have inventory left in machine
    public List<Item> getAvailableItems() throws VendingMachinePersistenceException;
    
    
    
}

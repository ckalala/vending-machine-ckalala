/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ckala
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getAvailableItems() throws VendingMachinePersistenceException;
    
    public String buyItem(BigDecimal cash, int choice) throws NoItemInventoryException, 
            InsufficientFundsException, 
            VendingMachinePersistenceException;
    
    public void checkCashInsert(BigDecimal cash) throws InsufficientFundsException;
    
     public void checkCashPurchase(BigDecimal cash, String cost) throws InsufficientFundsException;
     
     public BigDecimal getPrice(String name) throws VendingMachinePersistenceException;
    
}

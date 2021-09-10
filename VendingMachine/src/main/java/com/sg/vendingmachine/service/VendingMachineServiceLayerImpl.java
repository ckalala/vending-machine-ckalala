/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;


import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ckala
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
     private VendingMachineDao dao;
     private VendingMachineAuditDao auditDao;
   
     public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
     
       @Override
        public List<Item> getAvailableItems() throws VendingMachinePersistenceException {
                return dao.getAvailableItems();
    }
        
     @Override
        public String buyItem(BigDecimal cash, int choice) throws NoItemInventoryException, 
                InsufficientFundsException, 
                VendingMachinePersistenceException{
            List<Item> choiceList = getAvailableItems();
                            Item chosenItem = choiceList.get(choice -1);
                            String itemName = chosenItem.getItemName();
                            String itemCost = chosenItem.getItemCost();
                            String itemInv = chosenItem.getItemInv();
                            
                            //Checks to make certain item still has inventory left
                            checkItemInv(itemInv);
                            
                            //Checks the cash to make sure it is sufficient for making purchases
                            checkCashPurchase(cash, itemCost);
                        
                            
                            dao.buyItem(itemName);
                            auditDao.writeAuditEntry(itemName + " PURCHASED.");
                            
                        
            return itemName;
        }
        
        //Check to see if the cash being used for purchase is enough to purchase item
        @Override
        public void checkCashPurchase(BigDecimal cash, String cost) throws InsufficientFundsException{
            
            if (cash.compareTo(new BigDecimal("0.00")) < 0 || cash.compareTo(new BigDecimal("0.00"))== 0){
                throw new InsufficientFundsException("Please insert proper amount of cash into machine.");
            } else if (cash.compareTo(new BigDecimal(cost)) < 0){
                throw new InsufficientFundsException("Not enough cash.");
            }
                
        }
        
        //Check to see if the cash inserted is above zero dollars
        @Override
        public void checkCashInsert(BigDecimal cash) throws InsufficientFundsException{
            
            if (cash.compareTo(new BigDecimal("0.00")) < 0 || cash.compareTo(new BigDecimal("0.00"))== 0){
                throw new InsufficientFundsException("Please insert proper amount of cash into machine.");
            }
                
        }
        
        public void checkItemInv(String itemInv) throws NoItemInventoryException{
            if (Integer.parseInt(itemInv) <= 0){
                throw new NoItemInventoryException("This item is not currently in stock.");
            }
        }
        
        @Override
        public BigDecimal getPrice(String name) throws VendingMachinePersistenceException{
            Item bought = dao.getItem(name);
            BigDecimal priceGive = new BigDecimal(bought.getItemCost());
            return priceGive;
        }
    
}

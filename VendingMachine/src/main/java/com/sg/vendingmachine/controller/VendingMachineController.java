/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.controller.VendingMachineController.Coins;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ckala
 */
public class VendingMachineController {
    
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    
     public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.cash = new BigDecimal("0.00");
            this.service = service;
            this.view = view;
    }
     private BigDecimal cash;
     public void run(){
         boolean keepGoing = true;
        int menuSelection = 0;
        
        
        try {
            while (keepGoing){

                listItems();
                System.out.println("Current total deposited into machine: $"+cash.toString());
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        cash = cash.add(cashMenu()).setScale(2 ,RoundingMode.HALF_UP);
                        break;
                    case 2:
                        purchaseMenu(cash);
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (VendingMachinePersistenceException | NoItemInventoryException | InsufficientFundsException e) {
            view.displayErrorMessage(e.getMessage());
            change(cash,new BigDecimal("0.00"));
            run();
        } 
     }
     
     private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
     
     //List the available inventory from the text file for selection by the user
     private void listItems() throws VendingMachinePersistenceException {
        List<Item> itemList = service.getAvailableItems();

        view.displayItemList(itemList);
    }
     
     private BigDecimal cashMenu() throws InsufficientFundsException{
         BigDecimal insertedCash = view.insertCash();
         service.checkCashInsert(insertedCash);
             
         
         return insertedCash;
         
     }
     
     //User will access this mode in order to choose their item for purchase from the available menu.
     private void purchaseMenu(BigDecimal cash) throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException{
         int itemChoice = view.selectItem();
         String boughtItem = service.buyItem(cash, itemChoice);
         BigDecimal itemPrice = service.getPrice(boughtItem);
         change(cash, itemPrice);
         view.thankYou(boughtItem);
     }
     
     public enum Coins {
        QUARTER, DIME, NICKEL, PENNY
    }
     
     
     
     private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
     
     private void exitMessage() {
        view.displayExitBanner();
    }
     
     //Calculate the change
     public void change(BigDecimal cash, BigDecimal price){
         BigDecimal remain = cash.subtract(price);
         System.out.println("Your total change is: "+remain);
         
            
             
                       
                           int numQuarters = (int) (remain.doubleValue() / 0.25);
                           remain = remain.remainder(new BigDecimal(.25)).setScale(2, RoundingMode.HALF_UP);;
                           
                           int numDimes = (int) (remain.doubleValue() / 0.10);
                           remain = remain.remainder(new BigDecimal(.10)).setScale(2, RoundingMode.HALF_UP);;
                           
                           int numNickels = (int) (remain.doubleValue() / 0.05);
                           remain = remain.remainder(new BigDecimal(.05)).setScale(2, RoundingMode.HALF_UP);    
                           
                           int numPennies = (int) (remain.doubleValue() / 0.01);
                           remain = remain.remainder(new BigDecimal(.01)).setScale(2, RoundingMode.HALF_UP);;
                           
                           System.out.println(numQuarters+" quarters.");
                           System.out.println(numDimes+" dimes.");
                           System.out.println(numNickels+" nickels.");
                           System.out.println(numPennies+" pennies.");
                   
                   
                   
                   
                   
                   resetCash();
                
         
         
     }
     
     private void resetCash(){
         cash = new BigDecimal("0.00");
     }
     
     
}

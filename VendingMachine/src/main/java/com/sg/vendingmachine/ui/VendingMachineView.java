/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ckala
 */
public class VendingMachineView {
    
     private UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Vending Machine Menu");
        io.print("1. Insert Cash");
        io.print("2. Choose Item from List ");
        io.print("3. Exit");

        return io.readInt("Please select from the above choices.", 1, 3);
    }
    
    public void displayItemList(List<Item> itemList) {
        int n = 1;
        for (Item currentItem : itemList) {
            
            String studentInfo = String.format("%s : $%s : %s",
                  currentItem.getItemName(),
                  currentItem.getItemCost(),
                  currentItem.getItemInv());
            io.print(n +" "+studentInfo);
            n++;
        }
       System.out.println("");
    }
    
    public BigDecimal insertCash(){
        BigDecimal nc = new BigDecimal(io.readString("Please enter your cash amount:"));
        return nc;
    }
    
    public int selectItem(){
        return io.readInt("Please select an item from the list: ");
    }
        
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
     public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
     
     public void thankYou(String item){
         io.readString("Thank you for purchasing a "+ item +". Please hit enter to continue.");
     }
    
}

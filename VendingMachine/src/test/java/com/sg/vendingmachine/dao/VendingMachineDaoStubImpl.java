/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ckala
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    public Item onlyItem;
    
    public VendingMachineDaoStubImpl(){
        onlyItem = new Item("Twizzlers");
        onlyItem.setItemInv("12");
        onlyItem.setItemCost("1.25");
    }
    
    public VendingMachineDaoStubImpl(Item testItem){
        this.onlyItem = testItem;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        if(itemName.equals(onlyItem.getItemName())){
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void buyItem(String name) throws VendingMachinePersistenceException, NoItemInventoryException {
        int numInv = Integer.parseInt(onlyItem.getItemInv());
        if(name.equals(onlyItem.getItemName()) && numInv > 0){
            onlyItem.setItemInv(Integer.toString(numInv - 1));
        } else {
            throw new NoItemInventoryException("No items.");
        }
    }

    @Override
    public List<Item> getAvailableItems() throws VendingMachinePersistenceException {
        List <Item> available = getAllItems().stream().filter((p) -> Integer.parseInt(p.getItemInv()) > 0).collect(Collectors.toList());
        available.add(onlyItem);
        return new ArrayList(available);
    }
    
}

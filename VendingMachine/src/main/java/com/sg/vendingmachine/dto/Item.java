/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.util.Objects;

/**
 *
 * @author ckala
 */
public class Item {
    
    private String name;
    private String cost;
    private String inv;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.cost);
        hash = 23 * hash + Objects.hashCode(this.inv);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        if (!Objects.equals(this.inv, other.inv)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", inv=" + inv + ", cost=" + cost + '}';
    }
    
    public Item(String name){
        this.name = name;
    }
    
    public String getItemName(){
        return name;
    }
    
    public void setItemInv(String inv){
        this.inv = inv;
    }
    
    public String getItemInv(){
        return inv;
    }
    
    public void setItemCost(String cost){
        this.cost = cost;
    }
    
    public String getItemCost(){
        return cost;
    }
    
}

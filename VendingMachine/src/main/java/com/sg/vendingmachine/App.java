/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.service.*;
import com.sg.vendingmachine.ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ckala
 */
public class App {
    
    public static void main (String[] args){
        // Instantiate the UserIO implementation
        //UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
       // VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the DAO
        //VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        // Instantiate the Audit DAO
        //VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        //VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        //VendingMachineController controller = new VendingMachineController(myService, myView);
        
        
        
        //Instantiate object to hold application context xml file for use
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        //Retrieve beans instantiated by the Spring application context
        VendingMachineController controller =  ctx.getBean("controller", VendingMachineController.class);
        
        // Kick off the Controller
        controller.run();
    }
    
}

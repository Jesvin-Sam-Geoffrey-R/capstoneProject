package com.wecp.medicalequipmentandtrackingsystem.controller;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Order;
import com.wecp.medicalequipmentandtrackingsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {
   
    private OrderService orderService;

    @Autowired
    public SupplierController(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping("/api/supplier/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        // get all order and return it status code 200 OK
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        
    }

    @PutMapping("/api/supplier/order/update/{orderId}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String newStatus) {
        // update order status and return updated order with status code 200 OK
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId, newStatus), HttpStatus.OK);

    }
    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        orderService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

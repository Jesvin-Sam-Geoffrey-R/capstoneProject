package com.wecp.medicalequipmentandtrackingsystem.service;


import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Order;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    
    private OrderRepository orderRepository;
    private EquipmentRepository equipmentRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, EquipmentRepository equipmentRepository){
        this.orderRepository = orderRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long orderId, String status){
        Order o1 = orderRepository.findById(orderId).get();
        if(o1 != null){
            o1.setStatus(status);
           return orderRepository.save(o1);
        }
        return null;
    }

    public Order placeOrder(Long equipmentId, Order order){
        Equipment e1 = equipmentRepository.findById(equipmentId).orElseThrow(()-> new EntityNotFoundException("No order"));
        order.setEquipment(e1);
        order.setOrderDate(new Date());
        order.setStatus("Initiated");
        return orderRepository.save(order);
    }
    public void deleteEvent(Long eventId) {
        orderRepository.deleteById(eventId);
    }
}

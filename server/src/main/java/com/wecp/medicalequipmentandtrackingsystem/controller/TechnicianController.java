package com.wecp.medicalequipmentandtrackingsystem.controller;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TechnicianController {
    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("/api/technician/maintenance")
    public ResponseEntity<List<Maintenance>> getAllMaintenance()throws SQLException{
        // Get all maintenance records and return them with status code 200 OK;
        List<Maintenance> listMaintainance = maintenanceService.getAllMaintenance();
        return new ResponseEntity<>(listMaintainance,HttpStatus.OK);
    }

    @PutMapping("/api/technician/maintenance/update/{maintenanceId}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable Long maintenanceId, @RequestBody Maintenance updatedMaintenance)throws SQLException {
        // Update the maintenance record with the given id and return updated record with status code 200 OK;
        return ResponseEntity.ok(maintenanceService.updateMaintenance(maintenanceId,updatedMaintenance));
    }

    @DeleteMapping("/api/maintainence/{id}")
    public void deleteMaintainence(@PathVariable Long id){
        //rnew ResponseEntity<Void>(maintenanceService.deleteaMaitainence(id), HttpStatus.OK);
        maintenanceService.deleteaMaitainence(id);
    }
}
package com.wecp.medicalequipmentandtrackingsystem.service;


import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.HospitalRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    public Maintenance scheduleMaintenance(Long id,Maintenance maintenance){
        Equipment obj = equipmentRepository.findById(id).get();
        maintenance.setEquipment(obj);
        return maintenanceRepository.save(maintenance);
    }

    public List<Maintenance> getAllMaintenance(){
        return maintenanceRepository.findAll();
    }

    public Maintenance updateMaintenance(Long id,Maintenance maintenance){
        Maintenance obj = maintenanceRepository.findById(id).get();
        if(obj!=null){
            obj.setScheduledDate(maintenance.getScheduledDate());
            obj.setCompletedDate(maintenance.getCompletedDate());
            obj.setDescription(maintenance.getDescription());
            obj.setStatus(maintenance.getStatus());
            return maintenanceRepository.save(obj);
        }
        return null;
    }

   public void deleteaMaitainence(Long id){
    this.maintenanceRepository.deleteById(id);
   }
}
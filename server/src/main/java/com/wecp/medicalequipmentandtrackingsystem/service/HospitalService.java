package com.wecp.medicalequipmentandtrackingsystem.service;


import java.util.List;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;
import com.wecp.medicalequipmentandtrackingsystem.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public Hospital createHospital(Hospital hospital){
        return hospitalRepository.save(hospital);
    }

    public List<Hospital>  getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // public void deleteHospital(Long id){
    //     if(hospitalRepository.findById(id) != null){
    //         hospitalRepository.deleteById(id);

    //     }
    // }

    // public Hospital updateHospital(Long id, Hospital hospital){
    //     if(hospitalRepository.findById(id) !=null){
    //         Hospital hos = new Hospital();
    //         hos.setName(hospital.getName());
    //         hos.setLocation(hospital.getLocation());
    //         hospitalRepository.save(hos);
    //         return hos;

    //     }
    //      else{
    //         return null;
    //      }
    // }


    public void deleteInfo(Long id){
        System.out.print("HospiTAL SERVICE"+id);
        hospitalRepository.deleteById(id);
        this.getAllHospitals();
        // return "Deleted Successfully";
    }
}

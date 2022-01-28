package com.example.vjezba4.controller;

import com.example.vjezba4.model.Device;
import com.example.vjezba4.model.Client;
import com.example.vjezba4.model.Records;
import com.example.vjezba4.repository.ClientRepository;
import com.example.vjezba4.repository.DeviceRepository;
import com.example.vjezba4.repository.RecordsRepository;
import com.example.vjezba4.service.ClientService;
import com.example.vjezba4.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/mqtt")
public class ControllerMqtt {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    DeviceService deviceService;
    @Autowired
    RecordsRepository recordsRepository;


    @GetMapping(path = "/clients")
    @ResponseBody
    public List<Client> getClients() throws IOException {

        return clientRepository.findAll();

    }

    @PostMapping(path = "/clients")
    @ResponseBody
    public ResponseEntity createDevice(@RequestBody Client client) {
        try {
            clientService.addClient();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/devices")
    @ResponseBody
    public List<Device> getDevices() throws IOException {

        return deviceRepository.findAll();

    }
    @PostMapping(path = "/devices")
    @ResponseBody
    public ResponseEntity createDevice(@RequestBody Device device) {
        try {
            deviceService.addDevice();
            return ResponseEntity.status(HttpStatus.OK).body(deviceService.addDevice());
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

//RADI!
    @PostMapping("/generaterecords/{param}")
    public ResponseEntity<Device> generate(@PathVariable("param")int id){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.setRecords(id));
    }

//RADI!
    @GetMapping("/recordspermonth/{param}")
    public ResponseEntity<Map<Integer, Map<String, Integer>>> month(@PathVariable("param")int year)
    {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.perMonth(year));
    }


    @GetMapping("/recordsperyear/{param}")
    public ResponseEntity<Map<Integer, Integer>> pyear(@PathVariable("param")int year)
    {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.perYear(year));
    }

    @GetMapping("/recordsmonthyear/{param1}/{param2}")
    public ResponseEntity<Map<String,Integer>> miks(@PathVariable("param1")int year, @PathVariable("param2")String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        return ResponseEntity.status(HttpStatus.OK).body(deviceService.perMonthperYear(year,name));
    }

    @GetMapping("/listdeviceid")
    public ResponseEntity<Object> id(@RequestParam ("year")Integer year, @RequestParam ("month") String month, @RequestParam ("aggr") String aggr) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if(month.equals("null") && aggr.equals("false"))
        {
            return ResponseEntity.status(HttpStatus.OK).body(deviceService.perYear(year));
        }
        else if(aggr.equals("false"))
        {
            return ResponseEntity.status(HttpStatus.OK).body(deviceService.perMonthperYear(year,month));
        }
        else if(aggr.equals("true"))
        {
            return ResponseEntity.status(HttpStatus.OK).body(deviceService.perMonth(year));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
        }
    }

}


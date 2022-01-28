package com.example.vjezba4.service;

import com.example.vjezba4.model.Client;
import com.example.vjezba4.model.Device;
import com.example.vjezba4.model.Records;
import com.example.vjezba4.repository.DeviceRepository;
//import com.example.vjezba4.repository.RecordsRepository;
import com.example.vjezba4.repository.RecordsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IDeviceService implements DeviceService {
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    RecordsRepository recordsRepository;

    public List<Device> addDevice() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Device> deviceList = objectMapper.readValue(new File("C:\\Users\\Tin\\IdeaProjects\\vjezba4\\vjezba4\\devices.json"),
                new TypeReference<List<Device>>(){});
        List<Device> result = new ArrayList<>();

        for(int i=0;i<deviceList.size();i++) {
            deviceRepository.save(deviceList.get(i));
            result.add(deviceList.get(i));
        }
        return result;
    }

    public Device setRecords(int id){
        List<Records> recordsList=new ArrayList<>();
        int max = 10;
        int min = 1;
        int range = max - min + 1;

        for(int i=2000;i<=2022;i++){
            Records recordsTemp= new Records();
            int rand = (int)(Math.random() * range) + min;


            recordsTemp.setYear(i);
            recordsTemp.setJanuary(rand*1);
            recordsTemp.setFebruary(rand*3);
            recordsTemp.setMarch(rand*5);
            recordsTemp.setApril(rand*7);
            recordsTemp.setMay(rand*2);
            recordsTemp.setJune(rand*15);
            recordsTemp.setJuly(rand*10);
            recordsTemp.setAugust(rand*9);
            recordsTemp.setSeptember(rand*14);
            recordsTemp.setOctober(rand*4);
            recordsTemp.setNovember(rand*8);
            recordsTemp.setDecember(rand*17);
            recordsList.add(recordsTemp);

        }
        deviceRepository.findById(id).get().setRecordings(recordsList);
        deviceRepository.save(deviceRepository.findById(id).get());
        return deviceRepository.findById(id).get();
    }

    public Map<Integer, Map<String, Integer>> perYearperMonth(int year)
    {
        Map<Integer, Map<String, Integer>> allReadings = perMonth(year);
        Map<String,Integer> yearReadings = allReadings.get(year);
        Map<Integer,Map<String,Integer>> records = new HashMap<>();
        Map<String,Integer> monthRecords = new HashMap<>();

        monthRecords.put("January",yearReadings.get("January"));
        monthRecords.put("February",yearReadings.get("February"));
        monthRecords.put("March",yearReadings.get("March"));
        monthRecords.put("April",yearReadings.get("April"));
        monthRecords.put("May",yearReadings.get("May"));
        monthRecords.put("June",yearReadings.get("June"));
        monthRecords.put("July",yearReadings.get("July"));
        monthRecords.put("August",yearReadings.get("August"));
        monthRecords.put("October",yearReadings.get("October"));
        monthRecords.put("September",yearReadings.get("September"));
        monthRecords.put("November",yearReadings.get("November"));
        monthRecords.put("December",yearReadings.get("December"));

        records.put(year,monthRecords);
        return records;
    }

    public Map<Integer, Integer> perYear(int year)
    {
        List<Records> recordsList = recordsRepository.findByYear(year);
        int total = 0;
        for (Records records : recordsList) {
            total = total +
                    records.getJanuary() +
                    records.getFebruary() +
                    records.getMarch() +
                    records.getApril() +
                    records.getMay() +
                    records.getJune() +
                    records.getJuly() +
                    records.getAugust() +
                    records.getSeptember() +
                    records.getOctober() +
                    records.getNovember() +
                    records.getDecember();
        }
        Map<Integer,Integer> result = new HashMap<>();
        result.put(year,total);
        return result;
    }

    public Map<Integer, Map<String, Integer>> perMonth(int year)
    {
        List<Records> recordsList = recordsRepository.findByYear(year);
        int january = 0;
        int february = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        int july = 0;
        int august = 0;
        int september = 0;
        int october = 0;
        int november = 0;
        int december = 0;
        for (Records records: recordsList) {
            january = january + records.getJanuary();
            february = february + records.getFebruary();
            march = march + records.getMarch();
            april = april + records.getApril();
            may = may + records.getMay();
            june = june + records.getJune();
            july = july + records.getJuly();
            august = august + records.getAugust();
            september = september + records.getSeptember();
            october = october + records.getOctober();
            november = november + records.getNovember();
            december = december + records.getDecember();
        }
        Map<String,Integer> months = new HashMap<>();
        Map<Integer,Map<String,Integer>> result = new HashMap<>();

        months.put("January",january);
        months.put("February",february);
        months.put("March",march);
        months.put("April",april);
        months.put("May",may);
        months.put("June",june);
        months.put("July",july);
        months.put("August",august);
        months.put("September",september);
        months.put("October",october);
        months.put("November",november);
        months.put("December",december);

        result.put(year,months);
        return result;
    }

    public Map<String, Integer> perMonthperYear(int year, String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Records> recordsList = recordsRepository.findByYear(year);

        int value = 0;
        for (Records records : recordsList) {
            Method method = records.getClass().getDeclaredMethod("get"+name);
            value = value +  Integer.parseInt(method.invoke(records).toString());

        }
        Map<String,Integer> result = new HashMap<>();
        result.put(name,value);
        return result;
    }
}
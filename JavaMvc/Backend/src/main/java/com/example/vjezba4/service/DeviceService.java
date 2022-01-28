package com.example.vjezba4.service;

import com.example.vjezba4.model.Device;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface DeviceService {
    List<Device> addDevice() throws IOException;
    Device setRecords(int id);
    Map<Integer,Map<String,Integer>>perYearperMonth(int year);
    Map<Integer,Map<String,Integer>>perMonth(int year);
    Map<Integer,Integer>perYear(int year);
    Map<String,Integer> perMonthperYear(int year,String name)throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}

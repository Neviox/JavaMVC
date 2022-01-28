package com.example.vjezba4.service;


import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.example.vjezba4.repository.RecordsRepository;
import com.example.vjezba4.repository.DeviceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.vjezba4.model.Client;
import com.example.vjezba4.repository.ClientRepository;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

@Service
public class IClientService implements ClientService {
    @Autowired
    ClientRepository clientRepository;



    public List<Client> addClient() throws IOException{

        ObjectMapper objectMapper = new ObjectMapper();
        List<Client> clientList = objectMapper.readValue(new File("C:\\Users\\Tin\\IdeaProjects\\vjezba4\\vjezba4\\clients.json"),
                new TypeReference<List<Client>>(){});
        List<Client> result = new ArrayList<>();

        for(int i=0;i<clientList.size();i++) {
            Client client = new Client();

            client.setName(clientList.get(i).getName());
            client.setAddress(clientList.get(i).getAddress());
        }
        for(Client clientTemp:clientList){
            if(clientRepository.findByAddress(clientTemp.getAddress()).isEmpty()){
                clientRepository.save(clientTemp);
                result.add(clientTemp);
            }
        }
        return result;
    }

    public void startMqtt() throws MqttException {

        MemoryPersistence persistence = new MemoryPersistence();

        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setMaxInflight(3000);
        connectOptions.setAutomaticReconnect(true);

    }

    @Override
    public void connectionLost(Throwable arg0) {
        System.out.println("connectionLost :" + arg0.getMessage()+" :"+arg0.toString());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("arg0 :" + topic + " arg1 :" + message.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryCompleted ");
    }
}


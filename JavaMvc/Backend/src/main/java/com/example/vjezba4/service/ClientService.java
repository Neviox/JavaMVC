package com.example.vjezba4.service;


import com.example.vjezba4.model.Client;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;
import java.util.List;

public interface ClientService extends MqttCallback {
    List<Client> addClient() throws IOException;
    void startMqtt() throws MqttException;
}

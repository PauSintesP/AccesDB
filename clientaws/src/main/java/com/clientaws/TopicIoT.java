package com.clientaws;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;

import java.sql.Connection;

public class TopicIoT extends AWSIotTopic {

    private final AccesMethodsToDB dbAccess;
    private final Connection dbConnection;

    public TopicIoT(String topic, AWSIotQos qos, AccesMethodsToDB dbAccess, Connection dbConnection) {
        super(topic, qos);
        this.dbAccess = dbAccess;
        this.dbConnection = dbConnection;
    }

    @Override
    public void onMessage(AWSIotMessage message) {
        try {
            System.out.println("Mensaje recibido: " + message.getStringPayload());
            
            // Extraer UID del mensaje
            String uid = ClientIoT.extractUid(message.getStringPayload());
            if (uid != null && !uid.isEmpty()) {
                dbAccess.insertRegistry(dbConnection, uid);
            } else {
                System.err.println("Mensaje inválido, UID no encontrado: " + message.getStringPayload());
            }
        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje IoT: " + e.getMessage());
        }
    }
}

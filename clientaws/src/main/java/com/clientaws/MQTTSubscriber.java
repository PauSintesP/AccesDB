package com.clientaws;

import software.amazon.awssdk.services.iotdataplane.IotDataPlaneClient;
import software.amazon.awssdk.services.iotdataplane.model.PublishRequest;

public class MQTTSubscriber {

    public static void subscribeToTopic(IotDataPlaneClient client, String topic) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .topic(topic)
                    .qos(1) // Nivel de calidad del servicio (QoS 1)
                    .build();

            client.publish(request);
            System.out.println("Publicado en el tópico: " + topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

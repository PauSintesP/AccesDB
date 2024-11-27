package com.clientaws;

import java.net.URI;

import software.amazon.awssdk.services.iotdataplane.IotDataPlaneClient;

public class IoTClientSetup {

    public static IotDataPlaneClient setupClient() {
        return IotDataPlaneClient.builder()
                .endpointOverride(URI.create("https://a3jyc2122j8ooj-ats.iot.us-east-1.amazonaws.com")) // Reemplaza con tu endpoint de AWS IoT
                .build();
    }
}

package com.clientaws;

import software.amazon.awssdk.services.iotdataplane.IotDataPlaneClient;

public class Main {
    public static void main(String[] args) {
        // Configurar el cliente IoT
        IotDataPlaneClient client = IoTClientSetup.setupClient();

        // Tópico al que se suscribe
        String topic = "iticbcn/sub";
        MQTTSubscriber.subscribeToTopic(client, topic);

        // Simulación de descarga desde S3
        String bucketName = "mi-bucket"; // Reemplaza con el nombre de tu bucket
        String objectKey = "mi-archivo.txt"; // Reemplaza con la clave del archivo en S3
        String downloadPath = "/ruta/donde/guardar/archivo.txt"; // Ruta de destino

        S3Downloader.downloadFile(bucketName, objectKey, downloadPath);
    }
}

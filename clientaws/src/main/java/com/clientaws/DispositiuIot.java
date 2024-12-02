/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientaws;

/**
 *
 * @author pau
 */
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil.KeyStorePasswordPair;

public class DispositiuIot{

    private static final String FICH_CLAU_PRIVADA = "clientaws\\src\\main\\resources\\6149b8930f8fbd530d8afdb87b9cc49868b1aff6011b23862c47c535ff4a1368-private.pem.key";
    private static final String FICH_CERT_DISP_IOT = "clientaws\\src\\main\\resources\\6149b8930f8fbd530d8afdb87b9cc49868b1aff6011b23862c47c535ff4a1368-certificate.pem.crt";
    private static final String ENDPOINT = "a3jyc2122j8ooj-ats.iot.us-east-1.amazonaws.com";

    public static final String TOPIC = "iticbcn/sub";
    public static final String CLIENT_ID = "BBDD-Connect";
    public static final AWSIotQos TOPIC_QOS = AWSIotQos.QOS0;

    private static AWSIotMqttClient awsIotClient;

    public static void setClient(AWSIotMqttClient cli) {
        awsIotClient = cli;
    }

    public static void initClient() {
        String cliEP = ENDPOINT;
        String cliId = CLIENT_ID;

        String certFile = FICH_CERT_DISP_IOT;
        String pKFile = FICH_CLAU_PRIVADA;

        if (awsIotClient == null && certFile != null && pKFile != null) {
            String algorithm = null;
            
            KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certFile, pKFile, algorithm);

            awsIotClient = new AWSIotMqttClient(cliEP, cliId, pair.keyStore, pair.keyPassword);
        }

        if (awsIotClient == null) {
            throw new IllegalArgumentException("Error als construir client amb el certificat o les credencials.");
        }
    }

    public void conecta() throws AWSIotException{
        initClient();
        awsIotClient.connect();
    }

    public void subscriu() throws AWSIotException{
        TopicIoT topic= new TopicIoT(TOPIC, TOPIC_QOS);
        awsIotClient.subscribe(topic, true);
    }
}
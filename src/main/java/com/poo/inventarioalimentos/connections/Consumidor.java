package com.poo.inventarioalimentos.connections;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class Consumidor {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "tu_cola_de_mensajes";

    public void iniciarConsumidor() {
        
        try {
            // Configurar la conexión a ActiveMQ
            ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
            Connection connection = factory.createConnection();
            connection.start();

            // Crear una sesión de mensajería
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Obtener la cola de destino
            Queue destination = session.createQueue(QUEUE_NAME);

            // Crear un consumidor de mensajes para la cola
            MessageConsumer consumer = session.createConsumer(destination);

            // Implementa la lógica para recibir y procesar mensajes
            while (true) {
                Message message = consumer.receive(); // Bloquea hasta que llegue un mensaje

                // Aquí puedes procesar el mensaje según tus necesidades
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String contenido = textMessage.getText();
                    System.out.println("Mensaje recibido: " + contenido);
                    // Implementa la lógica de procesamiento aquí
                }
            }
        } catch (JMSException e) {
            e.getMessage();
        }
    }
}

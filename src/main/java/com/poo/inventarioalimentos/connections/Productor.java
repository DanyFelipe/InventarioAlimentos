package com.poo.inventarioalimentos.connections;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import static org.apache.activemq.console.command.DefaultPasswordFactory.factory;

public class Productor {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "TransaccionesInventario";

    public void enviarTransaccion(String transaccion) {
        ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

        try {
            // Crear una conexión y una sesión
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Crear una cola de mensajes
            Destination destination = session.createQueue(QUEUE_NAME);

            // Crear un productor de mensajes
            MessageProducer producer = session.createProducer(destination);

            // Crear un mensaje con la transacción y enviarlo a la cola
            TextMessage mensaje = session.createTextMessage(transaccion);
            producer.send(mensaje);

            // Cerrar conexiones
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

package com.poo.inventarioalimentos.connections;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poo.inventarioalimentos.Compra;
import com.poo.inventarioalimentos.Venta;
import java.io.IOException;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class Consumidor {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "TransaccionesInventario";
    private BaseDatosMySQL baseDatos;
    ObjectMapper objectMapper = new ObjectMapper();

    public Consumidor() {
        // Inicializa la instancia de la base de datos en el constructor
        baseDatos = new BaseDatosMySQL();

    }

    public void iniciarConsumidor() {

        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);

            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String contenido = textMessage.getText();

                    // Llama a la función para procesar el mensaje
                    procesarMensaje(contenido);
                } else {
                    System.out.println("Mensaje no reconocido.");
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void procesarMensaje(String contenido) {

        try {
            System.out.println("Contenido: " + contenido);
            // Determina el tipo de transacción (Compra o Venta) a partir del contenido
            if (contenido.contains("nombre")) {
                System.out.println("QUE SUCEDE!!");
                Compra compra = objectMapper.readValue(contenido, Compra.class);
                // Realiza acciones para procesar la compra en la base de datos
                System.out.println("NOS VAMOS!!");
                baseDatos.agregarProducto(compra);

            } else if (contenido.contains("id")) {

                Venta venta = objectMapper.readValue(contenido, Venta.class);
                // Realiza acciones para procesar la venta en la base de datos
                baseDatos.modificarCantidad(venta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

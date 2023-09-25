/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.poo.inventarioalimentos;
import com.poo.inventarioalimentos.connections.*;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;


public class InventarioAlimentos {
    
    private static final String BROKER_URL = "tcp://localhost:61616"; // URL del servidor ActiveMQ
    private static final String QUEUE_NAME = "TransaccionesInventario"; // Nombre de la cola para transacciones

    public static void main(String[] args) {
        try {
            System.out.println("INICIANDO");
            // Configurar la conexión a ActiveMQ
            ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
            System.out.println("FASE 1");
            Connection connection = factory.createConnection();
            connection.start();

            // Crear una sesión de mensajería
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Obtener la cola de destino para transacciones
            Queue destination = session.createQueue(QUEUE_NAME);

            // Crear un productor de mensajes para la cola
            MessageProducer producer = session.createProducer(destination);

            // Simular una transacción de compra en una sucursal
            Compra compra = new Compra(1, 2, 5, 3,10.0); // IDSucursal, IDProducto, Cantidad, PrecioUnitario
            TextMessage mensajeCompra = session.createTextMessage(compra.toString());

            // Simular una transacción de venta en otra sucursal
            Venta venta = new Venta(2, 4, 2, 1, 15.0); // IDSucursal, IDProducto, Cantidad, PrecioUnitario
            TextMessage mensajeVenta = session.createTextMessage(venta.toString());

            // Enviar mensajes de transacciones a la cola
            producer.send(mensajeCompra);
            producer.send(mensajeVenta);

            // Cerrar recursos
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            System.out.println("SE ROMPIO");
        }
    }
    private static void enviarTransaccion(MessageProducer producer, Session session, Transaccion transaccion) throws Exception {
        // Convertir la transacción a una cadena
        String mensaje = transaccion.toString();
        
        // Crear un mensaje de texto para la transacción
        TextMessage mensajeTransaccion = session.createTextMessage(mensaje);
        
        // Enviar el mensaje a la cola
        producer.send(mensajeTransaccion);
    }
}

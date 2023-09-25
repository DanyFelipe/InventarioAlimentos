/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.poo.inventarioalimentos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poo.inventarioalimentos.connections.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventarioAlimentos {
    
    public static void main(String[] args) {
        
        System.out.println("INICIANDO.");
        Productor productor = new Productor();
        Consumidor consumidor = new Consumidor();
        ObjectMapper objectMapper = new ObjectMapper();
        
        String transaccion = null;

        // Iniciar el consumidor en un hilo separado
        Thread consumidorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                consumidor.iniciarConsumidor();
            }
        });
        consumidorThread.start();

        // Crear instancias de Compra y Venta
        Compra info = new Compra("Nintendo Switch", "Consola de videojuegos japonesa", 300,8);
        //Venta info = new Venta(2, 4,5 ,1, 40);
        
        try {
            // Serializa el objeto JSON a STRING
            transaccion = objectMapper.writeValueAsString(info);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(InventarioAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Enviar la instancia de la transaccion como una COMPRA o VENTA para que la registre el consumidor en la base de datos
        productor.enviarTransaccion(transaccion);

        // Esperar a que el usuario termine
        try {
            consumidorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La aplicación ha terminado.");
    }
}

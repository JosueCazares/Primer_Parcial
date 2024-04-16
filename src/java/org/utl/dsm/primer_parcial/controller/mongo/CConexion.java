/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.controller.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 *
 * @author josue
 */
public class CConexion {
    private static final String url = "mongodb://localhost:27017"; 
            
    public static MongoClient getConnection(){
    return MongoClients.create(url);
    }
}

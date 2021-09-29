package com.APISocketsServidor;

import java.net.*;
import java.io.*;

public class mainServidor {
    public static void main (String args[]) {
        try{            
            int serverPort = 3306; // Porta Padrão
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Tentando estabelecer conexão...");
            while(true) {          
                new Conexao(listenSocket.accept()).start(); // Keep running           
            }
        }catch(IOException e) {
            System.out.println("Listen socket:"+e.getMessage());
        }
    }
}

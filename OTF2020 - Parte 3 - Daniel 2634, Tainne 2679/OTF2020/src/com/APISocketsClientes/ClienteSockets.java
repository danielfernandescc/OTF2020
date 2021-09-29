package com.APISocketsClientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSockets{

    private final int porta;

    public ClienteSockets(){
        this.porta = 3306;
    }

    public void run() {

        Socket socketObj = null;
        Scanner sc = new Scanner(System.in);

        try{
            socketObj = new Socket("localhost", this.porta);
            DataInputStream in = new DataInputStream(socketObj.getInputStream());
            DataOutputStream out = new DataOutputStream(socketObj.getOutputStream());
            while(true){
                String menu_mensagem = in.readUTF();
                System.out.println(menu_mensagem);
                System.out.println("Email");
                String email = sc.next();
                System.out.println("Senha");
                String senha = sc.next();   
                out.writeUTF(email);
                out.writeUTF(senha); 
                String resposta_servidor = in.readUTF();
                if(resposta_servidor.equals("Login Aprovado")){
                    out.writeUTF("continue");
                    in.readUTF(); 
                }                            
            }          
        }catch (UnknownHostException e){
            System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){
            System.out.println("readline2:"+e.getMessage());
        }finally {
            if(socketObj!=null)
            try{
            socketObj.close();
        }catch (IOException e){
        System.out.println("Close:"+e.getMessage());}
        }
    }
}   
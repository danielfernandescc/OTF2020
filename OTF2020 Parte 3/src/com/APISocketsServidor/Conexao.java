package com.APISocketsServidor;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException; 
 
public class Conexao extends Thread{
    
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private Integer flag;
    private String opcao_menu;

    public Conexao(Socket aclientSocket){

        try {
            this.clientSocket = aclientSocket;
            this.in = new DataInputStream(clientSocket.getInputStream());
            this.out =new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("Conectado ao servidor: "+this.clientSocket.getLocalAddress());         
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);}
        }

        @Override
        public void run(){ // THREAD
            if(!clientSocket.isConnected()){
                System.out.println("Não conectou");
            }else{
                System.out.println("Endereco:"+clientSocket.getInetAddress().getHostAddress());
            }
            try{
                String jdbcUrl = "jdbc:sqlite:C:\\Users\\danie\\Documents\\Dist2\\BD\\db_OTF2020.db";
                Connection conn = DriverManager.getConnection(jdbcUrl);
                System.out.println("[STATUS]   Conectado ao servidor");
                out.writeUTF("[SERVIDOR]   Entre com email e senha");
                String email = in.readUTF();
                String senha = in.readUTF();
                ServidorControle SC = new ServidorControle(email,senha);
                SC.verificaAcesso(email,senha,conn);
                out.writeUTF("Login Aprovado");
                flag = 1;   
                while(flag!=0){
                    String confirmacao_login = in.readUTF();
                    if(confirmacao_login.equals("continue")){
                        SC.printaMenu();
                        opcao_menu = in.readUTF();
                        System.out.print("MENSAGEM DE CONFIRMAÇÃO:"+opcao_menu);
                        flag = 1;
                    } 
                }
            }catch(SQLException e){
                System.out.println("Erro ao conectar ao banco de dados:"+e.getMessage());
                e.printStackTrace();
            }catch(IOException e){
                System.err.println("Erro IOException"+e.getMessage());
            }     
            try{
            clientSocket.close();
            System.out.println("Fechou uma conexão");
            } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}

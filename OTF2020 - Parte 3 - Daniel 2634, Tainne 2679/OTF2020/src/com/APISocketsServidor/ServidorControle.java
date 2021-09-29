package com.APISocketsServidor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ServidorControle{
    
    private String Email;
    private String Senha;
    
    public ServidorControle(String email, String senha){
        this.Email = email;
        this.Senha = senha;
    }

    public void printaMenu(){
        System.out.print("######-----Olá!! O que deseja fazer?-----######\n\n");
        System.out.print("|-----------------------------------------------|\n");
        System.out.print("| Opção 1 - Criar novo pacote de figurinhas     |\n");
        System.out.print("| Opção 2 - Criar novo álbum de figurinhas      |\n");
        System.out.print("| Opção 3 - Realizar uma tarefa                 |\n");
        System.out.print("| Opção 4 - Participar de um sorteio            |\n");
        System.out.print("| Opção 5 - Ver seus pontos                     |\n");
        System.out.print("| Opção 6 - Acompanhar um pedido                |\n");
        System.out.print("| Opção 7 - Participar de um sorteio            |\n");
        System.out.print("| Opção 8 - Realizar trocas                     |\n");
        System.out.print("| Opção 9 - Sair                                |\n");
        System.out.print("|-----------------------------------------------|\n");
    }

     // Metodos para gerencimento do banco de dados

    public void verificaAcesso(String email, String senha, Connection conn){
        try{
            String sql = "SELECT email, senha FROM Cadastro WHERE email = ? and senha = ?";
            PreparedStatement stmt  = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.execute(sql);
            //System.out.println(rs.getString("email") +  "\t" + rs.getString("senha"));
        }catch(SQLException e){
            System.out.println("Erro ao verificar acesso:"+e.getMessage());
        }
    }

    public String getEmail(){
        return this.Email;
    }

    public String getSenha(){
        return this.Senha;
    }

}


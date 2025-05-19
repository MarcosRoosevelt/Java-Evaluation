package br.com.empresa.dao;

import br.com.empresa.model.Folha;
import br.com.empresa.model.Funcionario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FolhaPagamentoDAO {
    
    String URL = "jdbc:postgresql://localhost:5432/empresa";
    String USER = "postgres";
    String PASSWORD = "comiumagoiaba";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public void salvar(Folha folha) {

        String sql = "INSERT INTO folhas_pagamento (funcionario_id, proventos, descontos) VALUES (?, ?, ?)";

        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) { 

            stmt.setInt(1, folha.getFuncionarioId());
            stmt.setDouble(2, folha.getProventos());
            stmt.setDouble(3, folha.getDescontos());

            stmt.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar folha: " + e.getMessage());
        }
        

    }
    
    public BigDecimal calcularIRRF(double salarioBase, double proventos) {
        String sql = "SELECT calcula_irrf(?, ?)";
        
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) { 

            stmt.setDouble(2, salarioBase);
            stmt.setDouble(3, proventos);

            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return rs.getBigDecimal(1);
                }
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao calcular IRRF: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }

}

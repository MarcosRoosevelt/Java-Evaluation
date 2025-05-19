package br.com.empresa.dao;

import br.com.empresa.Utils.HashUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.empresa.model.Funcionario;
import java.sql.ResultSet;

public class FuncionarioDAO {

    String URL = "jdbc:postgresql://localhost:5432/empresa";
    String USER = "postgres";
    String PASSWORD = "comiumagoiaba";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean salvar(Funcionario funcionario) {

        String sql = "INSERT INTO funcionarios(nome, cpf, salario_base, cargo, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) { 

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setDouble(3, funcionario.getSalario_base());
            stmt.setString(4, funcionario.getCargo());
            
            String cypherText = HashUtil.gerarHash(funcionario.getPassword());
            stmt.setString(5, cypherText);

            stmt.executeUpdate();

            return true;
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        

    }
    
    public Funcionario buscarPorId(int id) {
        String sql = "SELECT id, nome, cpf, cargo, salario_base, senha FROM funcionarios WHERE id = ?";
        
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) { 
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setCargo(rs.getString("cargo"));
                    funcionario.setSalario_base(rs.getDouble("salario_base"));
                    funcionario.setPassword(rs.getString("senha"));
                    return funcionario;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar o funcionário!: " + e.getMessage());
        }
        return null;
    }
    
    public Funcionario autenticar(String cpf, String senha) {
    String sql = "SELECT * FROM funcionarios WHERE cpf = ? AND senha = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            
            String hashStorage = rs.getString("senha");
            
            if(HashUtil.verificarSenha(senha, hashStorage));
            Funcionario f = new Funcionario();
            f.setId(rs.getInt("id"));
            f.setNome(rs.getString("nome"));
            f.setCpf(rs.getString("cpf"));
            f.setCargo(rs.getString("cargo"));
            f.setSalario_base(rs.getDouble("salario_base"));
            return f;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}


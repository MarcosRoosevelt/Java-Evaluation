package br.com.empresa.model;

import java.math.BigDecimal;

public class Folha {
    private int id;
    private int funcionarioId;
    private double proventos;
    private double descontos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Double getProventos() {
        return proventos;
    }

    public void setProventos(double proventos) {
        this.proventos = proventos;
    }

    public Double getDescontos() {
        return descontos;
    }

    public void setDescontos(double descontos) {
        this.descontos = descontos;
    }
    
    
}

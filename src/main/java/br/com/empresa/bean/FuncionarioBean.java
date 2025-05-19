package br.com.empresa.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.empresa.dao.FuncionarioDAO;
import br.com.empresa.model.Funcionario;

@Named("funcionarioBean")
@RequestScoped
public class FuncionarioBean implements Serializable{
    private Funcionario funcionario = new Funcionario();

    public Funcionario getFuncionario() {
        return funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void salvar() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        if (funcionarioDAO.salvar(funcionario)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Funcionário cadastrado com sucesso!"));
            funcionario = new Funcionario();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar!", null));
        }
    }
}

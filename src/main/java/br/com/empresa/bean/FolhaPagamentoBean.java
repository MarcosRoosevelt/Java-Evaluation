package br.com.empresa.bean;

import br.com.empresa.dao.FolhaPagamentoDAO;
import br.com.empresa.dao.FuncionarioDAO;
import br.com.empresa.model.Folha;
import br.com.empresa.model.Funcionario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class FolhaPagamentoBean implements Serializable{
    private Folha folha = new Folha();
    private List<Funcionario> funcionarios;
    
    private BigDecimal irrfCalculado;
    
    public void calcularIRRF() {
        try {
            Funcionario funcionario = new FuncionarioDAO().buscarPorId(folha.getFuncionarioId());
            if (funcionario != null) {
                irrfCalculado = new FolhaPagamentoDAO().calcularIRRF(funcionario.getSalario_base(), folha.getProventos());
            }
        } catch (Exception e) {
        }
    }

    
    public void salvar() {
        new FolhaPagamentoDAO().salvar(folha);
        folha = new Folha();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Folha cadastrada com sucesso!"));
    }

    public Folha getFolha() {
        return folha;
    }

    public void setFolha(Folha folha) {
        this.folha = folha;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    
}

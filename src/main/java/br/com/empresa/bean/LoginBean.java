package br.com.empresa.bean;

import br.com.empresa.dao.FuncionarioDAO;
import br.com.empresa.model.Funcionario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String cpf;
    private String senha;
    private Funcionario usuarioLogado;

    public String login() {
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario funcionario = dao.autenticar(cpf, senha);

        if (funcionario != null) {
            this.usuarioLogado = funcionario;

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(true);
            session.setAttribute("usuarioLogado", funcionario);

            return "/login.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Login falhou", "CPF ou senha inválidos."));
            return null;
        }
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "/login.xhtml?faces-redirect=true";
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Funcionario getUsuarioLogado() { return usuarioLogado; }
}

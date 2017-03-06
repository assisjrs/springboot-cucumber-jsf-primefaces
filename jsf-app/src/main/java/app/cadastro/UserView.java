package app.cadastro;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by assis on 01/03/17.
 */
@Component
@Scope("request")
public class UserView {
    @Autowired
    private UsuarioRepository repository;

    private Usuario usuario = new Usuario();

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Usuario Selected", ((Usuario) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void novo() throws Exception {
        usuario = new Usuario();
    }

    public void salvar(){
        if(usuario.getNome().equals("") || usuario.getNome() == null){
            FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage("Nome obrigatorio!"));
        }else
        {
            repository.save(usuario);
        }
        usuario = null;
    }

    public List<Usuario> getUsuarios() {
     return repository.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

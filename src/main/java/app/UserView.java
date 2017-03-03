package app;

import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by assis on 01/03/17.
 */
@ViewScoped
@ManagedBean(name = "userView")
public class UserView {
    @ManagedProperty("#{usuarioRepository}")
    private UsuarioRepository repository;

    private Usuario usuario;

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Usuario Selected", ((Usuario) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void novo(){
        usuario = new Usuario();
    }

    public void salvar(){
        repository.create(usuario);
    }

    public List<Usuario> getUsuarios() {
        System.out.println("getUsuarios");
        return repository.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRepository(UsuarioRepository repository) {
        this.repository = repository;
    }
}

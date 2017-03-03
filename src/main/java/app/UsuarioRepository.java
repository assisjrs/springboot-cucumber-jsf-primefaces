package app;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.List;

@ManagedBean(name = "usuarioRepository")
@ApplicationScoped
public class UsuarioRepository {
    private EntityManager entityManager;

    @PostConstruct
    public void init(){
        entityManager =  Persistence.createEntityManagerFactory("default").createEntityManager();
    }

    public void create(Usuario entity) {
        entityManager.persist(entity);
    }

    public Usuario update(Usuario entity) {
        return entityManager.merge(entity);
    }

    public void remove(Usuario entity) {
        entityManager.remove(entity);
    }

    public Usuario find(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    public List<Usuario> findAll() {
        Query query = entityManager.createQuery("from " + Usuario.class.getName());

        @SuppressWarnings("unchecked")
        List<Usuario> resultList = query.getResultList();

        return resultList;
    }
}

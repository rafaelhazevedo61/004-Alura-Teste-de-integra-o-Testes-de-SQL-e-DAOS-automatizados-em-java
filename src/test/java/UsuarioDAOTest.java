import br.com.alura.leilao.dao.UsuarioDao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsuarioDAOTest {

    private UsuarioDao dao;

    @Test
    public void deveriaEncontrarUsuarioCadastrado() {
        EntityManager em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);

        Usuario usuario = new Usuario("fulano", "fulano@email.com","123456789");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
        Assert.assertNotNull(usuario);
    }

    @Test
    public void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        EntityManager em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);

        Usuario usuario = new Usuario("fulano", "fulano@email.com","123456789");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        //Usuario encontrado = this.dao.buscarPorUsername("beltrano");
        Assert.assertThrows(NoResultException.class,
               () -> this.dao.buscarPorUsername("beltrano"));
    }

}

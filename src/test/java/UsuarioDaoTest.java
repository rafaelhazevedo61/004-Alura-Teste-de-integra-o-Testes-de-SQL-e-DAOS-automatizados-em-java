import br.com.alura.leilao.dao.UsuarioDao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.UsuarioBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    public void inicializar(){
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach(){
        em.getTransaction().rollback();
    }

    @Test
    public void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("12345678")
                .criar();

        em.persist(usuario);

        Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
        Assert.assertNotNull(usuario);
    }

    @Test
    public void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("12345678")
                .criar();

        em.persist(usuario);

        Assert.assertThrows(NoResultException.class,
               () -> this.dao.buscarPorUsername("beltrano"));
    }

    @Test
    public void deveriaRemoverUmUsuario(){
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("12345678")
                .criar();

        em.persist(usuario);

        dao.deletar(usuario);

        Assert.assertThrows(NoResultException.class,
                () -> this.dao.buscarPorUsername(usuario.getNome()));


    }

}

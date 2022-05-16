import br.com.alura.leilao.dao.UsuarioDao;
import br.com.alura.leilao.model.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class UsuarioDAOTest {

    private UsuarioDao dao;

    private EntityManager em;

    @Test
    public void testeBuscaDeUsuarioPeloUsername() {
        this.dao = new UsuarioDao(em);
        Usuario usuario = this.dao.buscarPorUsername("fulano");

        Assert.assertNotNull(usuario);

    }

}

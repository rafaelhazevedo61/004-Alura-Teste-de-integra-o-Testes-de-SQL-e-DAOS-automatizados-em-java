import br.com.alura.leilao.dao.UsuarioDao;
import br.com.alura.leilao.model.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UsuarioDAOTest {

    private UsuarioDao dao;

    @Test
    public void testeBuscaDeUsuarioPeloUsername() {
        this.dao = new UsuarioDao();
        Usuario usuario = this.dao.buscarPorUsername("fulano");

        Assert.assertNotNull(usuario);

    }

}

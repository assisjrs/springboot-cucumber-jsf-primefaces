package stepDefinition;

import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by gustavooliveira on 16/03/17.
 */
public class CadastroPageAssert extends AbstractAssert<CadastroPageAssert, CadastroPage> {

    public CadastroPageAssert(CadastroPage page) {
        super(page, CadastroPageAssert.class);
    }

    public void quantidaDeUsuariosCadastradosNaLista(int quantidadeUsuariosCadastrados) {
        assertThat(actual.getUsuarios().size()).isEqualTo(quantidadeUsuariosCadastrados);
    }

    public void verificaSeAMensagemSeEncontraNaPagina(String ... mensagemDeErro) {
        assertThat(mensagemDeErro).contains(mensagemDeErro);
    }
}

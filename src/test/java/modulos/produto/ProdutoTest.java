package modulos.produto;

import modulos.usuario.tela.LoginTela;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@DisplayName("Teste Mobile do Módulo de Produto")
public class ProdutoTest {

    private WebDriver app;

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        //Abrir o App
        DesiredCapabilities capacidades = new DesiredCapabilities();
        capacidades.setCapability("deviceName", "ASUS_X00QD");
        capacidades.setCapability("platform", "Android");
        capacidades.setCapability("udid", "JBAXB603Z10693L");
        capacidades.setCapability("appPackage", "com.lojinha");
        capacidades.setCapability("appActivity", "com.lojinha.ui.MainActivity");
        capacidades.setCapability("app", "C:\\Lojinha App\\Lojinha+Android+Nativa\\lojinha-nativa.apk");

        this.app = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidades);
        this.app.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @DisplayName("Validacao do Valor do Produto Nao Permitido")
    @Test
    public void testValidacaoDoValorDeProdutoNaoPermitido() {
        //Fazer Login
        String mensagemApresentada = new LoginTela(app)
                .preencherUsuario("admin")
                .preencherSenha("admin")
                .submeterLogin()
                .abrirTelaAdicaoProduto()
                .preencherNomeProduto("Iphone")
                .preencherValorProduto("700001")
                .preencherCoresProduto("preto,prata")
                .submissaoComErro()
                .obterMensagemDeErro();

        //Validar que a mensagem de valor inválido foi apresentada
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }
        @AfterEach
        public void afterEach() { app.quit();

    }
}



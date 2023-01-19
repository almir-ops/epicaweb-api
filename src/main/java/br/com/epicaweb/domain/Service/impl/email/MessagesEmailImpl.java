package br.com.epicaweb.domain.Service.impl.email;

import br.com.epicaweb.domain.model.EnvioEmailModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class MessagesEmailImpl {

    public String msgCobranca(EnvioEmailModel infoEmail){

        int qtdeAtraso = Integer.parseInt(infoEmail.getQtdDebAnt());
        String existeAtraso = "";

        if ( qtdeAtraso > 0) {
             existeAtraso = "<br><br><br>* Constam débitos anteriores neste contrato. <br><br><br>";
        }

        String bodyMSG = "Prezado(a),<br><br>"
                + "Notamos que não consta em nosso sistema o pagamento da <b>TAXA DE MANUTENÇÃO</b><br>"
                + "do contrato <b>nº. " + infoEmail.getProposta() + " </b>em nome de <b> " + infoEmail.getCliente()
                + " </b> descrita abaixo: <br><br>" +

                "<b>*&nbsp;&nbsp;&nbsp;&nbsp;" + infoEmail.getProduto() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + infoEmail.getVencimento()
                + "&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;" + infoEmail.getValor() + "</b><br><br>" +

                "Por favor entrar em contato para regularização dos valores, ou acesse https://cliente.jardimdacolina.com.br<br>"
                + "Caso o valor já tenha sido quitado, por favor desconsidere a mensagem."

                + existeAtraso +

                "Atenciosamente<br>" + "<br><br>" + "Deptoº Cobrança<br>" + "11 4122-0000 / 11 4125-9000<br>";

        return  bodyMSG;

    }

}

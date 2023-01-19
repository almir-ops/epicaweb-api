package br.com.epicaweb.domain.Service.impl.email;

import br.com.epicaweb.domain.Service.EnvioEmailService;
import br.com.epicaweb.domain.model.EnvioEmailModel;
import br.com.epicaweb.ws.request.EnvioEmailRequest;
import ch.qos.logback.core.joran.conditional.ElseAction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class EnvioEmailImpl implements EnvioEmailService {

    @PersistenceContext
    private EntityManager manager;

    MessagesEmailImpl messagesEmail;
    SendMailImpl sendMailImpl;

    private boolean geraLogEnvio(String proposta, String vencimento) {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ldVencto = LocalDate.parse(vencimento, formatter);
            LocalDate dataHoraAtual = LocalDate.now();
//          String dtVencto = formatter.format(LocalDate.parse(vencimento));
//          LocalDate dtVencto = LocalDate.parse(formatter.format(vencimento));
            Period period = Period.between(ldVencto, dataHoraAtual);
            int dias = period.getDays();

            String historico = "ENVIO DE EMAIL DE ALERTA DE VENCIMENTO DE " + dias + " DIAS";

            StoredProcedureQuery gravaLog = manager.createStoredProcedureQuery("SP_GRAVA_LOG_FOLLOWUP");
            gravaLog.registerStoredProcedureParameter("proposta", String.class, ParameterMode.IN);
            gravaLog.registerStoredProcedureParameter("operador", Integer.class, ParameterMode.IN);
            gravaLog.registerStoredProcedureParameter("motivo", Integer.class, ParameterMode.IN);
            gravaLog.registerStoredProcedureParameter("historico", String.class, ParameterMode.IN);
            gravaLog.registerStoredProcedureParameter("cod_produto", String.class, ParameterMode.IN);

            gravaLog.setParameter("proposta", proposta);
            gravaLog.setParameter("operador", 17);
            gravaLog.setParameter("motivo", 12);
            gravaLog.setParameter("historico", historico);
            gravaLog.setParameter("cod_produto", "T");
            gravaLog.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Transactional
    public List<EnvioEmailModel> getEnvioEmail(EnvioEmailRequest filtro) {

        String valProposta = validaProposta(filtro.getProposta());
        String validaTotal = validaTotal(filtro.getTotal());
        Number validaTop = validaTop(filtro.getTop());

        StoredProcedureQuery findAll = manager.createStoredProcedureQuery("dbo.SP_ENVIO_EMAIL_COBRANCA_NEW", EnvioEmailModel.class);
        findAll.registerStoredProcedureParameter("data", String.class, ParameterMode.IN);
        findAll.registerStoredProcedureParameter("proposta", String.class, ParameterMode.IN);
        findAll.registerStoredProcedureParameter("top", Number.class, ParameterMode.IN);
        findAll.registerStoredProcedureParameter("total", String.class, ParameterMode.IN);

        findAll.setParameter("data", filtro.getVencimento());
        findAll.setParameter("proposta", valProposta );
        findAll.setParameter("total", validaTotal);
        findAll.setParameter("top", validaTop);

        return (List<EnvioEmailModel>) findAll.getResultList();
    }

    @Transactional
    public EnvioEmailModel getOneEnvioEmail(EnvioEmailRequest filtro) {

        String valProposta = validaProposta(filtro.getProposta());
        String validaTotal = validaTotal(filtro.getTotal());
        Number validaTop = validaTop(filtro.getTop());

        StoredProcedureQuery findAll = manager.createStoredProcedureQuery("dbo.SP_ENVIO_EMAIL_COBRANCA_NEW", EnvioEmailModel.class);
        findAll.registerStoredProcedureParameter("data", String.class, ParameterMode.IN);
        findAll.registerStoredProcedureParameter("proposta", String.class, ParameterMode.IN);
        findAll.registerStoredProcedureParameter("top", Number.class, ParameterMode.IN);
        findAll.registerStoredProcedureParameter("total", String.class, ParameterMode.IN);

        findAll.setParameter("data", filtro.getVencimento());
        findAll.setParameter("proposta", valProposta );
        findAll.setParameter("total", validaTotal);
        findAll.setParameter("top", validaTop);

        return (EnvioEmailModel) findAll.getSingleResult();

    }

    private String validaProposta(String proposta){
        String valProposta = proposta;
        if((proposta == "") || (proposta == null)) {
            valProposta = "NA";
        }
        return valProposta;
    }

    private String validaTotal(String total) {
        String valTotal;
        if ((total == null) ||(total =="")) {
            valTotal = "0";
        } else {
            valTotal = "1";
        }
        return valTotal;
    }

    private Number validaTop(Number top) {
        Number valTop;
        if (top == null)  {
             valTop = 1;
        } else {
            valTop = top;
        }
        return valTop;
    }

    public void sendEmail(EnvioEmailRequest envioEmailRequest){
        String ambiente =  envioEmailRequest.getAmbiente();
        EnvioEmailModel retorno =  getOneEnvioEmail(envioEmailRequest);

        if ( ! sendMailCobranca(retorno, ambiente)){
            throw new RuntimeException();
        } else {
            if (ambiente.equals("prd")) {
                geraLogEnvio(envioEmailRequest.getProposta(), envioEmailRequest.getVencimento());
            }
        }
    }

    private Boolean sendMailCobranca(EnvioEmailModel sendEmailModel, String ambiente){

        String dest = "";
        if (ambiente.equals("prd")){
            dest = sendEmailModel.getEmail();
            //dest = "marcos@jardimdacolina.com.br,almir.junior@jardimdacolina.com.br";
        } else {
            dest = "marcos@jardimdacolina.com.br";
        }

        String subject = "Relacionamento com cliente - Proposta : " + sendEmailModel.getProposta();
        String message = messagesEmail.msgCobranca(sendEmailModel);
        String ocultos = "marcos@jardimdacolina.com.br,almir.junior@jardimdacolina.com.br,denilson@jardimdacolina.com.br";

        try {
            sendMailImpl.sendMail(dest,ocultos, subject, message);
        } catch (Exception e){
            return  false;
        }

        return true;
    }

    public void sendEmailError(String proposta, String erro) throws Exception {
        String dest = "marcos@jardimdacolina.com.br , almir.junior@jardimdacolina.com.br, denilson@jardimdacolina.com.br";
        String subject =  "Erro no envio do email ao cliente - Proposta : " + proposta;
        String message = "Envio de email de cobrança para a proposta :  " + proposta + "<br>" + "Erro :  " + erro + "<br><br><br><br>";
        String ocultos = "";
        sendMailImpl.sendMail(dest, ocultos,subject, message );
    }

}

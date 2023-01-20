package br.com.epicaweb.domain.Service.impl.sms;

import br.com.epicaweb.domain.Service.EnvioSMSService;
import br.com.epicaweb.domain.model.EnvioSMSModel;
import br.com.epicaweb.ws.request.EnvioSMSRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Service
public class EnvioSmsImpl implements EnvioSMSService {

    @PersistenceContext
    private EntityManager manager;


    @Transactional
    public List<EnvioSMSModel> getAllEnvioSms(EnvioSMSRequest filtro) {

        String valProposta = validaProposta(filtro.getProposta());
        StoredProcedureQuery findAll = manager.createStoredProcedureQuery("dbo.SP_ENVIO_SMS_NEW",EnvioSMSModel.class);
        findAll.registerStoredProcedureParameter("data", String.class, ParameterMode.IN);
        findAll.registerStoredProcedureParameter("proposta", String.class, ParameterMode.IN);

        findAll.setParameter("data", filtro.getVencimento());
        findAll.setParameter("proposta", valProposta );
        return (List<EnvioSMSModel>) findAll.getResultList();
    }

    @Transactional
    public EnvioSMSModel getOneEnviosms(EnvioSMSRequest filtro) {

        String valProposta = validaProposta(filtro.getProposta());

        StoredProcedureQuery findAll = manager.createStoredProcedureQuery("dbo.SP_ENVIO_SMS_NEW",EnvioSMSModel.class);
        findAll.registerStoredProcedureParameter("data", String.class, ParameterMode.IN);
        findAll.registerStoredProcedureParameter("proposta", String.class, ParameterMode.IN);

        findAll.setParameter("data", filtro.getVencimento());
        findAll.setParameter("proposta", valProposta );

        return (EnvioSMSModel) findAll.getSingleResult();
    }


    private String validaProposta(String proposta){
        String valProposta = proposta;
        if((proposta == "") || (proposta == null)) {
            valProposta = "NA";
        }
        return valProposta;
    }

    public boolean geraLogEnvio(String proposta, String vencimento) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ldVencto = LocalDate.parse(vencimento, formatter);
            LocalDate dataHoraAtual = LocalDate.now();
            Period period = Period.between(dataHoraAtual, ldVencto);

            int dias = period.getDays();

            String historico = "ENVIO DE SMS PARA ALERTA DE " + dias + " DIA(S) PARA VENCIMENTO";

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


    public boolean sendSMS(EnvioSMSRequest envioSMSRequest){

        String msg = msgSMSCobranca( envioSMSRequest.getProposta(),envioSMSRequest.getVencimento());
        String ambiente = envioSMSRequest.getAmbiente();

            if (!envioSMS(envioSMSRequest.getTelefone() , msg ,ambiente)) {
                throw new RuntimeException();
            } else {
                if (ambiente.equals("prd")){
                    geraLogEnvio(envioSMSRequest.getProposta(),envioSMSRequest.getVencimento());
                }
        }

        return true;
    }


     public boolean envioSMS(String destinatario , String msg, String ambiente){
        try {

            String dest  = ambiente.equals("prd") ? destinatario: "11978314035";

            StoredProcedureQuery enviaSMS = manager.createStoredProcedureQuery("SP_ENVIA_SMS_AUTOMATICO");
            enviaSMS.registerStoredProcedureParameter("destinatario",String.class,ParameterMode.IN);
            enviaSMS.registerStoredProcedureParameter("msg",String.class,ParameterMode.IN);
            enviaSMS.setParameter("destinatario",dest);
            enviaSMS.setParameter("msg",msg);
            enviaSMS.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public String msgSMSCobranca(String proposta, String vencimento){
        return "JD DA COLINA - Contr. "+ proposta + " - Prezado Cliente, lembramos que sua taxa de manutencao vence em " + vencimento + ".Para gerar o boleto acesse https://abrir.link/XTr2m";
    }


}

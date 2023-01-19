package br.com.epicaweb.domain.Service.impl.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Slf4j
@AllArgsConstructor
@Service
public class SendMailImpl {

    private ResourceLoader resourceLoader;


    public  void sendMail(String emailDestinatario, String destOcultos, String assunto, String conteudo) throws Exception{

        String servidor = "smtplw.com.br"; // do painel de controle do SMTP
        String username = "epicaadministracao"; // do painel de controle do SMTP
        String senha = "K269sMtp!CjC$!"; // do painel de controle do SMTP
        String porta = "465"; // do painel de controle do SMTP
        String emailRemetente = "cliente@jardimdacolina.com.br";


        Properties props = new Properties();
    /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", servidor);
        props.put("mail.smtp.socketFactory.port", porta);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", porta);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, senha);
                    }
                });
        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

            MimeMultipart multipart = new MimeMultipart("related");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = conteudo + "<img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText,"text/html; charset=iso-8859-1");

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setDataHandler(new DataHandler(getImage()));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // Adiciona
            multipart.addBodyPart(messageBodyPart);
            /* ================================================================= */

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailRemetente)); //Remetente

            Address[] toDest = InternetAddress.parse(emailDestinatario);  // Destinatário(s)
            Address[] toOcultos = InternetAddress.parse(destOcultos); // Destinatarios ocultos

            message.setRecipients(Message.RecipientType.TO, toDest);
            message.setRecipients(Message.RecipientType.BCC, toOcultos);
            message.setSubject(assunto);//Assunto
            message.setContent(multipart);
//          message.setText(conteudo);


         /**Método para enviar a mensagem criada*/
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static DataSource getImage() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = MailSender.class.getClassLoader();
        }
        DataSource ds = new URLDataSource(classLoader.getResource("images/logo_assinatura.jpg"));
        return ds;
    }


}
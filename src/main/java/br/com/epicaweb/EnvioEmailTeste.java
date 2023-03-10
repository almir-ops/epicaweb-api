package br.com.epicaweb;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailSender;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;


import javax.activation.URLDataSource;
import javax.mail.*;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import static org.springframework.boot.autoconfigure.web.WebProperties.*;
import static org.springframework.boot.autoconfigure.web.WebProperties.Resources.*;

@Slf4j
@AllArgsConstructor
public class EnvioEmailTeste {

    //@Autowired private static JavaMailSender mailSender;

    public static void main(String[] args) {

        String vencimento = "25/01/2023";
/*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ld = LocalDate.parse(vencimento, formatter);
        LocalDate dataHoraAtual = LocalDate.now();
        Period period = Period.between(ld, dataHoraAtual);
        int dias = period.getDays();
*/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ldVencto = LocalDate.parse(vencimento, formatter);
        LocalDate dataHoraAtual = LocalDate.now();
        Period period = Period.between(dataHoraAtual, ldVencto);

        int dias = period.getDays();

        System.out.println("quantidade de dias  :   " + dias);



/*
        String dest = "marcos@jardimdacolina.com.br";
//      String destOcultos = "marcos@jardimdacolina.com.br,denilson@jardimdacolina.com.br,almir.junior@jardimdacolina.com.br";
        String destOcultos = "marcos@jardimdacolina.com.br";
        String assunto = "Tete de envio de email";
        String conteudo = "Este texto se trata do corpo do email AGORA TEM QUE APARECER A IMAGEM<br>";

        sendMail(dest, destOcultos, assunto, conteudo);
*/
    }

    public static void sendMail(String emailDestinatario, String destOcultos, String assunto, String conteudo) {

        String servidor = "smtplw.com.br"; // do painel de controle do SMTP
        String username = "epicaadministracao"; // do painel de controle do SMTP
        String senha = "K269sMtp!CjC$!"; // do painel de controle do SMTP
        String porta = "465"; // do painel de controle do SMTP
        String emailRemetente = "cliente@jardimdacolina.com.br";

        Properties props = new Properties();
        /** Par??metros de conex??o com servidor Gmail */
        props.put("mail.smtp.host", servidor);
        props.put("mail.smtp.socketFactory.port", porta);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", porta);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,senha);
                    }
                });
        /** Ativa Debug para sess??o */
        session.setDebug(true);
        try {

            MimeMultipart multipart = new MimeMultipart("related");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = conteudo + "<img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText, "text/html");

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();


        //    Resource resource = new ClassPathResource("images/logo_assinatura.jpg");


            //DataSource fds = new FileDataSource("//servidor-epica/sisepica/FOLLOWUP/logo_assinatura.jpg");
      //      File file = ResourceUtils.getFile("classpath:images/logo_assinatura.jpg");
        //    DataSource fds = new FileDataSource(file);

            messageBodyPart.setDataHandler(new DataHandler(getImage()));
            messageBodyPart.setHeader("Content-ID", "<image>");




            // Adiciona
            multipart.addBodyPart(messageBodyPart);
            /* ================================================================= */

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailRemetente)); //Remetente

            Address[] toDest = InternetAddress.parse(emailDestinatario);  // Destinat??rio(s)
            Address[] toOcultos = InternetAddress.parse(destOcultos); // Destinatarios ocultos

            message.setRecipients(Message.RecipientType.TO,toDest);
            message.setRecipients(Message.RecipientType.BCC,toOcultos);
            message.setSubject(assunto);//Assunto
            message.setContent(multipart);
//            message.setText(conteudo);


            /**M??todo para enviar a mensagem criada*/
            Transport.send(message);
            System.out.println("Feito!!!");

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







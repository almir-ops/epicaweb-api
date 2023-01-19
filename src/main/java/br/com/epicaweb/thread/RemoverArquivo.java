package br.com.epicaweb.thread;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RemoverArquivo extends Thread{

//  String UPLOAD_ORIGEM = "//192.168.0.5/Documentos Digitalizados/Documentos Digitalizados Novos/";
    String UPLOAD_ORIGEM = "//192.168.0.5/scanners/";

    public RemoverArquivo(String scanner) {
        System.out.println(scanner);
        UPLOAD_ORIGEM = UPLOAD_ORIGEM + scanner + "/";
        start();
    }

    @Override
    public void run(){
        try {
            Thread.sleep(1000);

            Files.walk(Paths.get(UPLOAD_ORIGEM))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

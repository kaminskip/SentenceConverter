package pl.kaminski.sentencesconverter;

import pl.kaminski.sentencesconverter.impl.SentencesConverterImpl;

import java.io.*;

/**
 * Created by Paweł Kamiński.
 */
public class SentencesConverterApp {


    public static void main(String... params){
        SentencesConverter sentencesConverter = new SentencesConverterImpl();
        try(InputStream inputStream = new BufferedInputStream(new FileInputStream(params[0]));
        OutputStream xmlOutputStream = new BufferedOutputStream(new FileOutputStream(params[1]));
        OutputStream csvOutputStream = new BufferedOutputStream(new FileOutputStream(params[2]))){
            sentencesConverter.setInputStream(inputStream);
            sentencesConverter.addOutputStream(xmlOutputStream, OutputType.CSV);
            sentencesConverter.addOutputStream(csvOutputStream, OutputType.XML);
            sentencesConverter.process();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.example;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class BibliotecaManager {
    private final String nomeArquivo;
    private final XmlMapper xmlMapper;

    public BibliotecaManager(String nomeArquivo){
        this.nomeArquivo = nomeArquivo;
        //this.xmlMapper = new XmlMapper(); //versão 2.21
        this.xmlMapper = XmlMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build(); //versão 3

        //ativa a formatação correta (indentação, quebra de linha) no xml
        //this.xmlMapper.enable(SerializationFeature.INDENT_OUTPUT); //versão 2.21
    }
    public Biblioteca carregar(){
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()){
            System.out.println("Arquivo não encontrado. Criando nova Biblioteca...");
            return new Biblioteca();
        }
        try {
            //Lê o arquivo XML e converte para o objeto Biblioteca
            return xmlMapper.readValue(arquivo, Biblioteca.class);
        // } catch (IOException e){ //versão 2
        } catch (JacksonException e){
            System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
            //printStackTrace: exibe erros no console
            e.printStackTrace();
            return new Biblioteca();
        }
    }
    public void salvar(Biblioteca biblioteca){
        try {
            xmlMapper.writeValue(new File(nomeArquivo), biblioteca);
        // } catch (IOException e){
        // } catch (JacksonException e){ //versão 3 completa
        } catch (Exception e) {
            System.out.println("Erro ao salvar o arquivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

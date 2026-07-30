package org.example;


import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;
//@notação da biblioteca jackson
@JacksonXmlRootElement(localName = "biblioteca") //cria a tag principal no xml
public class Biblioteca {
    @JacksonXmlElementWrapper(localName = "livros") //cria as tags livros do xml
    @JacksonXmlProperty(localName = "livro") //cria as tags livro no xml
    private List<Livro> livros = new ArrayList<>();

    public Biblioteca(){}

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}

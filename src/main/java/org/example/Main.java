package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final BibliotecaManager manager = new BibliotecaManager("biblioteca.xml");
    private static final Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca;

    public static void main(String[] args) {
        biblioteca = manager.carregar();
        System.out.println("Bem-vindo a biblioteca dos estudos!!!");
        System.out.println(biblioteca.getLivros().size() + " livro(s) carregado(s).");

        int opcao = 0;
        while (opcao != 3){
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao){
                    case 1: adicionarLivro(); break;
                    case 2: listarLivros(); break;
                    case 3: System.out.println("Salvando e saindo..."); break;
                    default: System.out.println("Opção inválida!!!"); break;
                }
            } catch(InputMismatchException e){
                System.out.println("Erro: Por favor, digite um número.");
                scanner.nextLine();
            }
        }
        manager.salvar(biblioteca);
        System.out.println("Salvo com sucesso em 'biblioteca.xml'.");
        scanner.close();
    }
    private static void exibirMenu(){
        System.out.println("---Menu---");
        System.out.println("1. Adicionar novo livro");
        System.out.println("2. Listar todos os livros");
        System.out.println("3. Sair e salvar");
        System.out.print("Escolha uma opção: ");
    }
    private static void adicionarLivro(){
        System.out.println("---Adicionar livro---");
        try {
            System.out.println("Título: ");
            String titulo = scanner.nextLine();

            System.out.println("Autor: ");
            String autor = scanner.nextLine();

            System.out.println("Ano de publicação: ");
            int ano = scanner.nextInt();
            scanner.nextLine();

            biblioteca.getLivros().add(new Livro(titulo, autor, ano));
            System.out.println("Livro adicionado com sucesso!!!");
        } catch (InputMismatchException e){
            System.out.println("Erro: o ano deve ser um número!!!");
            scanner.nextLine();
        }
    }
    private static void listarLivros(){
        System.out.println("---Livros na biblioteca---");
        if (biblioteca.getLivros().isEmpty()){
            System.out.println("Nenhum livro cadastrado.");
        } else {
            biblioteca.getLivros().forEach(System.out::println);
        }
    }
}
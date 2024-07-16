package com.alura.literatura.principal;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.DadosAutor;
import com.alura.literatura.model.DadosLivro;
import com.alura.literatura.model.Livro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LivroRepository;
import com.alura.literatura.service.GutendexApi;
import com.alura.literatura.service.Conversor;

import java.util.*;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private GutendexApi requisicao = new GutendexApi();
    private AutorRepository repositorioAutor;
    private LivroRepository repositorioLivro;
    private Conversor conversor = new Conversor();
    private final String ADDRESS = "https://gutendex.com/books?search=";

    public Principal(AutorRepository repositorioAutor, LivroRepository repositorioLivro) {
        this.repositorioAutor = repositorioAutor;
        this.repositorioLivro = repositorioLivro;
    }

    public void principal(){
        String headerBase = """
                -------------------------------------------------------------------------------------------------
                                        BEM VINDO AO NOSSO CATÁLOGO DE LIVROS
                -------------------------------------------------------------------------------------------------
                """;
        System.out.println(headerBase);
        String menu = """
                -------------------------------------------   M E N U   -----------------------------------------
                1 - BUSCAR LIVRO PELO TITULO
                2 - LISTAR LIVROS REGISTRADOS
                3 - LISTAR AUTORES REGISTRADOS
                4 - LISTAR AUTORES VIVOS EM DETERMINADO ANO
                5 - SAIR
                -------------------------------------------------------------------------------------------------
                """;
        var opcao = -1;
        while (opcao != 5){
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    buscaLivroTitulo();
                    break;
                case 2:
                    buscaLivrosRegistrados();
                    break;
                case 3:
                    buscaAutoresRegistrados();
                    break;
                case 4:
                    buscaAutoresVivosPorAno();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("\n Digite uma opção valida (Opções de 1 a 5) \n");
            }
        }
    }

    private void buscaLivroTitulo() {
        System.out.println("\nDigite o nome do livro que seja buscar:");
        var buscaDoUsuario = sc.nextLine();
        var dados = requisicao.UtilizaAPI(ADDRESS + buscaDoUsuario.replace(" ","%20"));
        salvaNoDb(dados);
    }

    private void salvaNoDb(String dados){
        try{
            Livro livro = new Livro(conversor.getData(dados, DadosLivro.class));
            Autor autor = new Autor(conversor.getData(dados, DadosAutor.class));
            Autor autorDb = null;
            Livro livroDb = null;
            if (!repositorioAutor.existsByNome(autor.getNome())){
                repositorioAutor.save(autor);
                autorDb = autor;
            }else{
                autorDb = repositorioAutor.findByNome(autor.getNome());
            }
            if (!repositorioLivro.existsByNome(livro.getNome())){
                livro.setAutor(autorDb);
                repositorioLivro.save(livro);
                livroDb = livro;
            }else{
                livroDb = repositorioLivro.findByNome(livro.getNome());
            }
            System.out.println(livroDb);
        }catch (NullPointerException e){
            System.out.println("\n\n---------------  Livro   não   encontrado     -----------------\n\n");
        }
    }

    private void buscaLivrosRegistrados() {
        var buscasDB = repositorioLivro.findAll();
        if(!buscasDB.isEmpty()){
            System.out.println("\nLivros cadastrados com sucesso! ");
            buscasDB.forEach(System.out::println);
        }else{
            System.out.println("\nNão foi possivel encontrar este livro.");
        }
    }

    private void buscaAutoresRegistrados() {
        var buscaDb = repositorioAutor.findAll();
        if(!buscaDb.isEmpty()){
            System.out.println("\nAutores cadastrados com sucesso!");
            buscaDb.forEach(System.out::println);
        }else{
            System.out.println("\nNão foi possivel encontrar este autor.");
        }
    }

    private void buscaAutoresVivosPorAno() {
        System.out.println("\nDigite o ano que deseja buscar:");
        var anoSelecionado = sc.nextInt();
        sc.nextLine();
        var buscaAutoresDb = repositorioAutor.buscaPorAnoDeFalecimento(anoSelecionado);
        if(!buscaAutoresDb.isEmpty()){
            System.out.println("\nAutores vivos neste ano: " + anoSelecionado);
            buscaAutoresDb.forEach(System.out::println);
        }else {
            System.out.println("\nNão foi possivel encontrar autores nesta data.");
        }
    }
}

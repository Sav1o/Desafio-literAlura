package com.alura.literatura.repository;

import com.alura.literatura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository  extends JpaRepository<Livro,Long> {

    boolean existsByNome(String nome);

    @Query("SELECT DISTINCT b.idioma FROM Livro b ORDER BY b.idioma")
    List<String> buscaidiomas();

    @Query("SELECT b FROM Livro b WHERE idioma = :idiomaSelecionado")
    List<Livro> buscaPorIdioma(String idiomaSelecionado);

    Livro findByNome(String nome);
}

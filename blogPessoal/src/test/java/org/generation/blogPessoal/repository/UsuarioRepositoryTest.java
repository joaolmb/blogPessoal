package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	void stard() {
		repository.deleteAll();
		
		repository.save(new Usuario(0L, "Victor Marques", "joao@joao", "12345678", "FOTO"));
		repository.save(new Usuario(0L, "João Bento", "bento@joao", "12345678", "FOTO"));
		repository.save(new Usuario(0L, "João David", "david@joao", "12345678", "FOTO"));
		repository.save(new Usuario(0L, "João Victor", "victor@joao", "12345678", "FOTO"));
	}
	
	@Test
	@DisplayName("Retorna um usuário")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = repository.findByUsuario("joao@joao");
		assertTrue(usuario.get().getUsuario().equals("joao@joao"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuários")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = repository.findAllByNomeContainingIgnoreCase("joão");
		
		assertEquals(3, listaDeUsuarios.size());
		
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João Bento"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("João David"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("João Victor"));
		
	}
	
	@AfterAll
	public void end() {
		repository.deleteAll();
	}

}

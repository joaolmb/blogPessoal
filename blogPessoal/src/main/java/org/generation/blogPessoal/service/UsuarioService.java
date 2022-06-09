package org.generation.blogPessoal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) 
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe.", null);
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(usuarioRepository.Save(usuario));
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		if(usuarioRepository.findById(usuario.getId()))
	}

}
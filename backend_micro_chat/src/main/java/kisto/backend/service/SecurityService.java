package kisto.backend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService{

	boolean isUsuarioInConversa(Authentication authentication, Long usuarioId, Long conversaId);
}
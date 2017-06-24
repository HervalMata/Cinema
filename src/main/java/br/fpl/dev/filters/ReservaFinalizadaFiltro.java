package br.fpl.dev.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.fpl.dev.controllers.ResultadoMB;

@WebFilter(urlPatterns={"/",
						"/index.jsf",
						"/views/selecao-filme.jsf", 
						"/views/selecao-sessao.jsf",
						"/views/selecao-assento.jsf",
						"/views/selecao-reserva.jsf"})
public class ReservaFinalizadaFiltro implements Filter {
	
	@Inject
	private ResultadoMB resultado;
	
	public void destroy(){
		
	}
	
	
	/**
	 * Evita que uma nova escolha seja feita, enquanto a antiga sessão não for finalizada
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if(resultado.isFinalizado() == true){
			res.sendRedirect(req.getServletContext().getContextPath() + "/views/resultado/confirmacao.jsf");
		}
		
		chain.doFilter(request, response);
		
	}
	
	public void init(FilterConfig arg0) throws ServletException {

	}
}

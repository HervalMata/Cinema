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

import br.fpl.dev.controllers.SelecaoSessaoMB;

/**
 * Verifica se o filme ja foi selecionado
 * caso ja tenha sido, permite que entre na seleção de sessão
 * caso não, retorna para a página inicial
 * @author Lucas P. França
 *
 */
@WebFilter(urlPatterns="/views/selecao-sessao.jsf")
public class SessaoFiltro implements Filter {
	
	@Inject
	private SelecaoSessaoMB selecaoSessao;
	
	public void destroy(){
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if (selecaoSessao.getFilmeSelecionado() == null){
			res.sendRedirect(req.getServletContext().getContextPath() + "/index.jsf");
		}
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

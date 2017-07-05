package br.fpl.dev.validators;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import br.fpl.dev.controllers.SelecaoBean;

@Named
@RequestScoped
public class Validators implements Serializable {

	@Inject
	private SelecaoBean selecao;

	public void validateFilmeSelecionado(FacesContext context, UIComponent uiComponent, Object o)
			throws ValidatorException {

		if (selecao.getFilmeSelecionado() == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Escolha um filme!",
					"É necessário escolher um filme para prosseguir.");
			throw new ValidatorException(message);
		}
	}

	public void validateSessaoSelecionada(FacesContext context, UIComponent uiComponent, Object o)
			throws ValidatorException {
		
		if (o == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Escolha uma Sessão!",
					"É necessário escolher uma sessão para prosseguir.");
			throw new ValidatorException(message);
		}
	}
	
	public void validateAssentoSelecionado(FacesContext context, UIComponent uiComponent, Object o)
			throws ValidatorException {
		
		if (o == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Escolha um Assento!",
					"É necessário escolher um assento para prosseguir.");
			throw new ValidatorException(message);
		}
	}
}

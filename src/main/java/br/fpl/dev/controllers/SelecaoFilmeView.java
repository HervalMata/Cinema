package br.fpl.dev.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.fpl.dev.entities.Filme;

@ManagedBean
@SessionScoped
public class SelecaoFilmeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1183670266389529517L;

	private List<Filme> filmes;

	private List<String> images;
	
	private String option;
	
	@PostConstruct
    public void init() {
        images = new ArrayList<>();
         
        images.add("mulher-maravilha.jpg");
        images.add("a-mumia.jpg");
        images.add("piratas-do-caribe.jpg");
        images.add("neve-negra.jpg");
        images.add("meus-15-anos.jpg");
        images.add("a-cabana.jpg");
        images.add("o-jardin-das-aflicoes.jpg");
    }

	
	
	
	public String getOption() {
		return option;
	}




	public void setOption(String option) {
		this.option = option;
	}




	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<Filme> getFilme() {
		return filmes;
	}

	public void setFilme(List<Filme> filme) {
		this.filmes = filme;
	}

}

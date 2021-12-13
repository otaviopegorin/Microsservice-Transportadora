package br.com.pegorinmoveis.Transportadora.modelo;

import java.util.ArrayList;
import java.util.List;


public class ListaDeEntregas {
	private List<Entrega> entregas = new ArrayList<>();

	public List<Entrega> getEntregas() {
		return entregas;
	}

	public void setProdutos(List<Entrega> entregas) {
		this.entregas = entregas;
	}
	
	public void adicionar(Entrega entrega) {
		entregas.add(entrega);
	}
}
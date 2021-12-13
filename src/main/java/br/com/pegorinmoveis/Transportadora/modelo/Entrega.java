package br.com.pegorinmoveis.Transportadora.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Entrega {

	@Enumerated(EnumType.STRING)
	private Status status;

	private LocalDate data;

	private String nomeProduto;

	private String enderecoDestinatario;

	private String enderecoRemetente="Rua Miguel Braz Arroteia (59) - Itaju - Sp - Brasil";

	@Id
	@Column(unique=true)
	private String codigoDeRastreamento;

	public Entrega() {}
	public Entrega(Status status, String nomeProduto, String enderecoDestinatario, String codigoDeRastreamento) {
		this.status = status;
		this.nomeProduto = nomeProduto;
		this.enderecoDestinatario = enderecoDestinatario;
		this.codigoDeRastreamento = codigoDeRastreamento;
		this.enderecoRemetente="Rua Miguel Braz Arroteia (59) - Itaju - Sp - Brasil";
		this.data = LocalDate.now();
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setIdPedido(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getEnderecoDestinatario() {
		return enderecoDestinatario;
	}

	public void setEnderecoDestinatario(String enderecoDestinatario) {
		this.enderecoDestinatario = enderecoDestinatario;
	}

	public String getEnderecoRemetente() {
		return enderecoRemetente;
	}


	public String getCodigoDeRastreamento() {
		return codigoDeRastreamento;
	}

	public void setCodigoDeRastreamento(String codigoDeRastreamento) {
		this.codigoDeRastreamento = codigoDeRastreamento;
	}

}

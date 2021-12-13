package br.com.pegorinmoveis.Transportadora.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pegorinmoveis.Transportadora.Repository.EntregaRepository;
import br.com.pegorinmoveis.Transportadora.modelo.Entrega;
import br.com.pegorinmoveis.Transportadora.modelo.ListaDeEntregas;
import br.com.pegorinmoveis.Transportadora.modelo.Status;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository repository;
	
	public void criarNovaEntrega(String nomeProduto, String endereco) {
		Entrega entrega = new Entrega(Status.PREPARANDO_PARA_A_ENTREGA,nomeProduto,endereco,gerarCodigoDeRastreamento());
		repository.save(entrega);
	}

	private String gerarCodigoDeRastreamento() {
		boolean aux = true;
		String codigo = "";
		while(aux) {
			Random random = new Random();
			for(int a=0; a<10;a++) {
				codigo += random.nextInt(9);
			}
			Optional<Entrega> entrega = repository.findById(codigo);
			if(entrega != null) {
				aux = false;
			}
		}
		return codigo;
	}

	
	
	public void organizarEntregas() {
		registrarItensEntregues();//Se faz mais de 10 dias q a entrega foi registrada ele registra ela como entregue, pois aqui a entrega é rapida :)
		registrarItensACaminho();//se faz de 10 a 5 dias que o item foi postado então ele está a caminho
	}

	private void registrarItensACaminho() {
		List<Entrega> entregas = repository.findByDataMenorQueCincoDias();
		entregas.forEach(e -> {
			e.setStatus(Status.A_CAMINHO);
			repository.save(e);
		});
	}

	private void registrarItensEntregues() {
		List<Entrega> entregas = repository.findByDataMenorQueDezDias();
		entregas.forEach(e -> {
			e.setStatus(Status.ENTREGUE);
			repository.save(e);
		});
	}

	public ListaDeEntregas pegarEntregas(int pagina) {
		Pageable pageable = PageRequest.of(pagina, 10);
		organizarEntregas();
		ListaDeEntregas lista = new ListaDeEntregas();
		Page<Entrega> pageEntregas = repository.findAll(pageable);
		pageEntregas.forEach(entrega -> {
			lista.adicionar(entrega);
		});
		return lista;
	}

	public int pegarTotalDePaginas() {
		Pageable pageable = PageRequest.of(1, 10);
		int totalPaginas = repository.findAll(pageable).getTotalPages();
		return totalPaginas;
	}
}

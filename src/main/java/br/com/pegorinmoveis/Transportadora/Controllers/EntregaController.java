package br.com.pegorinmoveis.Transportadora.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pegorinmoveis.Transportadora.Service.EntregaService;
import br.com.pegorinmoveis.Transportadora.modelo.ListaDeEntregas;

@RestController
@RequestMapping("/transportadora")
public class EntregaController {

	@Autowired
	private EntregaService service;
	
	@GetMapping("/novaEntrega/{nomeProduto}&{endereco}")
	private void criarEntrega(@PathVariable String nomeProduto, @PathVariable String endereco) {
		service.criarNovaEntrega(nomeProduto, endereco);
	}
	
	@GetMapping("/entregas/{pagina}")
	private ListaDeEntregas acompanharEntregas(@PathVariable int pagina){
		return service.pegarEntregas(pagina);
	}
	
	@GetMapping("/totalDePaginas")
	private int resgatarTotalDePaginas(){
		return service.pegarTotalDePaginas();
	}
}

package ar.edu.unlam.tallerweb1.controladores;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente;
import ar.edu.unlam.tallerweb1.modelo.Sanguchetto;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.modelo.Stock;
import ar.edu.unlam.tallerweb1.modelo.TipoIngrediente;

@Controller
public class ControladoresSanguchettoWeb {

	private User usuario;
	
	public ControladoresSanguchettoWeb(){
		inicializarStock();
	}
	
	@RequestMapping(path = "/")
	public ModelAndView irAHome() {
		ModelMap modelo = new ModelMap();
		usuario=null;
		modelo.put("user", new User());
		return new ModelAndView("home", modelo);
	}

	@RequestMapping(value = "/redireccionar", method = RequestMethod.POST)
	public ModelAndView redireccionar(@ModelAttribute("user") User user) {
		usuario=user;
		if (user.getUsername().equalsIgnoreCase("admin"))
			return new ModelAndView("redirect:/gestion-sitio");
		return new ModelAndView("redirect:/prepara-tu-sanguche");
	}

	@RequestMapping(value = "/prepara-tu-sanguche", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView preparaTuSanguche(@ModelAttribute(value="agregarIng")Ingrediente ingredienteAgregar,
										  @ModelAttribute(value="quitarIng")Ingrediente ingredienteQuitar){
		if(usuario==null)
			return new ModelAndView();
		ModelMap modelo = new ModelMap();
		Stock stock = Stock.getInstance();
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
		Set<Ingrediente> condimentos = new HashSet<Ingrediente>();
		Set<Ingrediente> listaMezclada = stock.listarIngredientesDisponibles();
		dividirIngredientes(ingredientes, condimentos, listaMezclada);
		modelo.put("username", usuario.getUsername());
		modelo.put("ingredientes", ingredientes);
		modelo.put("condimentos", condimentos);
		modelo.put("sanguche",Sanguchetto.getInstance());
		return new ModelAndView("preparacion", modelo);
	}
	
	@RequestMapping(value = "/prepara-tu-sanguche/agregar", method = RequestMethod.POST)
	public ModelAndView agregarIngredientes(@ModelAttribute(value="agregarIng")Ingrediente ingredienteAgregar){
		Sanguchetto.getInstance().agregarIngrediente(ingredienteAgregar);
		return new ModelAndView("redirect:/prepara-tu-sanguche");
	}
	
	@RequestMapping(value = "/prepara-tu-sanguche/quitar", method = RequestMethod.POST)
	public ModelAndView quitarIngredientes(@ModelAttribute(value="quitarIng")Ingrediente ingredienteQuitar){
		Sanguchetto.getInstance().quitarIngrediente(ingredienteQuitar);
		return new ModelAndView("redirect:/prepara-tu-sanguche");
	}
	@RequestMapping(value = "/prepara-tu-sanguche/cancelar")
	public ModelAndView cancelarCompra(){
		Sanguchetto.getInstance().vaciar();
		return new ModelAndView("redirect:/prepara-tu-sanguche");
	}
	@RequestMapping(value = "/compra-realizada")
	public ModelAndView compraRealizada(){
		Sanguchetto.getInstance().comprar();
		ModelMap modelo=new ModelMap();
		modelo.put("username",usuario.getUsername());
		return new ModelAndView("compraRealizada",modelo);
	}
	
	
	@RequestMapping(value = "/gestion-sitio")
	public ModelAndView gestionSitio() {
		if(usuario==null)
			return new ModelAndView();
		ModelMap modelo = new ModelMap();
		Stock stock = Stock.getInstance();
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
		Set<Ingrediente> condimentos = new HashSet<Ingrediente>();
		Set<Ingrediente> listaMezclada = stock.listarIngredientes();
		dividirIngredientes(ingredientes, condimentos, listaMezclada);
		modelo.put("ingredientes", ingredientes);
		modelo.put("condimentos", condimentos);
		modelo.put("stock",stock);
		modelo.put("username", usuario.getUsername());
		return new ModelAndView("gestion", modelo);
	}

	private static void dividirIngredientes(Set<Ingrediente> ingredientes, Set<Ingrediente> condimentos,
			Set<Ingrediente> listaMezclada) {
		for (Ingrediente ingrediente : listaMezclada) {
			if (ingrediente.getTipo().equals(TipoIngrediente.INGREDIENTE))
				ingredientes.add(ingrediente);
			else
				condimentos.add(ingrediente);
		}
	}
	private static void inicializarStock(){
		Stock stock = Stock.getInstance();
		if (stock.listarIngredientesDisponibles().isEmpty()) {
			Ingrediente mayonesa = new Ingrediente("mayonesa", 0.50, TipoIngrediente.CONDIMENTO);
			Ingrediente vinagre = new Ingrediente("vinagre", 1.50, TipoIngrediente.CONDIMENTO);
			Ingrediente salsaCasa = new Ingrediente("salsa de la casa", 2.50, TipoIngrediente.CONDIMENTO);
			Ingrediente ketchup = new Ingrediente("ketchup", 0.50, TipoIngrediente.CONDIMENTO);
			Ingrediente mostaza = new Ingrediente("mostaza", 0.50, TipoIngrediente.CONDIMENTO);
			Ingrediente salsaGolf = new Ingrediente("salsa golf", 0.50, TipoIngrediente.CONDIMENTO);
			Ingrediente papasPays = new Ingrediente("papas pays", 1., TipoIngrediente.CONDIMENTO);
			Ingrediente salsaBolo = new Ingrediente("salsa boloñesa", 1.50, TipoIngrediente.CONDIMENTO);
			Ingrediente jamon = new Ingrediente("jamon", 5.0, TipoIngrediente.INGREDIENTE);
			Ingrediente queso = new Ingrediente("queso", 5.0, TipoIngrediente.INGREDIENTE);
			Ingrediente pollo = new Ingrediente("pollo", 10.0, TipoIngrediente.INGREDIENTE);
			Ingrediente churrasco = new Ingrediente("churrasco", 20.0, TipoIngrediente.INGREDIENTE);
			Ingrediente tomate = new Ingrediente("tomate", 5.0, TipoIngrediente.INGREDIENTE);
			Ingrediente lechuga = new Ingrediente("lechuga", 1.2, TipoIngrediente.INGREDIENTE);
			Ingrediente pezcado = new Ingrediente("pezcado", 15.5, TipoIngrediente.INGREDIENTE);
			stock.agregarIngrediente(mayonesa);
			stock.agregarIngrediente(ketchup);
			stock.agregarIngrediente(mostaza);
			stock.agregarIngrediente(salsaGolf);
			stock.agregarIngrediente(vinagre);
			stock.agregarIngrediente(papasPays);
			stock.agregarIngrediente(salsaCasa);
			stock.agregarIngrediente(salsaBolo);
			stock.agregarIngrediente(jamon);
			stock.agregarIngrediente(queso);
			stock.agregarIngrediente(pollo);
			stock.agregarIngrediente(churrasco);
			stock.agregarIngrediente(tomate);
			stock.agregarIngrediente(lechuga);
			stock.agregarIngrediente(pezcado);
			stock.agregarStock(mayonesa, 5);
			stock.agregarStock(ketchup, 5);
			stock.agregarStock(mostaza, 5);
			stock.agregarStock(salsaGolf, 5);
			stock.agregarStock(vinagre, 5);
			stock.agregarStock(salsaCasa, 5);
			stock.agregarStock(salsaBolo, 5);
			stock.agregarStock(papasPays, 5);
			stock.agregarStock(jamon, 5);
			stock.agregarStock(queso, 5);
			stock.agregarStock(pollo, 5);
			stock.agregarStock(churrasco, 5);
			stock.agregarStock(tomate, 5);
			stock.agregarStock(lechuga, 5);
			stock.agregarStock(pezcado, 5);
		}
	}
}

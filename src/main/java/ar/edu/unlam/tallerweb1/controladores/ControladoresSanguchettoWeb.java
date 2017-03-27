package ar.edu.unlam.tallerweb1.controladores;

import java.util.HashSet;
import java.util.Set;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente;
import ar.edu.unlam.tallerweb1.modelo.ObjetoCompra;
import ar.edu.unlam.tallerweb1.modelo.Stock;
import ar.edu.unlam.tallerweb1.modelo.TipoIngrediente;

public class ControladoresSanguchettoWeb {

	@RequestMapping(
			value = "/gestion-sitio")
	public ModelAndView gestionSitio() {
		if (usuario == null)
			return new ModelAndView();
		ModelMap modelo = new ModelMap();
		Stock stock = Stock.getInstance();
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
		Set<Ingrediente> condimentos = new HashSet<Ingrediente>();
		Set<Ingrediente> listaMezclada = stock.listarIngredientes();
		dividirIngredientes(ingredientes, condimentos, listaMezclada);
		modelo.put("ingredientes", ingredientes);
		modelo.put("condimentos", condimentos);
		modelo.put("stock", stock);
		modelo.put("username", usuario.getUsername());
		modelo.put("objetoCompra", new ObjetoCompra());
		modelo.put("ingredienteEliminar", new Ingrediente());
		return new ModelAndView("gestion", modelo);
	}

	@RequestMapping(
			value = "/gestion-sitio/eliminar-ingrediente")
	public ModelAndView agregarStock(@ModelAttribute(
			value = "ingredienteEliminar") Ingrediente ingrediente) {
		Stock.getInstance().eliminarIngrediente(ingrediente);
		return new ModelAndView("redirect:/gestion-sitio");
	}

	@RequestMapping(
			value = "/gestion-sitio/comprar")
	public ModelAndView agregarStock(@ModelAttribute(
			value = "objetoCompra") ObjetoCompra compra) {
		Stock.getInstance().agregarStock(new Ingrediente(compra.getNombre()), compra.getCantidad());
		return new ModelAndView("redirect:/gestion-sitio");
	}

	@RequestMapping(
			value = "/condimento-nuevo")
	public ModelAndView condimentoNuevo() {
		ModelMap modelo = new ModelMap();
		modelo.put("condimento", new Ingrediente());
		return new ModelAndView("agregarCondimento", modelo);
	}

	@RequestMapping(
			value = "/condimento-nuevo/datos")
	public ModelAndView condimentoNuevoDatos(@ModelAttribute(
			value = "condimento") Ingrediente condimento) {
		condimento.setTipo(TipoIngrediente.CONDIMENTO);
		Stock.getInstance().agregarIngrediente(condimento);
		return new ModelAndView("redirect:/gestion-sitio");
	}

	@RequestMapping(
			value = "/ingrediente-nuevo")
	public ModelAndView ingredienteNuevo() {
		ModelMap modelo = new ModelMap();
		modelo.put("ingrediente", new Ingrediente());
		return new ModelAndView("agregarIngrediente", modelo);
	}

	@RequestMapping(
			value = "/ingrediente-nuevo/datos")
	public ModelAndView ingredienteNuevoDatos(@ModelAttribute(
			value = "ingrediente") Ingrediente ingrediente) {
		ingrediente.setTipo(TipoIngrediente.INGREDIENTE);
		Stock.getInstance().agregarIngrediente(ingrediente);
		return new ModelAndView("redirect:/gestion-sitio");
	}

	private static void dividirIngredientes(Set<Ingrediente> ingredientes, Set<Ingrediente> condimentos, Set<Ingrediente> listaMezclada) {
		for (Ingrediente ingrediente: listaMezclada) {
			if (ingrediente.getTipo().equals(TipoIngrediente.INGREDIENTE))
				ingredientes.add(ingrediente);
			else
				condimentos.add(ingrediente);
		}
	}

	private static void inicializarStock() {
		Stock stock = Stock.getInstance();
		if (stock.listarIngredientesDisponibles().isEmpty()) {
			Ingrediente mayonesa = new Ingrediente("mayonesa", 0.50, TipoIngrediente.CONDIMENTO);
			Ingrediente vinagre = new Ingrediente("vinagre", 1.50, TipoIngrediente.CONDIMENTO);
			Ingrediente salsaCasa = new Ingrediente("salsa de la casa", 2.50, TipoIngrediente.CONDIMENTO);
			Ingrediente ketchup = new Ingrediente("ketchup", 0.50, TipoIngrediente.CONDIMENTO);
			Ingrediente mostaza = new Ingrediente("mostaza", 0.50, TipoIngrediente.CONDIMENTO);
			Ingrediente salsaGolf = new Ingrediente("salsa golf", 0.50, TipoIngrediente.CONDIMENTO);
			Ingrediente papasPays = new Ingrediente("papas pays", 1., TipoIngrediente.CONDIMENTO);
			Ingrediente salsaBolo = new Ingrediente("salsa bolognesa", 1.50, TipoIngrediente.CONDIMENTO);
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

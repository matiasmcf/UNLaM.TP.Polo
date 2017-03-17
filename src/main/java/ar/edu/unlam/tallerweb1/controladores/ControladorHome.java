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
import ar.edu.unlam.tallerweb1.modelo.Persona;
import ar.edu.unlam.tallerweb1.modelo.Stock;
import ar.edu.unlam.tallerweb1.modelo.TipoIngrediente;

@Controller
public class ControladorHome {

	@RequestMapping(path = "/")
	public ModelAndView irAHome() {
		ModelMap modelo = new ModelMap();
		modelo.put("persona", new Persona());
		return new ModelAndView("home", modelo);
	}
	
	@RequestMapping(
			value = "/prepara-tu-sanguche",
			method = RequestMethod.POST)
	public ModelAndView bienvenida(@ModelAttribute("persona") Persona persona) {
		ModelMap modelo = new ModelMap();
		Stock stock = Stock.getInstance();
		Ingrediente mayonesa = new Ingrediente("mayonesa",2.50,TipoIngrediente.CONDIMENTO);
		Ingrediente jamon = new Ingrediente("jamon",5.0,TipoIngrediente.INGREDIENTE);
		stock.agregarIngrediente(mayonesa);
		stock.agregarIngrediente(jamon);
		stock.agregarStock(mayonesa,10);
		stock.agregarStock(jamon,10);
		modelo.put("persona", persona);
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();		//separo condimentos de ingredientes
		Set<Ingrediente> condimentos = new HashSet<Ingrediente>();
		Set<Ingrediente> listaMezclada= stock.listarIngredientesDisponibles();
		for (Ingrediente ingrediente : listaMezclada) {
			if(ingrediente.getTipo().equals(TipoIngrediente.INGREDIENTE))
				ingredientes.add(ingrediente);
			else
				condimentos.add(ingrediente);
		}
		modelo.put("ingredientes",ingredientes);
		modelo.put("condimentos",condimentos);
		return new ModelAndView("preparacion", modelo);
	}
}

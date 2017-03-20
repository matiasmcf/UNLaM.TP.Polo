package ar.edu.unlam.tallerweb1.controladores;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente;
import ar.edu.unlam.tallerweb1.modelo.User;
import sun.security.jca.GetInstance;
import ar.edu.unlam.tallerweb1.modelo.Stock;
import ar.edu.unlam.tallerweb1.modelo.TipoIngrediente;

@Controller
public class ControladoresSanguchettoWeb {

	@RequestMapping(path = "/")
	public ModelAndView irAHome() {
		ModelMap modelo = new ModelMap();
		modelo.put("user", new User());
		return new ModelAndView("home", modelo);
	}

	@RequestMapping(value = "/redireccionar",method = RequestMethod.POST)
	public ModelAndView redireccionar(@ModelAttribute("user") User user) {
		String username= user.getUsername();
		if(username.equalsIgnoreCase("admin"))
			return new ModelAndView("redirect:/gestion-sitio?username="+username);
		return new ModelAndView("redirect:/prepara-tu-sanguche?username="+username);
	}
	@RequestMapping(
			value = "/prepara-tu-sanguche",
			method = RequestMethod.GET)
	public ModelAndView preparaTuSanguche(@RequestParam(value = "username") String username){
		ModelMap modelo = new ModelMap();
		Stock stock = Stock.getInstance();
		Ingrediente mayonesa = new Ingrediente("mayonesa",2.50,TipoIngrediente.CONDIMENTO);
		Ingrediente jamon = new Ingrediente("jamon",5.0,TipoIngrediente.INGREDIENTE);
		stock.agregarIngrediente(mayonesa);
		stock.agregarIngrediente(jamon);
		stock.agregarStock(mayonesa,10);
		stock.agregarStock(jamon,10);
		modelo.put("username", username);
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();		//separo condimentos de ingredientes
		Set<Ingrediente> condimentos = new HashSet<Ingrediente>();
		Set<Ingrediente> listaMezclada= stock.listarIngredientesDisponibles();
		dividirIngredientes(ingredientes,condimentos,listaMezclada);
		modelo.put("ingredientes",ingredientes);
		modelo.put("condimentos",condimentos);
		return new ModelAndView("preparacion", modelo);
	}
	@RequestMapping(value = "/gestion-sitio",method = RequestMethod.GET)
	public ModelAndView redireccionar(@RequestParam(value="username")String username) {
		ModelMap modelo = new ModelMap();
		Stock stock=Stock.getInstance();
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();		//separo condimentos de ingredientes
		Set<Ingrediente> condimentos = new HashSet<Ingrediente>();
		Set<Ingrediente> listaMezclada= stock.listarIngredientesDisponibles();
		dividirIngredientes(ingredientes,condimentos,listaMezclada);
		modelo.put("ingredientes",ingredientes);
		modelo.put("condimentos",condimentos);
		modelo.put("username",username);
		return new ModelAndView("gestion",modelo);
	}
	
	private static void dividirIngredientes(Set<Ingrediente> ingredientes,Set<Ingrediente> condimentos,Set<Ingrediente> listaMezclada){
		for (Ingrediente ingrediente : listaMezclada) {
			if(ingrediente.getTipo().equals(TipoIngrediente.INGREDIENTE))
				ingredientes.add(ingrediente);
			else
				condimentos.add(ingrediente);
		}
	}
}

package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.User;

@Controller
public class ControladorAplicacion {

	@RequestMapping(
			value = "/bienvenida",
			method = RequestMethod.POST)
	public ModelAndView bienvenida(@ModelAttribute("persona") User persona) {
		ModelMap modelo = new ModelMap();
		ArrayList<User> lista = new ArrayList<User>();
		lista.add(new User("nombre1", "apellido1"));
		lista.add(new User("nombre2", "apellido2"));
		lista.add(new User("nombre3", "apellido3"));
		lista.add(new User("nombre4", "apellido4"));
		lista.add(new User("nombre5", "apellido5"));
		lista.add(new User("nombre6", "apellido6"));
		lista.add(new User("nombre7", "apellido7"));
		modelo.put("persona", persona);
		modelo.put("listaPersonas", lista);
		return new ModelAndView("bienvenida", modelo);
	}

}

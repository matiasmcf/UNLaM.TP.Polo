package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Persona;

@Controller
public class ControladorAplicacion {

	@RequestMapping(
			value = "/hola",
			method = RequestMethod.GET)
	public ModelAndView bienvenidaQueryString(@RequestParam(
			value = "nombre") String nombre,
			@RequestParam(
					value = "apellido",
					required = false) String apellido) {
		String mensajeNombre = nombre;
		String mensajeApellido = apellido;
		ModelMap modelo = new ModelMap();
		modelo.put("m1", mensajeNombre);
		modelo.put("m2", mensajeApellido);
		return new ModelAndView("bienvenidaQueryString", modelo);
	}

	@RequestMapping(
			value = "/hola/n/{nombre}/a/{apellido}",
			method = RequestMethod.GET)
	public ModelAndView bienvenidaPathVariable(@PathVariable String nombre, @PathVariable String apellido) {
		String mensajeNombre = nombre;
		String mensajeApellido = apellido;
		ModelMap modelo = new ModelMap();
		modelo.put("m1", mensajeNombre);
		modelo.put("m2", mensajeApellido);
		return new ModelAndView("bienvenidaPathVariable", modelo);
	}

	@RequestMapping(
			value = "/hola/objetoPersona",
			method = RequestMethod.GET)
	public ModelAndView bienvenidaObjeto() {
		ModelMap modelo = new ModelMap();
		modelo.put("persona", new Persona("Hola", "Mundo"));
		return new ModelAndView("bienvenidaObjetoPersona", modelo);
	}

	@RequestMapping(
			value = "/bienvenida",
			method = RequestMethod.POST)
	public ModelAndView bienvenida(@ModelAttribute("persona") Persona persona) {
		ModelMap modelo = new ModelMap();
		ArrayList<Persona> lista = new ArrayList<Persona>();
		lista.add(new Persona("nombre1", "apellido1"));
		lista.add(new Persona("nombre2", "apellido2"));
		lista.add(new Persona("nombre3", "apellido3"));
		lista.add(new Persona("nombre4", "apellido4"));
		lista.add(new Persona("nombre5", "apellido5"));
		lista.add(new Persona("nombre6", "apellido6"));
		lista.add(new Persona("nombre7", "apellido7"));
		modelo.put("persona", persona);
		modelo.put("listaPersonas", lista);
		return new ModelAndView("bienvenida", modelo);
	}

	@RequestMapping("/")
	public ModelAndView indexController() {
		ModelMap modelo = new ModelMap();
		modelo.put("persona", new Persona());
		return new ModelAndView("index", modelo);
	}
}
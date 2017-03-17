package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Persona;

@Controller
public class ControladorEjemplos {

	@RequestMapping(
			value = "/ejemplo",
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
			value = "/ejemplo/n/{nombre}/a/{apellido}",
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
			value = "/ejemplo/objetoPersona",
			method = RequestMethod.GET)
	public ModelAndView bienvenidaObjeto() {
		ModelMap modelo = new ModelMap();
		modelo.put("persona", new Persona("Hola", "Mundo"));
		return new ModelAndView("bienvenidaObjetoPersona", modelo);
	}
}

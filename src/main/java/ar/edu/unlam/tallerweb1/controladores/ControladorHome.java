package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Persona;

@Controller
public class ControladorHome {

	@RequestMapping(
			path = "/")
	public ModelAndView irAHome() {
		ModelMap modelo = new ModelMap();
		modelo.put("persona", new Persona());
		return new ModelAndView("home", modelo);
	}
}

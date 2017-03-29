package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.unlam.tallerweb1.modelo.SQLiteDatabase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario.TipoUsuario;

@Controller
public class ControladorAplicacion {

	@RequestMapping(
			path = "/")
	public ModelAndView irAHome() {
		System.out.println("INICIANDO");
		ModelMap modelo = new ModelMap();
		modelo.put("usuario", new Usuario());
		return new ModelAndView("home", modelo);
	}

	@RequestMapping(
			value = "/redireccionar",
			method = RequestMethod.POST)
	public RedirectView redireccionar(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes atributos) {
		if (!SQLiteDatabase.getInstance().verificarDatos(usuario))
			return new RedirectView("redirect:/");
		atributos.addFlashAttribute("cliente", usuario);
		if (usuario.getTipo().equals(TipoUsuario.ADMIN))
			return new RedirectView("gestion-sitio");
		return new RedirectView("prepara-tu-sanguche");
	}

	@RequestMapping(
			value = "/registrar",
			method = RequestMethod.POST)
	public ModelAndView registrarUsuario(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes atributos) {
		ModelMap modelo = new ModelMap();
		modelo.put("usuario", new Usuario());
		return new ModelAndView("registroUsuario", modelo);
	}

	@RequestMapping(
			value = "/confirmarRegistro",
			method = RequestMethod.POST)
	public ModelAndView confirmarRegistro(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes atributos) {
		if (!SQLiteDatabase.getInstance().registrarUsuario(usuario.getUsername(), usuario.getPassword()))
			return new ModelAndView(new RedirectView("redirect:/confirmarRegistro"));
		ModelMap modelo = new ModelMap();
		modelo.put("usuario", new Usuario());
		return new ModelAndView("home", modelo);
	}
}

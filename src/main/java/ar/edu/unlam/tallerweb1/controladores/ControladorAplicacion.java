package ar.edu.unlam.tallerweb1.controladores;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.SQLiteDatabase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario.TipoUsuario;

@Controller
public class ControladorAplicacion {

	@RequestMapping(
			path = "/")
	public String irAHome(ModelMap modelo) {
		modelo.put("usuario", new Usuario());
		return "home";
	}

	@RequestMapping(
			value = "/redireccionar",
			method = RequestMethod.POST)
	public String redireccionar(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult resultado, RedirectAttributes atributos) {
		if (resultado.hasErrors()) {
			return "home";
		}
		if (!SQLiteDatabase.getInstance().verificarDatos(usuario)) {
			resultado.rejectValue(null, null, "Datos de inicio de sesión incorrectos.");
			return "home";
		}
		atributos.addFlashAttribute("cliente", usuario);
		if (usuario.getTipo().equals(TipoUsuario.ADMIN))
			return "redirect:/gestion-sitio";
		return "redirect:/prepara-tu-sanguche";
	}

	@RequestMapping(
			value = "/registrar")
	public String registrarUsuario(ModelMap modelo) {
		modelo.put("usuario", new Usuario());
		return "registroUsuario";
	}

	@RequestMapping(
			value = "/cancelar")
	public String cancelarRegistro() {
		return "redirect:/";
	}

	@RequestMapping(
			value = "/confirmarRegistro",
			method = RequestMethod.POST)
	public String confirmarRegistro(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult resultado, RedirectAttributes atributos, ModelMap modelo) {
		if (resultado.hasErrors()) {
			return "registroUsuario";
		}
		if (!SQLiteDatabase.getInstance().registrarUsuario(usuario.getUsername(), usuario.getPassword())) {
			resultado.rejectValue(null, null, "No se pudo registrar el usuario: el usuario ya existe.");
			return "redirect:/confirmarRegistro";
		}
		modelo.put("usuario", new Usuario());
		return "home";
	}
}

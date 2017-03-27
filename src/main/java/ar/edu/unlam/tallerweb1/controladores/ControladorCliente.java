package ar.edu.unlam.tallerweb1.controladores;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente;
import ar.edu.unlam.tallerweb1.modelo.Stock;
import ar.edu.unlam.tallerweb1.modelo.TipoIngrediente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Usuarios;

@Controller
@Scope("session")
public class ControladorCliente {

	private Usuario cliente;

	@RequestMapping(
			value = "/prepara-tu-sanguche")
	public ModelAndView preparacion(@ModelAttribute("cliente") Usuario usuario, @ModelAttribute(
			value = "agregarIng") Ingrediente ingredienteAgregar,
			@ModelAttribute(
					value = "quitarIng") Ingrediente ingredienteQuitar,
			RedirectAttributes atributos) {
		if (cliente == null) {
			cliente = new Usuario();
			cliente.clonar(usuario);
		}
		else
			System.out.println("[" + cliente.getUsername() + "] ha recargado la página.");
		ModelMap modelo = new ModelMap();
		Stock stock = Stock.getInstance();
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
		Set<Ingrediente> condimentos = new HashSet<Ingrediente>();
		Set<Ingrediente> listaMezclada = stock.listarIngredientesDisponibles();
		dividirIngredientes(ingredientes, condimentos, listaMezclada);
		modelo.put("userName", cliente.getUsername());
		modelo.put("ingredientes", ingredientes);
		modelo.put("condimentos", condimentos);
		modelo.put("sanguche", Usuarios.getInstance().obtenerSanguche(cliente));
		return new ModelAndView("preparacion", modelo);
	}

	@RequestMapping(
			value = "/prepara-tu-sanguche/{accion}",
			method = RequestMethod.POST)
	public ModelAndView acciones(@PathVariable String accion, @ModelAttribute(
			value = "agregarIng") Ingrediente ingredienteAgregar,
			@ModelAttribute(
					value = "quitarIng") Ingrediente ingredienteQuitar) {
		if (accion.equals("agregar")) {
			Usuarios.getInstance().obtenerSanguche(cliente).agregarIngrediente(ingredienteAgregar);
		}
		if (accion.equals("quitar")) {
			Usuarios.getInstance().obtenerSanguche(cliente).quitarIngrediente(ingredienteQuitar);
		}
		if (accion.equals("cancelar")) {
			Usuarios.getInstance().obtenerSanguche(cliente).vaciar();
		}
		if (accion.equals("salir")) {
			Usuarios.getInstance().quitarUsuario(cliente);
			cliente = null;
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("redirect:/prepara-tu-sanguche");
	}

	@RequestMapping(
			value = "/compra-realizada")
	public ModelAndView compraRealizada() {
		Usuarios.getInstance().obtenerSanguche(cliente).comprar();
		ModelMap modelo = new ModelMap();
		modelo.put("username", cliente.getUsername());
		return new ModelAndView("compraRealizada", modelo);
	}

	private static void dividirIngredientes(Set<Ingrediente> ingredientes, Set<Ingrediente> condimentos, Set<Ingrediente> listaMezclada) {
		for (Ingrediente ingrediente: listaMezclada) {
			if (ingrediente.getTipo().equals(TipoIngrediente.INGREDIENTE))
				ingredientes.add(ingrediente);
			else
				condimentos.add(ingrediente);
		}
	}
}

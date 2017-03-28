package ar.edu.unlam.tallerweb1.controladores;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente;
import ar.edu.unlam.tallerweb1.modelo.ObjetoCompra;
import ar.edu.unlam.tallerweb1.modelo.SQLiteDatabase;
import ar.edu.unlam.tallerweb1.modelo.Stock;
import ar.edu.unlam.tallerweb1.modelo.Ingrediente.TipoIngrediente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Controller
@Scope("session")
public class ControladorGestion {

	private Usuario administrador;

	@RequestMapping(
			value = "/gestion-sitio")
	public ModelAndView gestionSitio(@ModelAttribute("cliente") Usuario usuario) {
		if (administrador == null) {
			administrador = new Usuario();
			administrador.clonar(usuario);
		}
		ModelMap modelo = new ModelMap();
		Stock stock = Stock.getInstance();
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
		Set<Ingrediente> condimentos = new HashSet<Ingrediente>();
		Set<Ingrediente> listaMezclada = stock.listarIngredientes();
		dividirIngredientes(ingredientes, condimentos, listaMezclada);
		modelo.put("ingredientes", ingredientes);
		modelo.put("condimentos", condimentos);
		modelo.put("stock", stock);
		modelo.put("username", administrador.getUsername());
		modelo.put("objetoCompra", new ObjetoCompra());
		modelo.put("ingredienteEliminar", new Ingrediente());
		return new ModelAndView("gestion", modelo);
	}

	@RequestMapping(
			value = "/gestion-sitio/eliminar-ingrediente")
	public ModelAndView agregarStock(@ModelAttribute(
			value = "ingredienteEliminar") Ingrediente ingrediente) {
		Stock.getInstance().eliminarIngrediente(ingrediente);
		SQLiteDatabase.getInstance().eliminarIngrediente(ingrediente);
		return new ModelAndView("redirect:/gestion-sitio");
	}

	@RequestMapping(
			value = "/gestion-sitio/comprar")
	public ModelAndView agregarStock(@ModelAttribute(
			value = "objetoCompra") ObjetoCompra compra) {
		Stock.getInstance().agregarStock(new Ingrediente(compra.getNombre()), compra.getCantidad());
		SQLiteDatabase.getInstance().actualizarStockIngrediente(Stock.getInstance(), new Ingrediente(compra.getNombre()));
		return new ModelAndView("redirect:/gestion-sitio");
	}
	
	@RequestMapping(
			value = "/gestion-sitio/vaciar")
	public ModelAndView vaciarStock(@ModelAttribute(
			value = "objetoCompra") ObjetoCompra compra) {
		Stock.getInstance().vaciarStock(new Ingrediente(compra.getNombre()));
		SQLiteDatabase.getInstance().actualizarStockIngrediente(Stock.getInstance(), new Ingrediente(compra.getNombre()));
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
		SQLiteDatabase.getInstance().insertarIngrediente(Stock.getInstance(), condimento);
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
		SQLiteDatabase.getInstance().insertarIngrediente(Stock.getInstance(), ingrediente);
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
}

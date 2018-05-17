package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Controller
public class Operaciones {

	@RequestMapping("/Sumar/{operando1}/{operando2}")
	public ModelAndView Sumar(@PathVariable String operando1, @PathVariable String operando2) {
		ModelMap model = new ModelMap();
		
		try {
			Double resultado = Double.parseDouble(operando1) + Double.parseDouble(operando2);

			
			model.put("resultado", resultado);
			model.put("operando1", operando1);
			model.put("operando2", operando2);
			model.put("operatoria", "suma");
			return new ModelAndView("ResultadoView", model);
		}
		catch (Exception e) {
			
			return new ModelAndView("ErrorView", model);
		}
		

	}
	
	@RequestMapping("/Restar/{operando1}/{operando2}")
	public ModelAndView Restar(@PathVariable String operando1, @PathVariable String operando2) {
		ModelMap model = new ModelMap();
		

		try {
			Double resultado = Double.parseDouble(operando1) - Double.parseDouble(operando2);

			
			model.put("resultado", resultado);
			model.put("operando1", operando1);
			model.put("operando2", operando2);
			model.put("operatoria", "resta");
			
			return new ModelAndView("ResultadoView", model);
		} catch (Exception e) {
			
			return new ModelAndView("ErrorView", model);
		}

	
	}
	
	@RequestMapping("/Multiplicar/{operando1}/{operando2}")
	public ModelAndView Multiplicar(@PathVariable String operando1, @PathVariable String operando2) {
		ModelMap model = new ModelMap();
		

		try {
			Double resultado = Double.parseDouble(operando1) * Double.parseDouble(operando2);

			
			model.put("resultado", resultado);
			model.put("operando1", operando1);
			model.put("operando2", operando2);
			model.put("operatoria", "multiplicacion");

			return new ModelAndView("ResultadoView", model);
		} catch (Exception e) {
			return new ModelAndView("ErrorView", model);
		}
	}
	
	@RequestMapping("/Dividir/{operando1}/{operando2}")
	public ModelAndView Dividir(@PathVariable String operando1, @PathVariable String operando2) {
		ModelMap model = new ModelMap();
		

		try {
			Double resultado = Double.parseDouble(operando1) / Double.parseDouble(operando2);

			
			model.put("resultado", resultado);
			model.put("operando1", operando1);
			model.put("operando2", operando2);
			model.put("operatoria", "division");

			return new ModelAndView("ResultadoView", model);
		} catch (NumberFormatException e) {
			return new ModelAndView("ErrorView", model);
		}
	}
	
	@RequestMapping("/Mostrar-Tabla/{cantidad}")
	public ModelAndView mostrarTabla(@PathVariable Integer cantidad) {
		
		ModelMap modeloTabla = new ModelMap();
		Usuario usuario = new Usuario(); 
		usuario.setEmail("mail");
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		for(int i=0; i < cantidad; i++){
			
			listaUsuarios.add(usuario);
			
		}
		
		modeloTabla.put("usuarios", listaUsuarios);		
		return new ModelAndView("mostrarTabla" , modeloTabla);
	}
	
}

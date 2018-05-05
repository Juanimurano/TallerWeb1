package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Controller
public class SaludarController {

	@RequestMapping(path="/saludar")
	public ModelAndView saludar() {
		ModelMap model = new ModelMap();
		
		Usuario usuario = new Usuario(); 
		usuario.setEmail("asdasd@asdas.com");
		usuario.setId(1L);
		
		
		String mensajePantalla = "Saludo2";
		model.put("mensaje", "Hola como estas?");
		model.put("mensaje2", mensajePantalla);
		model.put("usuarioMostrar", usuario);
		
		
		return new ModelAndView("saludarView", model);
	}
		
	@RequestMapping(path="/DecirHola")
	public ModelAndView DecirHola(@RequestParam(value = "persona") String nombre) {
		ModelMap model = new ModelMap();
		
		model.put("saludo", "hola wachin");
		model.put("personaSaludada", nombre);
		
		return new ModelAndView("decirHolaView", model);
	}
	
	@RequestMapping("/SayHi/{nombre}")
	public ModelAndView SayHi(@PathVariable String nombre) {
		
		
		ModelMap model = new ModelMap();
		
		model.put("saludos", "I said hi");
		model.put("nombreHi", nombre);
		
		return new ModelAndView("SayHiView", model);
	}
}


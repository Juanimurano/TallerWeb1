package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Controller
public class RegistrarUsuario {

	@RequestMapping("/registrausuario")
	public ModelAndView registrar() {
		ModelMap model = new ModelMap();
		Usuario usuario = new Usuario();
		
		model.put("usuario", usuario);

		
		return new ModelAndView("registrar", model);
	}
	
	@RequestMapping(path = "/crear-usuario", method = RequestMethod.POST)
	public ModelAndView crearUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		model.put("usuarioMail", usuario.getEmail());
		model.put("usuarioPassword", usuario.getPassword());
		model.put("usuarioRol", usuario.getRol());
		
		return new ModelAndView("registrado", model);
	}
	
}

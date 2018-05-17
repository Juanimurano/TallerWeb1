package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListaController {
	@RequestMapping("/lista")
	public ModelAndView lista() {
		ModelMap model = new ModelMap();
		
		List<String> listaArray = new ArrayList<String>();
		listaArray.add("Arial");
		listaArray.add("Times New Roman");
		listaArray.add("Verdana");
		listaArray.add("Wingdings");
		listaArray.add("Comic Sans MS");
		listaArray.add("Trebuchet MS");
		listaArray.add("Tahoma");
		
		model.put("listaArray", listaArray);
		
		return new ModelAndView("ListaView", model);
	}
}

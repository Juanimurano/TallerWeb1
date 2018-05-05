package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Operaciones {

	@RequestMapping("/Sumar/{operando1}/{operando2}")
	public ModelAndView Sumar(@PathVariable String operando1, @PathVariable String operando2) {
		ModelMap model = new ModelMap();
		

		Double resultado = Double.parseDouble(operando1) + Double.parseDouble(operando2);

		
		model.put("resultado", resultado);
		model.put("operando1", operando1);
		model.put("operando2", operando2);
		model.put("operatoria", "suma");
		

		//	return new ModelAndView("ErrorView", model);

			return new ModelAndView("ResultadoView", model);
	}
	
	@RequestMapping("/Restar/{operando1}/{operando2}")
	public ModelAndView Restar(@PathVariable String operando1, @PathVariable String operando2) {
		ModelMap model = new ModelMap();
		

		Double resultado = Double.parseDouble(operando1) - Double.parseDouble(operando2);

		
		model.put("resultado", resultado);
		model.put("operando1", operando1);
		model.put("operando2", operando2);
		model.put("operatoria", "resta");

		//	return new ModelAndView("ErrorView", model);

			return new ModelAndView("ResultadoView", model);
	}
	
	@RequestMapping("/Multiplicar/{operando1}/{operando2}")
	public ModelAndView Multiplicar(@PathVariable String operando1, @PathVariable String operando2) {
		ModelMap model = new ModelMap();
		

		Double resultado = Double.parseDouble(operando1) * Double.parseDouble(operando2);

		
		model.put("resultado", resultado);
		model.put("operando1", operando1);
		model.put("operando2", operando2);
		model.put("operatoria", "multiplicacion");

		//	return new ModelAndView("ErrorView", model);

			return new ModelAndView("ResultadoView", model);
	}
	
	@RequestMapping("/Dividir/{operando1}/{operando2}")
	public ModelAndView Dividir(@PathVariable String operando1, @PathVariable String operando2) {
		ModelMap model = new ModelMap();
		

		Double resultado = Double.parseDouble(operando1) / Double.parseDouble(operando2);

		
		model.put("resultado", resultado);
		model.put("operando1", operando1);
		model.put("operando2", operando2);
		model.put("operatoria", "division");

		//	return new ModelAndView("ErrorView", model);

			return new ModelAndView("ResultadoView", model);
	}
	
}

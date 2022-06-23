package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Anotacion;
import pe.edu.upc.spring.service.IAnotacionService;

@Controller
@RequestMapping("/anotacion")
public class AnotacionController {

	
	@Autowired
	private IAnotacionService dService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoDocente(Map<String, Object> model) {
		model.put("listaAnotacion", dService.listar());
		return "listAnotacion"; //"listAnotacion" es una pagina del frontend
	}
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("anotacion", new Anotacion());
		return "anotacion"; //"anotacion" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Anotacion objAnotacion, BindingResult binRes, Model model) throws java.text.ParseException{
		if(binRes.hasErrors())
		{
			return "anotacion";
		}
		else {
			boolean flag = dService.grabar(objAnotacion);
			if(flag)
				return "redirect:/anotacion/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/anotacion/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws java.text.ParseException{
		Optional<Anotacion> objAnotacion = dService.listarId(id);
		if(objAnotacion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/anotacion/listar";
		}
		else {
			if(objAnotacion.isPresent())
				objAnotacion.ifPresent(o -> model.addAttribute("anotacion",o));
			
			return "anotacion";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaAnotacion", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaAnotacion", dService.listar());
		}
		return "listAnotacion";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAnotacion", dService.listar());
		return "listAnotacion";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Anotacion anotacion) throws java.text.ParseException 
	{
		dService.listarId(anotacion.getIdAnotacion());
		return "listAnotacion";
	}
}

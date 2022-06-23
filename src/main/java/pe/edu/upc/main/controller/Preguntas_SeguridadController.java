package pe.edu.upc.main.controller;

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

import pe.edu.upc.main.model.Preguntas_Seguridad;
import pe.edu.upc.main.service.IPreguntas_SeguridadService;

@Controller
@RequestMapping("/preguntas_seguridad")
public class Preguntas_SeguridadController {

	
	@Autowired
	private IPreguntas_SeguridadService pService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoPreguntas_Seguridad(Map<String, Object> model) {
		model.put("listaPreguntas_Seguridad", pService.listar());
		return "listPreguntas_Seguridad"; //"listPreguntas_Seguridad" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("preguntas_seguridad", new Preguntas_Seguridad());
		return "preguntas_seguridad"; //"preguntas_seguridad" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Preguntas_Seguridad objPreguntas_Seguridad, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			return "preguntas_seguridad";
		}
		else {
			boolean flag = pService.grabar(objPreguntas_Seguridad);
			if(flag)
				return "redirect:/preguntas_seguridad/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/preguntas_seguridad/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Preguntas_Seguridad> objPreguntas_Seguridad = pService.listarId(id);
		if(objPreguntas_Seguridad == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/preguntas_seguridad/listar";
		}
		else {
			if(objPreguntas_Seguridad.isPresent())
				objPreguntas_Seguridad.ifPresent(o -> model.addAttribute("preguntas_seguridad",o));
			
			return "preguntas_seguridad";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPreguntas_Seguridad", pService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaPreguntas_Seguridad", pService.listar());
		}
		return "listDocente";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPreguntas_Seguridad", pService.listar());
		return "listPreguntas_Seguridad";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Preguntas_Seguridad preguntas_seguridad) throws java.text.ParseException 
	{
		pService.listarId(preguntas_seguridad.getIdPreguntas_Seguridad());
		return "listPreguntas_Seguridad";
	}
}

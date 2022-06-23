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

import pe.edu.upc.main.model.Curso;
import pe.edu.upc.main.service.ICursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

	
	@Autowired
	private ICursoService dService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoDocente(Map<String, Object> model) {
		model.put("listaCurso", dService.listar());
		return "listCurso"; //"listAnotacion" es una pagina del frontend
	}
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("Curso", new Curso());
		return "curso"; //"anotacion" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Curso objAnotacion, BindingResult binRes, Model model) throws java.text.ParseException{
		if(binRes.hasErrors())
		{
			return "curso";
		}
		else {
			boolean flag = dService.grabar(objAnotacion);
			if(flag)
				return "redirect:/curso/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/curso/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws java.text.ParseException{
		Optional<Curso> objAnotacion = dService.listarId(id);
		if(objAnotacion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/curso/listar";
		}
		else {
			if(objAnotacion.isPresent())
				objAnotacion.ifPresent(o -> model.addAttribute("Curso",o));
			
			return "curso";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaCurso", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaCurso", dService.listar());
		}
		return "listCurso";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCurso", dService.listar());
		return "listCurso";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Curso curso) throws java.text.ParseException 
	{
		dService.listarId(curso.getIdCurso());
		return "listCurso";
	}
}


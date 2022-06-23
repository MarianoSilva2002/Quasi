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

import pe.edu.upc.spring.model.Alumno_cursos;
import pe.edu.upc.spring.service.IAlumno_cursosService;

@Controller
@RequestMapping("/alumno_cursos")
public class Alumno_CursosController {

	
	@Autowired
	private IAlumno_cursosService dService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoAlumno_cursos(Map<String, Object> model) {
		model.put("listaAlumno_cursos", dService.listar());
		return "listAlumno_cursos"; //
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("alumno_cursos", new Alumno_cursos());
		return "alumno_cursos"; //
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Alumno_cursos objAlumno_cursos, BindingResult binRes, Model model) throws java.text.ParseException{
		if(binRes.hasErrors())
		{
			return "alumno_cursos";
		}
		else {
			boolean flag = dService.grabar(objAlumno_cursos);
			if(flag)
				return "redirect:/alumno_cursos/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/alumno_cursos/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws java.text.ParseException{
		Optional<Alumno_cursos> objAlumno_cursos = dService.listarId(id);
		if(objAlumno_cursos == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/alumno_cursos/listar";
		}
		else {
			if(objAlumno_cursos.isPresent())
				objAlumno_cursos.ifPresent(o -> model.addAttribute("docente",o));
			
			return "alumno_cursos";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaAlumno_cursos", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaAlumno_cursos", dService.listar());
		}
		return "listAlumno_cursos";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAlumno_cursos", dService.listar());
		return "listAlumno_cursos";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Alumno_cursos alumno_cursos) throws java.text.ParseException 
	{
		dService.listarId(alumno_cursos.getIdAlumno_curso());
		return "listAlumno_cursos";
	}
}


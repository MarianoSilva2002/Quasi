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

import pe.edu.upc.main.service.IAlumnoService;
import pe.edu.upc.main.model.Alumno;
//import pe.edu.upc.spring.service.IAlumno_cursosService;
//import pe.edu.upc.spring.service.IPreguntas_SeguridadService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	
	@Autowired
	private IAlumnoService dService;
	
	//@Autowired
	//private IAlumno_cursosService cService;
	
	//@Autowired
	//private IPreguntas_SeguridadService pService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoAlumno(Map<String, Object> model) {
		model.put("listaAlumno", dService.listar());
		return "listAlumno"; //"listAlumno" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "alumno"; //"alumno" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Alumno objAlumno, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			return "alumno";
		}
		else {
			boolean flag = dService.grabar(objAlumno);
			if(flag)
				return "redirect:/alumno/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/alumno/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Alumno> objAlumno = dService.listarId(id);
		if(objAlumno == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/alumno/listar";
		}
		else {
			if(objAlumno.isPresent())
				objAlumno.ifPresent(o -> model.addAttribute("alumno",o));
			
			return "alumno";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaAlumno", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaAlumno", dService.listar());
		}
		return "listAlumno";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAlumno", dService.listar());
		return "listAlumno";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Alumno alumno) throws ParseException 
	{
		dService.listarId(alumno.getIdAlumno());
		return "listAlumno";
	}
}
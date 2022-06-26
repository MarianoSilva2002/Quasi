package pe.edu.upc.main.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.main.model.Alumno_cursos;
import pe.edu.upc.main.model.Seccion;
import pe.edu.upc.main.service.IAlumno_cursosService;
import pe.edu.upc.main.service.ISeccionService;

@Controller
@RequestMapping("/seccion")
public class SeccionController {

	
	@Autowired
	private ISeccionService dService;

	@Autowired
	private IAlumno_cursosService acService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoSeccion(Map<String, Object> model) {
		model.put("listaSeccion", dService.listar());
		return "listSeccion"; //"listSeccion" es una pagina del frontend
	}
	
	@RequestMapping("/crear")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("seccion", new Seccion());
		return "crearsecciondocente";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Seccion objSeccion, BindingResult binRes, Model model) throws ParseException{
		objSeccion.setIdcurso(CursoController.CursoCActiva);
		if(binRes.hasErrors())
		{
			return "crearsecciondocente";
		}
		else {
			boolean flag = dService.grabar(objSeccion);
			if(flag)
				return "redirect:/seccion/crear";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/seccion/crear";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Seccion> objSeccion = dService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/seccion/listar";
		}
		else {
			if(objSeccion.isPresent())
				objSeccion.ifPresent(o -> model.addAttribute("seccion",o));
			
			return "seccion";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaSeccion", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaSeccion", dService.listar());
		}
		return "listSeccion";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSeccion", dService.listar());
		return "listSeccion";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Seccion seccion) throws ParseException 
	{
		dService.listarId(seccion.getIdSeccion());
		return "listSeccion";
	}

	@RequestMapping("/entrar/{id}")
	public String entrardocente(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Seccion> objSeccion = dService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/docente/cursos";
		}
		else {
			if(objSeccion.isPresent())
				objSeccion.ifPresent(o -> model.addAttribute("seccion",o));
			model.addAttribute("seccion",objSeccion.get());
			model.addAttribute("curso",objSeccion.get().getIdcurso());
			return "infogeneralcursodocente";
		}
	}

	@RequestMapping("/entrar2/{id}")
	public String entraralumno(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Seccion> objSeccion = dService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/alumno/cursos";
		}
		else {
			if(objSeccion.isPresent())
				objSeccion.ifPresent(o -> model.addAttribute("seccion",o));
			model.addAttribute("seccion",objSeccion.get());
			model.addAttribute("curso",objSeccion.get().getIdcurso());
			return "infogeneralcursoalumno";
		}
	}

	@RequestMapping("/registraralumno/{id}")
	public String registroalumnocurso(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Seccion> objSeccion = dService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/alumno/cursos";
		}
		else {
			Alumno_cursos ac = new Alumno_cursos();
			ac.setSeccion(objSeccion.get());
			ac.setAlumno(AlumnoController.AlumnoCActiva);
			boolean flag = acService.grabar(ac);
			if(flag)
				return "redirect:/alumno/nuevoscursos";
			else{
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/alumno/nuevoscursos";
			}
		}
	}
}

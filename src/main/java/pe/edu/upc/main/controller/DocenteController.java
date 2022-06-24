package pe.edu.upc.main.controller;

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

import pe.edu.upc.main.model.Alumno;
import pe.edu.upc.main.model.Docente;
import pe.edu.upc.main.model.Preguntas_Seguridad;
import pe.edu.upc.main.service.IDocenteService;
import pe.edu.upc.main.service.IPreguntas_SeguridadService;

@Controller
@RequestMapping("/docente")
public class DocenteController {

	@Autowired
	private IPreguntas_SeguridadService psService;
	@Autowired
	private IDocenteService dService;

	public static Docente DocenteCActiva;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "menudocente"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoDocente(Map<String, Object> model) {
		model.put("listaDocente", dService.listar());
		return "listDocente"; //"listDocente" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("listaPreguntas", psService.listar());

		model.addAttribute("pseguridad", new Preguntas_Seguridad());
		model.addAttribute("docente", new Docente());
		return "registroDocente";
	}

	@RequestMapping("/irInicioSesion")
	public String irPaginaInicioSesion(Model model) {
		model.addAttribute("docente", new Docente());
		return "iniciosesionDocente";
	}

	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(@ModelAttribute Docente objDocente, BindingResult binRes, Model model) throws ParseException{
		List<Docente> FiltroDocente = dService.buscarContrasena(objDocente.getCorreo(), objDocente.getContrasena());
		if(FiltroDocente.isEmpty())
		{
			return "redirect:/docente/irInicioSesion";
		}
		else {
			DocenteCActiva =FiltroDocente.get(0);
			return "redirect:/docente/bienvenido";
		}

	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Docente objDocente, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			return "docente";
		}
		else {
			boolean flag = dService.grabar(objDocente);
			if(flag)
				return "redirect:/docente/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/docente/irRegistrar";
			}
		}
	}

	@RequestMapping("/editarperfil")
	public String irPaginaEditarPerfil(Model model) {
		model.addAttribute("listaPreguntas", psService.listar());

		model.addAttribute("pseguridad", new Preguntas_Seguridad());
		model.addAttribute("docente", DocenteCActiva);
		return "editarperfildocente";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Docente> objDocente = dService.listarId(id);
		if(objDocente == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/docente/listar";
		}
		else {
			if(objDocente.isPresent())
				objDocente.ifPresent(o -> model.addAttribute("docente",o));
			
			return "docente";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaDocente", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaDocente", dService.listar());
		}
		return "listDocente";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDocente", dService.listar());
		return "listDocente";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Docente docente) throws java.text.ParseException 
	{
		dService.listarId(docente.getIdDocente());
		return "listDocente";
	}
}

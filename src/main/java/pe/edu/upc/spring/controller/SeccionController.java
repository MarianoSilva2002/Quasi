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

import pe.edu.upc.spring.model.Seccion;
import pe.edu.upc.spring.service.ISeccionService;

@Controller
@RequestMapping("/seccion")
public class SeccionController {

	
	@Autowired
	private ISeccionService dService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoSeccion(Map<String, Object> model) {
		model.put("listaSeccion", dService.listar());
		return "listSeccion"; //"listDocente" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("seccion", new Seccion());
		return "seccion"; //"Seccion" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Seccion objDocente, BindingResult binRes, Model model) throws java.text.ParseException{
		if(binRes.hasErrors())
		{
			return "seccion";
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
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws java.text.ParseException{
		Optional<Seccion> objDocente = dService.listarId(id);
		if(objDocente == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/seccion/listar";
		}
		else {
			if(objDocente.isPresent())
				objDocente.ifPresent(o -> model.addAttribute("docente",o));
			
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
	public String listarId(Map<String, Object> model, @ModelAttribute Seccion seccion) throws java.text.ParseException 
	{
		dService.listarId(seccion.getIdSeccion());
		return "listSeccion";
	}
}

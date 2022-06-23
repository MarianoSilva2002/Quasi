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

import pe.edu.upc.spring.model.Consulta;
import pe.edu.upc.spring.service.IConsultaService;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

	
	@Autowired
	private IConsultaService dService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoConsulta(Map<String, Object> model) {
		model.put("listaConsulta", dService.listar());
		return "listConsulta"; //"listDocente" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("consulta", new Consulta());
		return "consulta"; //"Consulta" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Consulta objConsulta, BindingResult binRes, Model model) throws java.text.ParseException{
		if(binRes.hasErrors())
		{
			return "consulta";
		}
		else {
			boolean flag = dService.grabar(objConsulta);
			if(flag)
				return "redirect:/consulta/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/consulta/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws java.text.ParseException{
		Optional<Consulta> objDocente = dService.listarId(id);
		if(objDocente == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/consulta/listar";
		}
		else {
			if(objDocente.isPresent())
				objDocente.ifPresent(o -> model.addAttribute("consulta",o));
			
			return "consulta";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaConsulta", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaConsulta", dService.listar());
		}
		return "listConsulta";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaConsulta", dService.listar());
		return "listeConsulta";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Consulta Consulta) throws java.text.ParseException 
	{
		dService.listarId(Consulta.getIdConsulta());
		return "listConsulta";
	}
}

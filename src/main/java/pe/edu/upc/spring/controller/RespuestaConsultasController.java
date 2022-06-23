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

import pe.edu.upc.spring.model.RespuestaConsultas;
import pe.edu.upc.spring.service.IRespuestaConsultasService;

@Controller
@RequestMapping("/respuestaconsultas")
public class RespuestaConsultasController {

	
	@Autowired
	private IRespuestaConsultasService dService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoRespuestaConsultas(Map<String, Object> model) {
		model.put("listaRespuestaConsultas", dService.listar());
		return "listRespuestaConsultas"; //"listRespuestaConsultas" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("respuestaconsultas", new RespuestaConsultas());
		return "respuestaconsultas"; //"RespuestaConsultas" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute RespuestaConsultas objRespuestaConsultas, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			return "respuestaconsultas";
		}
		else {
			boolean flag = dService.grabar(objRespuestaConsultas);
			if(flag)
				return "redirect:/respuestaconsultas/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/respuestaconsultas/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<RespuestaConsultas> objRespuestaConsultas = dService.listarId(id);
		if(objRespuestaConsultas == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/respuestaconsultas/listar";
		}
		else {
			if(objRespuestaConsultas.isPresent())
				objRespuestaConsultas.ifPresent(o -> model.addAttribute("respuestaconsultas",o));
			
			return "respuestaconsultas";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaRespuestaConsultas", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaRespuestaConsultas", dService.listar());
		}
		return "listDocente";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaRespuestaConsultas", dService.listar());
		return "listRespuestaConsultas";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute RespuestaConsultas respuestaconsultas) throws ParseException 
	{
		dService.listarId(respuestaconsultas.getIdRespuestaConsulta());
		return "listRespuestaConsultas";
	}
}

package pe.edu.upc.main.controller;

import java.util.Date;
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

import pe.edu.upc.main.model.Consulta;
import pe.edu.upc.main.model.Seccion;
import pe.edu.upc.main.service.IConsultaService;
import pe.edu.upc.main.service.ISeccionService;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

	
	@Autowired
	private IConsultaService dService;

	@Autowired
	private ISeccionService sService;
	
	
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
		Date fechaactual = new Date();
		objConsulta.setFechaConsulta(fechaactual);
		objConsulta.setHoraConsulta(fechaactual);
		objConsulta.setIdAlumno_curso(Alumno_CursosController.CActivaAlumnoCurso);
		if(binRes.hasErrors())
		{
			return "redirect:/seccion/agregarconsulta/" + Alumno_CursosController.CActivaAlumnoCurso.getSeccion().getIdSeccion();
		}
		else {
			boolean flag = dService.grabar(objConsulta);
			if(flag)
				return "redirect:/seccion/consulta1/" + Alumno_CursosController.CActivaAlumnoCurso.getSeccion().getIdSeccion();
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/seccion/agregarconsulta/" + Alumno_CursosController.CActivaAlumnoCurso.getSeccion().getIdSeccion();
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

	@RequestMapping("/regresar/{id}")
	public String regresar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws java.text.ParseException{
		Optional<Seccion> objSeccion = sService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/consulta/listar";
		}
		else {
			if(objSeccion.isPresent())
				objSeccion.ifPresent(o -> model.addAttribute("seccion",o));


			return "redirect:/seccion/consulta1/" + objSeccion.get().getIdSeccion();
		}
	}
}

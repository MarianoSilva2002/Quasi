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

import pe.edu.upc.main.model.*;
import pe.edu.upc.main.service.*;

@Controller
@RequestMapping("/seccion")
public class SeccionController {

	
	@Autowired
	private ISeccionService dService;

	@Autowired
	private IAlumno_cursosService acService;

	@Autowired
	private IAnuncioService anService;
	@Autowired
	private IAnotacionService anotService;
	@Autowired
	private IConsultaService coService;

	@Autowired
	private IRespuestaConsultasService rcService;
	public static Seccion CActivaSeccion;
	
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
		model.addAttribute("cantsecciones", dService.seccionporCurso(CursoController.CursoCActiva.getIdCurso()).size());
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
			CActivaSeccion = objSeccion.get();
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
			Alumno_cursos objAlumnoCurso = acService.seccionesporAlumnoySeccion(AlumnoController.AlumnoCActiva.getIdAlumno(),objSeccion.get().getIdSeccion()).get(0);
			if(objAlumnoCurso!= null)
			{
				Alumno_CursosController.CActivaAlumnoCurso = objAlumnoCurso;
				return "infogeneralcursoalumno";
			}
			else{
				objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
				return "redirect:/alumno/cursos";
			}

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
	@RequestMapping("/infogeneral1/{id}")
	public String infogeneralAlumno(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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

	@RequestMapping("/anuncios1/{id}")
	public String anunciosAlumno(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Seccion> objSeccion = dService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/alumno/cursos";
		}
		else {
			if(objSeccion.isPresent())
				objSeccion.ifPresent(o -> model.addAttribute("seccion",o));
			List<Anuncio> listaAnuncios = anService.anunciosporSeccion(objSeccion.get().getIdSeccion());

			model.addAttribute("seccion",objSeccion.get());
			model.addAttribute("curso",objSeccion.get().getIdcurso());
			model.addAttribute("listaAnuncios",listaAnuncios);
			if(listaAnuncios != null)
				return "anunciosalumno";
			else
				return "anunciosalumno2";
		}
	}

	@RequestMapping("/calificaciones1/{id}")
	public String calificacionesAlumno(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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
			return "calificacionesalumno";
		}
	}

	@RequestMapping("/consultas1/{id}")
	public String consultasAlumno(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Seccion> objSeccion = dService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/alumno/cursos";
		}
		else {
			if(objSeccion.isPresent())
				objSeccion.ifPresent(o -> model.addAttribute("seccion",o));

			List<Consulta> listaConsultas = coService.consultaporAlumno(AlumnoController.AlumnoCActiva.getIdAlumno());

			model.addAttribute("seccion",objSeccion.get());
			model.addAttribute("curso",objSeccion.get().getIdcurso());
			model.addAttribute("listarespuesta",rcService.listar());
			if(listaConsultas.isEmpty())
				return "consultasalumno2";
			else
			{
				model.addAttribute("listaConsultas",listaConsultas);
				return "consultasalumno";
			}
		}
	}

	@RequestMapping("/agregarconsulta/{id}")
	public String agregarConsulta(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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
			model.addAttribute("consulta", new Consulta());
			return "consultaregistro";
		}
	}
	@RequestMapping("/anotaciones/{id}")
	public String anotacionesAlumno(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Seccion> objSeccion = dService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/alumno/cursos";
		}
		else {
			if(objSeccion.isPresent())
				objSeccion.ifPresent(o -> model.addAttribute("seccion",o));

			List<Anotacion> listaAnotaciones = anotService.anotacionporAlumno(AlumnoController.AlumnoCActiva.getIdAlumno());

			model.addAttribute("seccion",objSeccion.get());
			model.addAttribute("curso",objSeccion.get().getIdcurso());
			if(listaAnotaciones.isEmpty())
				return "anotacionesalumno2";
			else
			{
				model.addAttribute("listaAnotaciones",listaAnotaciones);
				return "anotacionesalumno";
			}
		}
	}

	@RequestMapping("/agregaranotacion/{id}")
	public String agregarAnotacion(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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
			model.addAttribute("anotacion", new Anotacion());
			return "anotacionregistro";
		}
	}

	@RequestMapping("/unidad1/{id}")
	public String unidadAlumno(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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
			return "semanasalumno";
		}
	}
	@RequestMapping("/semana1/{id}")
	public String semanaAlumno(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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
			return "semanaalumno";
		}
	}


	@RequestMapping("/infogeneral2/{id}")
	public String infogeneralDocente(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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

	@RequestMapping("/anuncios2/{id}")
	public String anunciosDocente(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Seccion> objSeccion = dService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/docente/cursos";
		}
		else {
			if(objSeccion.isPresent())
				objSeccion.ifPresent(o -> model.addAttribute("seccion",o));
			List<Anuncio> listaAnuncios = anService.anunciosporSeccion(objSeccion.get().getIdSeccion());

			model.addAttribute("seccion",objSeccion.get());
			model.addAttribute("curso",objSeccion.get().getIdcurso());
			model.addAttribute("listaAnuncios",listaAnuncios);
			model.addAttribute("anuncio",new Anuncio());
			return "anunciosdocente";
		}
	}

	@RequestMapping("/calificaciones2/{id}")
	public String calificacionesDocente(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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
			return "calificacionesdocente";
		}
	}
	@RequestMapping("/tarea/{id}")
	public String tareasDocente(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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
			return "calificacionesdocente2";
		}
	}

	@RequestMapping("/consultas2/{id}")
	public String consultasDocente(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Seccion> objSeccion = dService.listarId(id);
		if(objSeccion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/docente/cursos";
		}
		else {
			if(objSeccion.isPresent())
				objSeccion.ifPresent(o -> model.addAttribute("seccion",o));

			List<Consulta> listaConsultas = coService.consultaporSeccion(CActivaSeccion.getIdSeccion());

			model.addAttribute("seccion",objSeccion.get());
			model.addAttribute("curso",objSeccion.get().getIdcurso());
			model.addAttribute("listaConsultas",listaConsultas);
			model.addAttribute("respuestaConsulta",new RespuestaConsultas());
			return "consultasdocente";
		}
	}


	@RequestMapping("/unidad2/{id}")
	public String unidadDocente(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
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
			return "semanasdocente";
		}
	}
}

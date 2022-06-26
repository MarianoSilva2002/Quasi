package pe.edu.upc.main.controller;

import java.util.ArrayList;
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

import pe.edu.upc.main.model.*;
import pe.edu.upc.main.repository.IAlumno_cursosRepository;
import pe.edu.upc.main.service.*;
//import pe.edu.upc.spring.service.IAlumno_cursosService;
import pe.edu.upc.main.service.IPreguntas_SeguridadService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private IPreguntas_SeguridadService psService;
	@Autowired
	private IAlumnoService dService;

	@Autowired
	private IAlumno_cursosService acService;

	@Autowired
	private ISeccionService sService;

	@Autowired
	private ICursoService cService;

	public static Alumno AlumnoCActiva;
	//@Autowired
	//private IAlumno_cursosService cService;
	
	//@Autowired
	//private IPreguntas_SeguridadService pService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "menualumno"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoAlumno(Map<String, Object> model) {
		model.put("listaAlumno", dService.listar());
		return "listAlumno"; //"listAlumno" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("listaPreguntas", psService.listar());

		model.addAttribute("pseguridad", new Preguntas_Seguridad());
		model.addAttribute("alumno", new Alumno());
		return "registroAlumno";
	}

	@RequestMapping("/irInicioSesion")
	public String irPaginaInicioSesion(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "iniciosesionAlumno";
	}

	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(@ModelAttribute Alumno objAlumno, BindingResult binRes, Model model) throws ParseException{
		List<Alumno> FiltroAlumno = dService.buscarContrasena(objAlumno.getCorreo(), objAlumno.getContrasena());
		if(FiltroAlumno.isEmpty())
		{
			return "redirect:/alumno/irInicioSesion";
		}
		else {
			AlumnoCActiva =FiltroAlumno.get(0);
			return "redirect:/alumno/bienvenido";
		}

	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Alumno objAlumno, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaPreguntas", psService.listar());
			return "alumno";
		}
		else {
			boolean flag = dService.grabar(objAlumno);
			if(flag)
				return "redirect:/login/";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/alumno/irRegistrar";
			}
		}
	}
	@RequestMapping("/editarperfil")
	public String irPaginaEditarPerfil(Model model) {
		model.addAttribute("listaPreguntas", psService.listar());

		model.addAttribute("pseguridad", new Preguntas_Seguridad());
		model.addAttribute("alumno", AlumnoCActiva);
		return "editarperfilalumno";
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

	@RequestMapping("/recuperarcontrasena")
	public String irPaginarecuperarcontrasena(Model model) {
		model.addAttribute("listaPreguntas", psService.listar());
		model.addAttribute("alumno", new Alumno());
		return "recuperarContrasenap1a";
	}

	@RequestMapping("/recuperarcontrasena2")
	public String reccontra1(@ModelAttribute Alumno objAlumno, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaPreguntas", psService.listar());
			return "alumno";
		}
		else {
			Alumno alumno = dService.buscarAlumnoporCorreo(objAlumno.getCorreo()).get(0);
			if(alumno.getIdPregunta().getIdPreguntas_Seguridad() == objAlumno.getIdPregunta().getIdPreguntas_Seguridad() && alumno.getRespuestaSeguridad().equals(objAlumno.getRespuestaSeguridad())) {
				model.addAttribute("listaPreguntas", psService.listar());
				alumno.setContrasena("");
				model.addAttribute("alumno", alumno);
				return "recuperarContrasenap2a";
			}else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/alumno/irRegistrar";
			}
		}
	}
	@RequestMapping("/recuperarcontrasena3")
	public String reccontra2(@ModelAttribute Alumno objAlumno, BindingResult binRes, Model model) throws ParseException{

		if(binRes.hasErrors())
		{
			model.addAttribute("listaPreguntas", psService.listar());
			return "alumno";
		}
		else {
			boolean flag = dService.grabar(objAlumno);
			if(flag)
				return "redirect:/alumno/irInicioSesion";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/alumno/recuperarcontrasena2";
			}
		}
	}

	@RequestMapping("/cambiocontrasena")
	public String irPaginacambiocontrasena(Model model) {
		model.addAttribute("listaPreguntas", psService.listar());
		model.addAttribute("nuevacontra", new String());
		model.addAttribute("alumno", new Alumno());
		return "cambiarContrasenaAlumno";
	}
	@RequestMapping("/cambiocontrasena2")
	public String camcontra1(@ModelAttribute Alumno objAlumno, @ModelAttribute String objNuevaContra, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaPreguntas", psService.listar());
			return "alumno";
		}
		else {
			Alumno alumno = dService.buscarContrasena(objAlumno.getCorreo(),objAlumno.getContrasena()).get(0);
			System.out.println(alumno.getCorreo());
			System.out.println(objAlumno.getCorreo());
			System.out.println(alumno.getContrasena());
			System.out.println(objAlumno.getContrasena());
			if(alumno.getCorreo().equals(objAlumno.getCorreo()))
			{
				System.out.println(objNuevaContra);
				objAlumno.setContrasena(objNuevaContra);
				System.out.println(objAlumno.getContrasena());
				boolean flag = dService.grabar(objAlumno);
				if(flag)
					return "redirect:/alumno/irInicioSesion";
				else {
					model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
					return "redirect:/alumno/cambiocontrasena";
				}
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/alumno/cambiocontrasena";
			}
		}
	}

	@RequestMapping("/cursos")
	public String irCursosAlumno(Model model) {
		List<Alumno_cursos> listaAlumnoCursos = acService.seccionesporAlumno(AlumnoCActiva.getIdAlumno());
		List<Seccion> listaSecciones = new ArrayList<Seccion>();
		for (Alumno_cursos ac: listaAlumnoCursos) {
			listaSecciones.add(ac.getSeccion());
		}
		model.addAttribute("listaSecciones", listaSecciones);
		return "cursosalumno";
	}

	@RequestMapping("/nuevoscursos")
	public String irNuevosCursosAlumno(Model model) {
		List<Alumno_cursos> listaAlumnoCursos = acService.seccionesporAlumno(AlumnoCActiva.getIdAlumno());
		List<Curso> listaCursos = cService.listar();
		for(Alumno_cursos ac: listaAlumnoCursos){
			listaCursos.remove(ac.getSeccion().getIdcurso());
		}
		List<Seccion> listaSecciones = new ArrayList<Seccion>();
		for (Curso c:
				listaCursos) {
			List<Seccion> secciones =sService.seccionporCurso(c.getIdCurso());
			for (Seccion s:
					secciones) {
				listaSecciones.add(s);
			}
		}
		model.addAttribute("listaSecciones", listaSecciones);
		return "nuevoscursosalumno";
	}
}

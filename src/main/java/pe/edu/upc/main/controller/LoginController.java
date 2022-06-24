package pe.edu.upc.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.main.model.Docente;
import pe.edu.upc.main.model.Alumno;
import pe.edu.upc.main.model.Preguntas_Seguridad;
import pe.edu.upc.main.service.IDocenteService;
import pe.edu.upc.main.service.IAlumnoService;
import pe.edu.upc.main.service.IPreguntas_SeguridadService;

@RequestMapping("/login")
@Controller
public class LoginController {
	
	@Autowired
	private IDocenteService dService;
	
	@Autowired
	private IPreguntas_SeguridadService psService;
	
	@Autowired
	private IAlumnoService aService;
	
	@GetMapping("/")
	public String login() {
		return "landingpage";
	}
	
	@RequestMapping("/registro")
	public String irPaginaRegistrarComo() {
		return "registrarsecomo";
	}
	
	@RequestMapping("/iniciarSesion")
	public String irPaginaIniciarSesion() {
		return "iniciarsesioncomo";
	}
		
	@RequestMapping("/irRegistrarAlumno")
	public String irPaginaRegistrarAlumno(Model model) {
		model.addAttribute("listaPreguntas", psService.listar());
		
		model.addAttribute("pseguridad", new Preguntas_Seguridad());
		model.addAttribute("alumno", new Alumno());
		return "registroAlumno";
	}
	
	@RequestMapping("/irRegistrarDocente")
	public String irPaginaRegistrarDocente(Model model) {
		model.addAttribute("listaPreguntas", psService.listar());

		model.addAttribute("pseguridad", new Preguntas_Seguridad());
		model.addAttribute("docente", new Docente());
		return "registroDocente";
	}
}

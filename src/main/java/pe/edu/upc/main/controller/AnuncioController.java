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

import com.sun.el.parser.ParseException;

import pe.edu.upc.main.model.Anuncio;
import pe.edu.upc.main.model.Seccion;
import pe.edu.upc.main.service.IAnuncioService;

@Controller
@RequestMapping("/anuncio")
public class AnuncioController {

	
	@Autowired
	private IAnuncioService dService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoAnuncio(Map<String, Object> model) {
		model.put("listaAnuncio", dService.listar());
		return "listAnuncio"; //"listAnuncio" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("anuncio", new Anuncio());
		return "anuncio"; //"Anuncio" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Anuncio objAnuncio, BindingResult binRes, Model model) throws ParseException{
		Date fechaactual = new Date();
		objAnuncio.setFechaanuncio(fechaactual);
		objAnuncio.setHoraanuncio(fechaactual);
		objAnuncio.setIdseccion(SeccionController.CActivaSeccion);
		if(binRes.hasErrors())
		{
			return "redirect:/seccion/anuncios2/" + SeccionController.CActivaSeccion.getIdSeccion();
		}
		else {
			boolean flag = dService.grabar(objAnuncio);
			if(flag)
				return "redirect:/seccion/anuncios2/" + SeccionController.CActivaSeccion.getIdSeccion();
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/seccion/anuncios2/" + SeccionController.CActivaSeccion.getIdSeccion();
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Anuncio> objAnuncio = dService.listarId(id);
		if(objAnuncio == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/anuncio/listar";
		}
		else {
			if(objAnuncio.isPresent())
				objAnuncio.ifPresent(o -> model.addAttribute("anuncio",o));
			
			return "anuncio";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaAnuncio", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaAnuncio", dService.listar());
		}
		return "listAnuncio";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAnuncio", dService.listar());
		return "listAnuncio";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Anuncio anuncio) throws ParseException 
	{
		dService.listarId(anuncio.getIdAnuncio());
		return "listAnuncio";
	}
}

package ${groupId}.core.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ${groupId}.core.form.Person;
import ${groupId}.core.service.PersonService;

@Controller
public class SystemController {	
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value={"/login","/logout"})
	public String loginAndLogout( HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session){
		session.invalidate();
		session = request.getSession();
		return "login";
	}
	
	@RequestMapping(value={"/system/home"})
	public String home( HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session,
			Map<String, Object> map){
		Person personSession = (Person) session.getAttribute("personSession");
		if(personSession == null){
			return "redirect:/login";
		}	
		map.put("commonName", personSession.getCommonName());
		return "system_home";
	}
	
	@RequestMapping(value={"/system/updatePerson"}, method = RequestMethod.POST)
	public @ResponseBody void updatePerson(HttpServletResponse response, 
			HttpSession session,
			@ModelAttribute("person")Person person,
			Map<String, Object> map) {
		PrintWriter out;
		try {
			out = response.getWriter();
			Person personSession = (Person) session.getAttribute("personSession");
			if(personSession == null){
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				out.write("no_session");
				out.close();
			}
			Person updatePerson = personService.getPersonById(personSession.getId());
			updatePerson.setEdad(person.getEdad());
			updatePerson.setPais(person.getPais());
			updatePerson.setCiudad(person.getCiudad());
			personService.updatePerson(updatePerson);
			
			session.setAttribute("personSession", personService.getPersonById(personSession.getId()));
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e){
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
}


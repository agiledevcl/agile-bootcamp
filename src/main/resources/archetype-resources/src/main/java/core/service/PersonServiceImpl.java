package ${groupId}.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${groupId}.core.form.Person;
import ${groupId}.core.dao.PersonDAO;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDAO personDAO;
	
	@Transactional
	public void addPerson(Person person) {
		personDAO.addPerson(person);
	}
	
	@Transactional
	public void updatePerson(Person person){
		personDAO.updatePerson(person);
	}

	@Transactional
	public void removePerson(Integer id) {
		personDAO.removePerson(id);		
	}

	@Transactional
	public List<Person> listPerson() {
		return personDAO.listPerson();
	}
	

	@Transactional
	public Person getPersonById(Integer id){				
		Person person = personDAO.getPersonById(id);
		return person;
	}

	@Transactional
	public Person getPersonByDni(String dni){
		Person person=personDAO.getPersonByDni(dni);
		return person;
	}
	
}

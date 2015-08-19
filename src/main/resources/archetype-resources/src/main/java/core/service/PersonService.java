package ${groupId}.core.service;

import java.util.List;

import ${groupId}.core.form.Person;

public interface PersonService {
	public void addPerson(Person person);
	public void updatePerson(Person person);
	public void removePerson(Integer id);
	public List<Person> listPerson();
	public Person getPersonById(Integer id);
	public Person getPersonByDni(String dni);
}

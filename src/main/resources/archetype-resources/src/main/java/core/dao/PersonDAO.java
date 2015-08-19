package ${groupId}.core.dao;

import java.util.List;

import ${groupId}.core.form.Person;

public interface PersonDAO {
	public void addPerson(Person person);
	public void updatePerson(Person person);
	public void removePerson(Integer id);
	public List<Person> listPerson();
	public Person getPersonById(Integer id);
	public Person getPersonByDni(String dni);
  }

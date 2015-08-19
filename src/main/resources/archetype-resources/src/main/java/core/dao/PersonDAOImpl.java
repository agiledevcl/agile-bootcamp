package ${groupId}.core.dao;

import java.util.List;

import ${groupId}.core.form.Person;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
@Repository
public class PersonDAOImpl implements PersonDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addPerson(Person person) {
		sessionFactory.getCurrentSession().save(person);
	}
	
	public void updatePerson(Person person){
		sessionFactory.getCurrentSession().update(person);
	}

	public void removePerson(Integer id) {
		Person person = (Person) sessionFactory.getCurrentSession().load(Person.class,id);
		if (null != person) {
			sessionFactory.getCurrentSession().delete(person);
		}	
	}

	@SuppressWarnings("unchecked")
	public List<Person> listPerson() {
		return sessionFactory.getCurrentSession().createQuery("from Person")
        .list();
	}
		
	public Person getPersonById(Integer id){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
		criteria.add(Restrictions.eq("id", id));
		return (Person) criteria.uniqueResult();
	}

	public Person getPersonByDni(String dni){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
		criteria.add(Restrictions.eq("dni", dni));
		return (Person) criteria.uniqueResult();
	}


}

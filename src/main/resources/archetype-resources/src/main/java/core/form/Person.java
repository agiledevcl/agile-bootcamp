package ${groupId}.core.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="AgileBootcamp_PERSON")
public class Person {
     
	@Id
	@Column(name="ID")
	@SequenceGenerator(name="AgileBootcamp_person_seq",sequenceName="AgileBootcamp_person_sequence",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AgileBootcamp_person_seq")
	@Expose
	private Integer id;
     
	@Column(name="DNI")
	@Expose
	private String dni;
 
	@Column(name="CN")
	@Expose
	private String commonName;
         
    @Column(name="EDAD")
    private Integer edad;
    
    @Column(name="PAIS")
    private String pais;
    
    @Column(name="CIUDAD")
    private String ciudad;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
}

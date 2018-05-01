package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class prueba {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private long Id;
private String Nombre;

public prueba(){
	
}

public prueba(String Nombre) {
	this.Nombre = Nombre;
}

public long getId() {
	return Id;
}
public void setId(long id) {
	Id = id;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
}


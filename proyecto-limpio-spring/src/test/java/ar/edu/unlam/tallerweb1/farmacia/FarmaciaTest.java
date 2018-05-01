package ar.edu.unlam.tallerweb1.farmacia;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Punto;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Comuna;

import ar.edu.unlam.tallerweb1.SpringTest;


import static org.assertj.core.api.Java6Assertions.assertThat;

public class FarmaciaTest extends SpringTest{
	private Session session;
	private Farmacia Farmacity;
	private Direccion direccion1;
	private Barrio almagro;
	private Punto punto1;
	private Comuna comuna1;
	private List<Farmacia> farmaciasBuscadas;
	
	@Before
	public void setUp() {
		session = this.getSession();
		farmaciasBuscadas = new ArrayList<>();
				
		
		comuna1 = new Comuna("Comuna1");
		session.save(comuna1);
		
		almagro = new Barrio("almagro", comuna1);
		session.save(almagro);
		
		direccion1 = new Direccion("Avenida Pipi", "332", almagro);
		session.save(direccion1);
		
		punto1 = new Punto("54,32","45,32");
		session.save(punto1);
		
		Farmacity = new Farmacia("Farmacity","1234","Martes", direccion1, punto1);
		session.save(Farmacity);
	}
	
	@Test
	@Transactional
	@Rollback()
	public void buscarFarmaciasTurnoMartes() {
		farmaciasBuscadas = session
				.createCriteria(Farmacia.class)
				.add(Restrictions.eq("diaDeTurno","Martes"))
				.list();
		
		assertThat(farmaciasBuscadas.size()).isEqualTo(1);
	}
	
	@Test
	@Transactional
	@Rollback()
	public void buscarFarmaciasMismaCalle() {
		farmaciasBuscadas = session
				.createCriteria(Farmacia.class)
				.createAlias("direccion", "direccionBuscada")
				.add(Restrictions.eq("direccionBuscada.calle", "Avenida Pipi"))
				.list();
		
		assertThat(farmaciasBuscadas.size()).isEqualTo(1);
	}
	
	@Test
	@Transactional
	@Rollback()
	public void buscarFarmaciasMismoBarrio() {
		farmaciasBuscadas = session
				.createCriteria(Farmacia.class)
				.createAlias("direccion", "direccionBuscada")
				.createAlias("direccionBuscada.barrio", "barrioBuscado")
				.add(Restrictions.eq("barrioBuscado.Nombre", "almagro"))
				.list();
		
		assertThat(farmaciasBuscadas.size()).isEqualTo(1);		
	}
}

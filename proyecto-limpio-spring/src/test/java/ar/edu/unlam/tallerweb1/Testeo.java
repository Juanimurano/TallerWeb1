package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;

import static org.assertj.core.api.Java6Assertions.assertThat;


import ar.edu.unlam.tallerweb1.modelo.prueba;

public class Testeo extends SpringTest{

	private prueba prueba1, prueba2;
	private Session session;
	private List<prueba> pruebaBuscada;
	
	@Before
	public void setUp() {
		session = this.getSession();
		pruebaBuscada = new ArrayList<>();
		
		prueba1 = new prueba("Jose");
		prueba2 = new prueba("Juan");
		
		session.save(prueba1);
		session.save(prueba2);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testVacio() {
		pruebaBuscada = session
		.createCriteria(prueba.class)
		.add(Restrictions.eq("Nombre", "Carlos"))
		.list();

		assertThat(pruebaBuscada.size()).isEqualTo(0);
	}

	
	@Test
	@Transactional
	@Rollback
	public void testUnResultado() {
		pruebaBuscada = session
		.createCriteria(prueba.class)
		.add(Restrictions.eq("Nombre", "Juan"))
		.list();

		assertThat(pruebaBuscada.size()).isEqualTo(1);
	}

}

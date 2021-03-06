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

public class FarmaciaTest extends SpringTest {
	private Session session;
	private Farmacia Farmacity;
	private Farmacia DrAhorro;
	private Farmacia OtroFarmacity;
	private Farmacia OtroDrAhorro;
	private Farmacia OtraFarmacia;
	private Direccion direccion1;
	private Direccion direccion2;
	private Direccion direccion3;
	private Direccion direccion4;
	private Direccion direccion5;
	private Barrio constitucion;
	private Barrio retiro;
	private Barrio almagro;
	private Barrio recoleta;
	private Barrio palermo;
	private Punto punto1;
	private Punto punto2;
	private Punto punto3;
	private Punto punto4;
	private Punto punto5;
	private Comuna comuna1;
	private Comuna comuna2;
	private Comuna comuna5;
	private Comuna comuna14;
	private List<Farmacia> farmaciasBuscadas;
	
	@Before
	public void setUp() {
		session = this.getSession();
		farmaciasBuscadas = new ArrayList<>();
				
		
		comuna1 = new Comuna("Comuna 1");
		session.save(comuna1);
		
		constitucion = new Barrio("constitucion", comuna1);
		session.save(constitucion);
		
		retiro = new Barrio("retiro", comuna1);
		session.save(retiro);
		
		
		comuna5 = new Comuna("Comuna 5");
		session.save(comuna5);
		
		almagro = new Barrio("almagro", comuna5);//Almagro est� en la comuna 5
		session.save(almagro);
		

		comuna2 = new Comuna("Comuna 2");
		session.save(comuna2);
		
		recoleta = new Barrio("recoleta", comuna2);//Recoleta, �nico barrio de la comuna 2
		session.save(recoleta);
		

		comuna14 = new Comuna("Comuna 14");
		session.save(comuna14);
		
		palermo = new Barrio("palermo", comuna14);//Misma situaci�n que Recoleta
		session.save(palermo);
		
		
		direccion1 = new Direccion("Avenida Pipi", "332", almagro);
		session.save(direccion1);
		
		direccion2 = new Direccion("Calle Falsa", "123", recoleta);//Chiste de cierto programa
		session.save(direccion2);
		
		direccion3 = new Direccion("Calle Cabrera", "1234", palermo);//Calle verdadera
		session.save(direccion3);
		
		direccion4 = new Direccion("Avenida San Juan", "1111", constitucion);
		session.save(direccion4);
		
		direccion5 = new Direccion("Juncal", "0000", retiro);
		session.save(direccion5);
		
		
		punto1 = new Punto("54,32","45,32");
		session.save(punto1);
		
		punto2 = new Punto("34,6","58,5");
		session.save(punto2);
		
		punto3 = new Punto("60","90");
		session.save(punto3);
		
		punto4 = new Punto("50","50");
		session.save(punto4);
		
		punto5 = new Punto("50","40");
		session.save(punto5);
		
		
		Farmacity = new Farmacia("Farmacity","1234","Martes", direccion1, punto1);
		session.save(Farmacity);
		
		DrAhorro = new Farmacia("Dr. Ahorro","567890","Viernes", direccion2, punto2);
		session.save(DrAhorro);
		
		OtroFarmacity = new Farmacia("Farmacity","4444","Martes", direccion3, punto3);
		session.save(OtroFarmacity);
		
		OtroDrAhorro = new Farmacia("Dr. Ahorro","45678901","Jueves", direccion4, punto4);
		session.save(OtroDrAhorro);
		
		OtraFarmacia = new Farmacia("Azul","43210987","Lunes a Jueves", direccion5, punto5);
		session.save(OtraFarmacia);
	}
	
	@Test
	@Transactional
	@Rollback()
	public void buscarFarmaciasTurnoMartes() {
		farmaciasBuscadas = session
			.createCriteria(Farmacia.class)
			.add(Restrictions.eq("diaDeTurno","Martes"))
			.list();
		
		assertThat(farmaciasBuscadas.size()).isEqualTo(2);//�Si hay una sola farmacia, no m�s de una, sino falla!
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
	
	@Test
	@Transactional
	@Rollback()
	public void buscarFarmaciasRecoleta() {
		farmaciasBuscadas = session
			.createCriteria(Farmacia.class)
			.createAlias("direccion", "direccionBuscada")
			.createAlias("direccionBuscada.barrio", "barrioBuscado")
			.add(Restrictions.eq("barrioBuscado.Nombre", "recoleta"))
			.list();
		
		assertThat(farmaciasBuscadas.size()).isGreaterThanOrEqualTo(1);//As� cuando hay dos o m�s farmacias en el mismo barrio este test no falle
	}
	
	@Test
	@Transactional
	@Rollback()
	public void buscarTodasLasFarmacity() {
		farmaciasBuscadas = session
			.createCriteria(Farmacia.class)
			.add(Restrictions.eq("nombre","Farmacity"))
			.list();
		
		assertThat(farmaciasBuscadas.size()).isGreaterThanOrEqualTo(1);
	}
	
	@Test
	@Transactional
	@Rollback()
	public void buscarFarmaciasMismaComuna() {//S�, de la Comuna 1
		farmaciasBuscadas = session
			.createCriteria(Farmacia.class)
			.createAlias("direccion", "direccionBuscada")
			.createAlias("direccionBuscada.barrio", "barrioBuscado")
			.createAlias("barrioBuscado.comuna", "comunaBuscada")
			.add(Restrictions.eq("comunaBuscada.nombre", "Comuna 1"))
			.list();
		
		assertThat(farmaciasBuscadas.size()).isGreaterThanOrEqualTo(1);
	}
	
	@Test
	@Transactional
	@Rollback()
	public void buscarFarmacityEnPalermo() {
		farmaciasBuscadas = session
			.createCriteria(Farmacia.class)
			.add(Restrictions.eq("nombre","Farmacity"))
			.createAlias("direccion", "direccionBuscada")
			.createAlias("direccionBuscada.barrio", "barrioBuscado")
			.add(Restrictions.eq("barrioBuscado.Nombre", "palermo"))
			.list();
		
		assertThat(farmaciasBuscadas.size()).isGreaterThanOrEqualTo(1);
	}
}

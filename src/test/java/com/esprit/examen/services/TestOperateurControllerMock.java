package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.controllers.OperateurController;
import com.esprit.examen.entities.Operateur;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestOperateurControllerMock {

	@Mock
	OperateurServiceImpl osI;
	@InjectMocks
	OperateurController osC;


	Operateur o = Operateur.builder().idOperateur((long) 7).nom("yahia").prenom("snoussi").password("yahia").build();

	@Test
	public void RetrieveOperateurById() {

		Mockito.when(osI.retrieveOperateur(Mockito.anyLong())).thenReturn(o);
		Operateur o_get = osI.retrieveOperateur((long) 7);
		assertNotNull(o_get);
		verify(osI).retrieveOperateur(Mockito.anyLong());
	}

	@Test
	public void RetrieveAll() {
		List<Operateur> operateurs = new ArrayList<>();
		operateurs.add(new Operateur());

		when(osI.retrieveAllOperateurs()).thenReturn(operateurs);

		List<Operateur> expected = osI.retrieveAllOperateurs();

		assertEquals(expected, operateurs);
		verify(osI).retrieveAllOperateurs();
	}
}
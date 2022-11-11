package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.controllers.FournisseurRestController;
import com.esprit.examen.entities.Fournisseur;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestFournisseurControllerMock {

	@Mock
	FournisseurServiceImpl osI;
	@InjectMocks
	FournisseurRestController osC;


	Fournisseur o = Fournisseur.builder().idFournisseur((long) 7).code("yahia").libelle("yahiaModuleFournisseur").build();

	
	@RepeatedTest(4)
	public void RetrieveFournisseurById() {

		Mockito.when(osI.retrieveFournisseur(Mockito.anyLong())).thenReturn(o);
		Fournisseur o_get = osI.retrieveFournisseur((long) 7);
		assertNotNull(o_get);
		verify(osI).retrieveFournisseur(Mockito.anyLong());
	}

	@Test
	public void RetrieveAll() {
		List<Fournisseur> fournisseurs = new ArrayList<>();
		fournisseurs.add(new Fournisseur());

		when(osI.retrieveAllFournisseurs()).thenReturn(fournisseurs);

		List<Fournisseur> expected = osI.retrieveAllFournisseurs();

		assertEquals(expected, fournisseurs);
		verify(osI).retrieveAllFournisseurs();
	}
}
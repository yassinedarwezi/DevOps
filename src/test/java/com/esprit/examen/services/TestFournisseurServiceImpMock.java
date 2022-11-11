package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.OperateurRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestFournisseurServiceImpMock {

	@Mock
	FournisseurRepository or;
	@InjectMocks
	FournisseurServiceImpl osI;

	Fournisseur o = Fournisseur.builder().idFournisseur((long) 7).code("yahia").libelle("yahiaModuleFournisseur").build();

	@Test
	public void AddFournisseur() {
		Fournisseur o_add = new Fournisseur();
		o_add.setCode("yahia add");
		o_add.setLibelle("yahiapre add");

		Mockito.when(or.save(ArgumentMatchers.any(Fournisseur.class))).thenReturn(o_add);

		Fournisseur o_added = osI.addFournisseur(o_add);

		assertEquals(o_add.getCode(), o_added.getCode());
		assertEquals(o_add.getLibelle(), o_added.getLibelle());
		
		verify(or).save(o_add);
	}

	@Test
	public void RetrieveFournisseurById() {

		Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(o));
		Fournisseur o_get = osI.retrieveFournisseur((long) 7);
		assertNotNull(o_get);
		verify(or).findById(Mockito.anyLong());
	}

	@Test
	public void RetrieveAll() {
		List<Fournisseur> fournisseurs = new ArrayList<>();
		fournisseurs.add(new Fournisseur());

		when(or.findAll()).thenReturn(fournisseurs);

		List<Fournisseur> expected = osI.retrieveAllFournisseurs();

		assertEquals(expected, fournisseurs);
		verify(or).findAll();
	}

	@Test
	public void DeleteFournisseur_ifFound() {
		Fournisseur o_delete = new Fournisseur();
		o_delete.setCode("yahia delete");
		o_delete.setIdFournisseur(1L);

		when(or.findById(o_delete.getIdFournisseur())).thenReturn(Optional.of(o_delete));

		osI.deleteFournisseur(o_delete.getIdFournisseur());
		verify(or).deleteById(o_delete.getIdFournisseur());
	}

	@Test
	public void DeleteException_ifnotFound() {
		try {
			Fournisseur o_delete = new Fournisseur();
			o_delete.setIdFournisseur(2L);
			o_delete.setCode("cxxsxws");

			when(or.findById(anyLong())).thenReturn(Optional.ofNullable(null));
			osI.deleteFournisseur(o_delete.getIdFournisseur());
		} catch (Exception e) {
			String expectedMessage = "entity with id";
			String actualMessage = e.getMessage();

			assertTrue(actualMessage.contains(expectedMessage));
		}
	}

	@Test
	public void EditFournisseur_ifFound() {
		Fournisseur o_edit = new Fournisseur();
		o_edit.setIdFournisseur(3L);
		o_edit.setCode("yahia edit");

		Fournisseur new_o_edit = new Fournisseur();
		new_o_edit.setCode("new yahia edit");

		when(or.findById(o_edit.getIdFournisseur())).thenReturn(Optional.of(o_edit));
		o_edit = osI.updateFournisseur(new_o_edit);

		verify(or).save(o_edit);
	}
	

	@Test
	public void EditException_ifnotFound() {
		try {
			Fournisseur o_edit = new Fournisseur();
			o_edit.setIdFournisseur(4L);
			o_edit.setCode("yahia edit");

			Fournisseur new_o_edit = new Fournisseur();
			new_o_edit.setIdFournisseur(5L);
			new_o_edit.setCode("new yahia edit");

			when(or.findById(anyLong())).thenReturn(Optional.ofNullable(null));
			osI.updateFournisseur(new_o_edit);

		} catch (Exception e) {
			String expectedMessage = "entity with id";
			String actualMessage = e.getMessage();

			assertTrue(actualMessage.contains(expectedMessage));
		}
	}
}
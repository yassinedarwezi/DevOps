package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import lombok.extern.slf4j.Slf4j;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;

@Slf4j
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProduitServiceImplMockTest {
	
	@Mock
	ProduitRepository produitrepo;
	@InjectMocks
	ProduitServiceImpl produitservi;
	
	Produit p = Produit.builder().codeProduit("200").libelleProduit("produit200").prix((float) 200).dateCreation(null).dateDerniereModification(null).build();

	
	@Test
	public void GetProductTest() {
		List<Produit> listproducts = new ArrayList<>();
		listproducts.add(new Produit());
		
		Mockito.when(produitrepo.findAll()).thenReturn(listproducts);
		List<Produit> listp = produitservi.retrieveAllProduits();
		assertEquals(listp, listproducts);
		log.info("get product ==>"+ listp.toString());
		
	}
	
	@Test
	public void GetbyID() {

		Mockito.when(produitrepo.findById(Mockito.anyLong())).thenReturn(Optional.of(p));
		Produit prod = produitservi.retrieveProduit(3L);
		assertNotNull(prod);
		log.info("get ==> " + prod.toString());
		verify(produitrepo).findById(Mockito.anyLong());

	}
	
	@Test
	public void AddProductTest() {

		Produit produit = new Produit();
		produit.setLibelleProduit("SpiritOfGamer");
		Mockito.lenient().when(produitrepo.save(produit)).thenReturn(produit);
		Produit newp = produitservi.addProduit(produit);
		assertEquals(produit.getLibelleProduit(), newp.getLibelleProduit());
		verify(produitrepo).save(produit);
		log.info("Added ==> " + produit.toString());
	}
	

	
	
	@Test
	public void DeleteTest() {
		Produit p = new Produit();
		p.setLibelleProduit("libelle");
		p.setIdProduit((long) 3);
		Long pid = p.getIdProduit();
		Mockito.lenient().when(produitrepo.findById(pid)).thenReturn(Optional.of(p));

		produitservi.deleteProduit(pid);
		verify(produitrepo).deleteById(pid);
		log.info("Deleted ==> " + pid.toString());
	}
}

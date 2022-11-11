package com.esprit.examen.services;

import com.esprit.examen.entities.Facture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class FactureServiceImplTest {
    @Autowired
    IFactureService factureService;

    @Test
    public void testAddFacture() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = dateFormat.parse("30/09/2000");
        Date date2 = dateFormat.parse("30/09/2000");
        Facture f = new Facture(1L, 2, 3, date1, date2, true);
        Facture facture = factureService.addFacture(f);
        System.out.print("facture " + facture);
        assertNotNull(facture.getIdFacture());

    }
    @Test
    public void testcancelFacture() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = dateFormat.parse("30/09/2000");
        Date date2 = dateFormat.parse("30/09/2000");
        Facture f = new Facture(1L, 2, 3, date1, date2, false);
        factureService.cancelFacture(f.getIdFacture());
        assertFalse(f.getArchivee());

    }
    @Test
    public void testRetrieveFactures() throws ParseException {


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = dateFormat.parse("30/09/2000");
        Date date2 = dateFormat.parse("30/09/2000");
        List<Facture> clients = factureService.retrieveAllFactures();
        int expected = clients.size();
        Facture f = new Facture(1L, 2, 3, date1, date2, false);
        factureService.cancelFacture(f.getIdFacture());
        assertEquals(expected = 1, factureService.retrieveAllFactures().size());
    }

}
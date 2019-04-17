package local.gsb.main.data;

import org.junit.Test;


public class DateManagerTest {

    //Get mois précédent sur la date actuelle
    @Test
    public void getMoisPrecedent() {
        String resultat = DateManager.getMoisPrecedent();
        System.out.println("GetMoisPredent() => Mois précédent du mois actuel : " + resultat + ".");

    }

    //Get mois précédent sur une date donnée
    @Test
    public void getMoisPrecedent1() {
        //Date au format timesstamp
        long localTime = 1329145929000L;
        String dateentree = "13/02/2012";
        //Possibilité d'utiliser cette variable pour choisir une date plus facilement
        //long localTime = LocalDateTime.of(2019, 11, 28, 1, 1).toInstant(ZoneOffset.UTC).toEpochMilli();
        String resultat = DateManager.getMoisPrecedent(localTime);
        System.out.println("GetMoisPrecedent("+localTime+") => Le mois précédent de la date entrée ("+ dateentree +") est : " + resultat + "." );
    }

    //Get mois suivant sur la date actuelle
    @Test
    public void getMoisSuivant() {
        String resultat = DateManager.getMoisSuivant();
        System.out.println("GetMoisSuivant() => Mois suivant du mois actuel : " + resultat + ".");
    }

    //Get mois suivant sur la date donnée
    @Test
    public void getMoisSuivant1() {
        //Date au format timesstamp
        long localTime = 1329145929000L;
        String dateentree = "13/02/2012";
        //Possibilité d'utiliser cette variable pour choisir une date plus facilement
        //long localTime = LocalDateTime.of(2019, 11, 28, 1, 1).toInstant(ZoneOffset.UTC).toEpochMilli();
        String resultat = DateManager.getMoisSuivant(localTime);
        System.out.println("GetMoisSuivant("+localTime+") => Le mois suivant de la date entrée ("+ dateentree +") est : " + resultat + "." );
    }

    //Entre sur la date actuelle
    @Test
    public void entre() {
        int jour1 = 12;
        int jour2 = 24;
        boolean resultat = DateManager.entre(jour1, jour2);
        System.out.println("entre(" + jour1 + ", " + jour2 + ") => Le jour actuel se situe-t-il entre le jour " + jour1 + " et le jour " + jour2 + "? Resultat : " + resultat + ".");
    }

    //Entre sur la date précédente
    @Test
    public void entre1() {
        //Date au format timesstamp
        long localTime = 1329145929000L;
        String dateentree = "13/02/2012";
        //Possibilité d'utiliser cette variable pour choisir une date plus facilement
        //long localTime = LocalDateTime.of(2019, 11, 28, 1, 1).toInstant(ZoneOffset.UTC).toEpochMilli();
        int jour1 = 3;
        int jour2 = 28;
        boolean resultat = DateManager.entre(jour1, jour2, localTime);
        System.out.println("entre(" + jour1 + ", " + jour2 + ", " + localTime + ") => Le jour entré (" + dateentree + ") se situe-t-il entre le jour " + jour1 + " et le jour " + jour2 + "? Resultat : " + resultat + ".");
    }
}
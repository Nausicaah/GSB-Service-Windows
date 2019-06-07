package local.gsb.main;

import local.gsb.main.data.DateManager;
import local.gsb.main.database.MysqlConnection;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe principale qui
 * permet d'amorcer le programme
 * et tilise les class DateManager & MysqlConnection,
 * utilise un Timer, qui démarre 5 secondes après le démarrage de l'application.
 * L'application vérifie les informations tous les 5 jours,
 * une connexion à la base de donnée est créée puis cloturée lorsque la tâche est effectuée :
 * entre le 1er et le 10 (inclus) : l'application met à jour la db et cloture la saisie des fiches et
 * à partir du 20, jusqu'au 31 (inclus) : l'application met à jour les fiches validées.
 */

public class Main {

    public static void main(String[] args) {

        runTimer();

    }

    public static void runTimer(){


        //Délai avant activation
        long delay = 5000L;
        //Période d'activation (tous les 5 jours)
        long period = 432000000L;
        //Timer
        // Objet Timer
        Timer timer = new Timer();
        //Mise en place de la tâche à effectuer
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        //connection mysql
                        MysqlConnection gsb_frais = new MysqlConnection("51.77.194.225", "gsb_java", "userGsb", "secret");
                        //MysqlConnection.curseur();
                        System.out.println("Timer opérationnel");

                        //Récupération de la date actuelle
                        LocalDateTime localTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
                        //On recupère la valeur de l'annee et on la convertie en String;
                        int annee = localTime.getYear();

                        //Récupération du mois précédent
                        String moisPrecedent = DateManager.getMoisPrecedent();

                        //Changement de l'année si le mois précédent est décembre
                        if (moisPrecedent.equals("12")) {
                            annee--;
                        }


                        //On converti l'année
                        String anneeFiche = String.valueOf(annee);


                        //A partie du 1er jusqu'au 10 inclus
                        if (DateManager.entre(0, 11)) {
                            System.out.println("Cloturation des fiches de frais du mois précédent (" + anneeFiche + "/" + moisPrecedent + ") en cours.");

                            //Execution de la commande UPDATE (Cloturation des fiches précédentes)
                            Statement result = gsb_frais.executeRaw("UPDATE `fichefrais` SET idetat = \"CL\" WHERE mois = \"" + anneeFiche + moisPrecedent + "\"");

                            System.out.println("Execution de la requête : \"UPDATE `fichefrais` SET idetat = \"CL\" WHERE mois = \"" + anneeFiche + moisPrecedent + "\"\"");

                            //A partir du 20eme jour jusqu'au 31 inclus
                        } else if (DateManager.entre(19, 32)) {
                            System.out.println("Remboursement des fiches de frais validées du mois précédent (" + anneeFiche + "/" + moisPrecedent + ") en cours.");
                            //Execution de la commande UPDATE (Remboursement des fiches validées)

                            Statement result = gsb_frais.executeRaw("UPDATE `fichefrais` SET idetat = \"RB\" WHERE mois = \"" + anneeFiche + moisPrecedent + "\" AND idetat = \"VA\"");

                            System.out.println("\"UPDATE `fichefrais` SET idetat = \"RB\" WHERE mois = \"" + anneeFiche + moisPrecedent + "\" AND idetat = \"VA\"\"");
                        }

                        //On ferme la connexion SQL
                        gsb_frais.mysqlClose();
                        LocalDateTime next = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() + period), ZoneId.systemDefault());
                        System.out.println("Prochaine mise à jour le : " + next.toLocalDate().toString());
                    }
                }, delay, period);
    }
}

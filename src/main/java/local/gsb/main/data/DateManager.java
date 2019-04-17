package local.gsb.main.data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Classe utilitaire afin de gérer plusieurs méthodes,
 * les méthodes permettent d'accéder à divers informations.
 * <br>
 * getMoisSuivant() : retourne le mois suivant du mois actuel
 * <br>
 * getMoisPrécédent() : retourne le mois précédent du mois actuel
 * <br>
 * getMoisSuivant(long timeStamp) : retourne le mois suivant du mois entré
 * <br>
 * getMoisPrécédent(long timeStamp) : retourne le mois précédent du mois entré
 * <br>
 * entre(int jour1, int jour2) : vérifie si la date du jour se trouve entre les deux entiers, retourne un boolean.
 * <br>
 * entre(int jour1, int jour2, long timeStamp) : vérifie si la date entrée se trouve entre les deux entiers, retourne un boolean.
 */
public abstract class DateManager {

    /**
     * Méthode générale pour récupérer un mois donné :
     * peut récupérer le mois précédent ou bien le mois suivant.
     *
     * @param long timeStamp : timeStamp (temps en ms)
     * @param int  offSet : décalage de la date
     * @return String mois : retourne le mois voulu (suivant ou précédent en fonction du param offSet), si la chaine est inf à 2 caractères, concate  un "0" (mois 1 à 9)
     */
    private static String getMois(long timeStamp, int offSet) {
        //On recupère le local time en fonction du timestamp
        LocalDateTime localTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());

        //Changement de mois

        if (offSet > 0) {
            localTime = localTime.plusMonths(offSet);
        } else if (offSet < 0) {
            localTime = localTime.minusMonths(Math.abs(offSet));
        }

        //On recupère la valeur du mois et on la convertie en String;
        String result = String.valueOf(localTime.getMonth().getValue());

        //On rajoute un 0 si le mois ne contient pas deux charactères
        if (result.length() > 1) return result;
        return "0" + result;
    }

    // MOIS PRECEDENT

    /**
     * Méthode getMoisPrécédent :
     * retourne le mois précédent du mois actuel et
     * utilise la surcharge de la méthode getMoisPrecedent(timeStamp)
     *
     * @return getMoisPrecedent(timeStamp)
     */
    public static String getMoisPrecedent() {
        return getMoisPrecedent(System.currentTimeMillis());
    }

    /**
     * Méthode getMoisPrécédent(timeStamp) :
     * retourne le mois précédent du timeStamp entré
     * et utilise la fonction getMois(timeStamp, offset).
     *
     * @param long timeStamp : temps en ms
     * @return int offSet = -1
     */
    public static String getMoisPrecedent(long timeStamp) {
        return getMois(timeStamp, -1);
    }

    // MOIS SUIVANT


    /**
     * Méthode getMoisSuivant :
     * retourne le mois suivant du mois actuel et
     * utilise la surcharge de la méthode getMoisSuivant(timeStamp).
     * @return getMoisSuivant(timeStamp)
     **/
    public static String getMoisSuivant() {
        return getMoisSuivant(System.currentTimeMillis());
    }

    /**
     * Méthode getMoisSuivant(timeStamp) :
     * retourne le mois suivant du timeStamp entré et
     * utilise la fonction getMois(timeStamp, offset).
     *
     * @param long timeStamp : temps en ms
     * @return int offSet : +1 (décalage mois suivant)
     */
    public static String getMoisSuivant(long timeStamp) {
        return getMois(timeStamp, +1);
    }

    // ENTRE

    /**
     * Méthode entre (jour1, jour2) :
     * retourne si la date du jour est entre les deux entiers entrés et
     * utilise la fonction entre(jour1, jour2, timeStamp).
     * Gère en cas d'inversion des jours (jour1 > jour2).
     *
     * @param int jour1 : nombre du jour1
     * @param int jour2 : nombre du jour2
     * @return entre(jour1, jour2, timeStamp) : utilise la méthode entre(jour1,jour2,timeStamp) avec le timeStamp du jour
     */
    public static boolean entre(int jour1, int jour2) {
        return entre(jour1, jour2, System.currentTimeMillis());
    }

    /**
     * Méthode entre (jour1, jour2, timeStamp) :
     * retourne si la date entrée (timeStamp) est entre les deux entiers entrés.
     *
     * @param int  jour1 : nombre du jour1
     * @param int  jour2 : nombre du jour2
     * @param long timeStamp : date entrée au format timeStamp
     * @return boolean : true si entre, false si non entre
     */
    public static boolean entre(int jour1, int jour2, long timeStamp) {
        LocalDateTime localTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());
        int day = localTime.getDayOfMonth();
        //Gestion de l'inversion des jours
        int minDay = Math.min(jour1, jour2);
        int maxDay = Math.max(jour1, jour2);
        return (minDay < day && maxDay > day);
    }

}

package local.gsb.main;

public class ResultRequete {

    private int index;
    private String idvisiteur;
    private String mois;

    //constructeur
    public ResultRequete(int index, String idvisteur, String mois){

        this.index = index;
        this.idvisiteur = idvisteur;
        this.mois = mois;

    }


    public int getIndex() {
        return index;
    }

    public String getIdvisiteur() {
        return idvisiteur;
    }

    public String getMois() {
        return mois;
    }
}

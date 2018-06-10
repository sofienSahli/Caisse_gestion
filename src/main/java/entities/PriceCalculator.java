package entities;

public class PriceCalculator {
    public static double calculerPrixRemise(double prix, int remise) {
        return  prix - ((prix / 100) * remise);
    }
    public  static  double prixTV (double prix ){
        return  ((prix / 100 ) * 19 ) + prix  ;
    }
    public static double prixVente (double prix , double gain ){
        return  ((prix / 100)*gain  ) + prix ;
    }

}

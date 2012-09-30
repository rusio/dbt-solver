package meter;
//Задължения

public class Obligation {

  public void getAllObligation() {

    System.out.println("\n");
    double all = DayElectricMeter.dSum() + NightElectricMeter.nSum();

    System.out.println("Обшо   :" + all + " Лв.\n");
  }


  public static void main(String[] args) {

    //Задължения за електричеството през деня
    DayElectricMeter dayObligation = new DayElectricMeter();
    dayObligation.getObligation();

    //Задължения за електричеството през нощта
    NightElectricMeter nightObligation = new NightElectricMeter();
    nightObligation.getObligation();

    //Общо
    Obligation ob = new Obligation();
    ob.getAllObligation();

    ProportionElectric dayAndNightObligation = new ProportionElectric();
    dayAndNightObligation.getObligation();
  }


}

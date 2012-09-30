package meter;

public class NightElectricMeter extends ElectricMeter {
  //Смятаме месечните натрупани киловата умножени по цената на електричеството през нощта за KW/h

  public static double nSum() {

    double sum = accumulateKW() * DataPrice.getPriseNightElectric();
    System.out.println("Дължите " + sum + " Лв. по нощтната тарифа");
    return sum;
  }

  public void getObligation() {
    System.out.println("\nМоля въведете данните от НОЩНИЯ електромер");
    setMeter();
    nSum();
  }
}

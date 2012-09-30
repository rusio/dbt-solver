package meter;

public class DayElectricMeter extends ElectricMeter {
  //Смятаме месечните натрупани киловата умножени по цената на електричеството през деня за KW/h

  public static double dSum() {

    double sum = accumulateKW() * DataPrice.getPriseDayElectric();
    System.out.println("Дължите " + sum + " Лв. по дневната тарифа");
    return sum;
  }

  public void getObligation() {
    System.out.println("\nМоля въведете данните от ДНЕВНИЯ електромер");
    setMeter();
    dSum();
  }
}

package meter;

public class DataPrice {

  //Взимаме цената на водата за m^3
  public static double getPriseWater() {
    double pr = 1.46;
    return pr;
  }

  //Взимаме цената на електричеството през нощта за KW/h
  public static double getPriseNightElectric() {
    double pr = 0.18;
    return pr;
  }

  //Взимаме цената на електричеството през деня за KW/h
  public static double getPriseDayElectric() {
    double pr = 0.21;
    return pr;
  }


  //Data за Необичайни случай.
  public static double getProportion() {
    double rate = 20; //проценти
    rate = rate / 100;
    return rate;
  }

}

package meter;
//Необичаен случай.

public class ProportionElectric extends ElectricMeter {


  public double dnSum() {
    //Алогоритъм за изчисляване на дневния и нощтния ток по проценти нощен.
    double totalKW = accumulateKW();
    double proportion = DataPrice.getProportion();
    double dayPrice = DataPrice.getPriseDayElectric();
    double nightPrice = DataPrice.getPriseNightElectric();

    double dayKW = totalKW * (1 - proportion);
    double nightKW = totalKW * proportion;
    double sum = (dayKW * dayPrice) + (nightKW * nightPrice);
    
    System.out.println("Дължите " + sum + " Лв. според договора");
    return sum;
  }

  public void getObligation() {
    System.out.println("\nМоля въведете данните от електромерa");
    setMeter();
    dnSum();

  }

}


//Относно алгоритъма:
//Примерен алгоритъм: 
//KW = 100 = accumulateKW
//Процентите = 20% = DataPrice.getProportion
//Цената за деня = 0.21 = DataPrice.getPriseDayElectric
//Цената за ноща = 0.18 = DataPrice.getPriseNightElectric
//((100 - (100*20%))*0.21) + ((100-(100 - (100*20%)))*0.18)

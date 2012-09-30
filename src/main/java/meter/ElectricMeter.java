package meter;

import java.util.Scanner;

public class ElectricMeter {
  static Scanner input = new Scanner(System.in);

  static int New, Old;

  //Въвеждаме стойностите на електромера - за изминалия и за настоящия месец
  public static void setMeter() {
    System.out.println("за изминялия месец: ");
    Old = input.nextInt();
    System.out.println("за натоящия месец: ");
    New = input.nextInt();
  }

  //Смятаме разликата в киловатите за да установим месечните натрупани киловата.
  public static double accumulateKW() {
    double KW = New - Old;
    //System.out.println("Изразходваните KW/h за този месец са: "+KW);
    return KW;
  }


}

package Lab6;

import Lab8.WagonOverloadedException;

public class EconomyClassWagon extends Wagon {
    public int wagonNumber = 0;
    public static int wagonCount = 0;
//    public int passengerCount;
//    public int luggageCount;
//    public int comfortLevel = 1;
    public EconomyClassWagon(int passengerCount, int luggageCount) throws WagonOverloadedException {
        super(passengerCount, luggageCount, 1, 250);
//        this.passengerCount = passengerCount;
//        this.luggageCount = luggageCount;
        wagonNumber += 1;
        wagonCount++;
        wagonNumber = wagonCount;
    }
    public int getWagonNumber(){
        return wagonNumber;
    }
//    @Override
//    public String toString(){
//        return "Economy class wagon number %s".formatted(wagonNumber);
//    }
}

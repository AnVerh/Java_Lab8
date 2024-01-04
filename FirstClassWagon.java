package Lab6;

import Lab8.WagonOverloadedException;

public class FirstClassWagon extends Wagon{
//    public int passengerCount;
//    public int luggageCount;
//    public int comfortLevel = 3;
    public int wagonNumber = 0;
    public static int wagonCount = 0;
    public FirstClassWagon(int passengerCount, int luggageCount) throws WagonOverloadedException {
        super(passengerCount, luggageCount, 3, 140);
        wagonCount++;
        wagonNumber = wagonCount;
    }
    public int getWagonNumber(){
        return wagonNumber;
    }
//    @Override
//    public String toString(){
//        return "First class wagon number %s".formatted(wagonNumber);
//    }
}

package Lab6;

import Lab8.WagonOverloadedException;

public class BusinessClassWagon extends Wagon{
//    public int passengerCount;
//    public int luggageCount;
//    public int comfortLevel = 2;
public int wagonNumber = 0;
    public static int wagonCount = 0;
    public BusinessClassWagon(int passengerCount, int luggageCount) throws WagonOverloadedException {
        super(passengerCount, luggageCount, 2, 150);
//        this.passengerCount = passengerCount;
//        this.luggageCount = luggageCount;
        wagonCount++;
        wagonNumber = wagonCount;
    }
    public int getWagonNumber(){
        return wagonNumber;
    }
//    @Override
//    public String toString(){
//        return "Business class wagon number %s".formatted(wagonNumber);
//    }
}

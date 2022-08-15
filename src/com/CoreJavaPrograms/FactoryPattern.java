package com.CoreJavaPrograms;

public class FactoryPattern {
    public static void main(String... args){
        CarFactory carFactory = new CarFactory();
        Car suv = carFactory.getCar("suv");
        System.out.println(suv);
    }
}

abstract class Car {
    private String engineDisplacement;
    private String color;
    private boolean isSuv;

    public abstract String getEngine();
    public abstract String getColor();
    public abstract boolean isSuv();
}

class SuvCar extends Car {
    private String engineDisplacement;
    private String color;
    private boolean isSuv;

    public SuvCar(String engineDisplacement, String color, boolean isSuv) {
        this.engineDisplacement = engineDisplacement;
        this.color = color;
        this.isSuv = isSuv;
    }

    @Override
    public String getEngine() {
        return this.engineDisplacement;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean isSuv() {
        return this.isSuv;
    }

    @Override
    public String toString() {
        return "SuvCar{" +
                "engineDisplacement='" + engineDisplacement + '\'' +
                ", color='" + color + '\'' +
                ", isSuv=" + isSuv +
                '}';
    }
}

class SedanCar extends Car {
    private String engineDisplacement;
    private String color;
    private boolean isSuv;

    public SedanCar(String engineDisplacement, String color, boolean isSuv) {
        this.engineDisplacement = engineDisplacement;
        this.color = color;
        this.isSuv = isSuv;
    }

    @Override
    public String getEngine() {
        return this.engineDisplacement;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean isSuv() {
        return this.isSuv;
    }

    @Override
    public String toString() {
        return "SedanCar{" +
                "engineDisplacement='" + engineDisplacement + '\'' +
                ", color='" + color + '\'' +
                ", isSuv=" + isSuv +
                '}';
    }
}

class CarFactory {

    public Car getCar(String carType) {
        if(carType == null) {
            System.out.println("Car type not provided");
            return null;
        }

        if(carType.equalsIgnoreCase("suv")){
            return new SuvCar("2000cc","Red", true);
        } else {
            return new SedanCar("1800cc", "black", false);
        }
    }
}
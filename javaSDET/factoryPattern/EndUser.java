package factoryPattern;

import commons.CarList;

public class EndUser {
    public static CarFactory carFactory;

    public static void main(String[] args){
        carFactory = getCar("ford");
        carFactory.viewCar();
        carFactory.bookCar();
        carFactory.driveCar();
    }

    public static CarFactory getCar(String carName){
        CarList carList = CarList.valueOf(carName.toUpperCase());

        switch (carList){
            case HONDA:
                carFactory = new HondaHead();
                break;
            case FORD:
                carFactory = new FordHead();
                break;
            default:
                throw new RuntimeException("No car found");
        }
        return carFactory;
    }
}

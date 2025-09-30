package factoryPattern;

public class FordHead extends CarFactory{

    @Override
    public void viewCar() {
        System.out.println("view Ford Car");
    }

    @Override
    public void driveCar() {
        System.out.println("drive Ford Car");
    }

    @Override
    public void bookCar() {
        System.out.println("book Ford Car");
    }
}

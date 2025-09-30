package factoryPattern;

public class HondaHead extends CarFactory{

    @Override
    public void viewCar() {
        System.out.println("view Honda Car");
    }

    @Override
    public void driveCar() {
        System.out.println("drive Honda Car");
    }

    @Override
    public void bookCar() {
        System.out.println("book Honda Car");
    }
}

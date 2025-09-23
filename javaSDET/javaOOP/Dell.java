package javaOOP;

public class Dell implements IComputer{
    @Override
    public void cpu() {
        System.out.println("CPU core i3 - 11000");
    }
    @Override
    public void ram() {
    }

    @Override
    public void ssd() {
    }
    @Override
    public void fan() {
    }

    public void gpu() {// đồ họa
        //onboard: tích hợp luôn trong main

        //Rời: gắn ngoài
    }
}

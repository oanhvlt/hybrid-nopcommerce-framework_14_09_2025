package javaBasic;

//abstract: trừu tượng => class cha
//Các hàm abstract: class con có thể extends và phải implement (tự viết lại body, cùng tên hàm)
//Các hàm non-abstract: class con có thể extends mà ko cần implement (chỉ cần dùng)

public abstract class Annimal {
    //constructor
    public Annimal(String annimalName){
        System.out.println("annimal running ....");
    }

    //Có chứa các hàm abstract: ko có body
    public abstract void sleep();

    //Có các hàm non-abstract: có phần body
    public void eat(){
        //body
        System.out.println("Eating ....");
    }

}

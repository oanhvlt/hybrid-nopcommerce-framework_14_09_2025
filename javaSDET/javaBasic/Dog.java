package javaBasic;

//extends: kế thừa lớp cha abstract > override các hàm non-abstract

//this: truy cập global
//super: gọi tới constructor của lớp cha: bắt buộc gọi nếu constructor có truyền tham số
public class Dog extends Annimal{

    //constructor
    String annimalName; // biến global

    public Dog( String annimalName){//biến local
        super(annimalName);
        this.annimalName = annimalName; //this.annimalName: là biến global
    }

    @Override
    public void sleep() {

    }
}

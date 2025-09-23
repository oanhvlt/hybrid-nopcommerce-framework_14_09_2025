package javaOOP;

// abstract class ngoài abstract method, có thể define thêm non-abstract method
// Interface: chỉ có thể chứa abstract method

public abstract class Tree_AbstractClass {
    //abstract
    abstract void setTreeName();

    //non-abstract: phải có body
    void cutTree( String treeName){
        //.....
    }
}

package phuongHoaXuan;

public class DinhBoLinh {
    //Access modifier
    //public: tất cả class đều truy cập được
    //private: chỉ truy cập được trong class chứa nó
    //protected: class kế thừa truy cập được
    //default: cùng package là truy cập dc => muốn class {kế thừa, khác package} không truy cập được thì set biến/method là default

    public String publicProp = "publicProp";
    public static String static_publicProp = "static_publicProp";
    public String getPublicProp(){
        return publicProp;
    }

    private String privateProp; // truy cập biến private = get/set
    public void setPrivateProp(){
        privateProp = "privateProp";
    }

    public String getPrivateProp(){
        return privateProp;
    }

    protected String protectedProp = "protectedProp";
    protected String getProtectedProp(){
        return protectedProp;
    }

    // biến static: khi class khác truy cập phải thông qua khởi tạo
    protected static String static_protectedProp = "static_protectedProp";
    protected String getStatic_protectedProp(){
        return static_protectedProp;
    }

    String defaultProp = "defaultProp";  //default
    String getDefaultProp(){
        return defaultProp;
    }

    public static void main(String[] args){
        DinhBoLinh dinhBoLinh = new DinhBoLinh();
        dinhBoLinh.privateProp = "Espresso";
        System.out.println( dinhBoLinh.getPrivateProp());
    }

}

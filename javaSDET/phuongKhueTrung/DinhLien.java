package phuongKhueTrung;

import phuongHoaXuan.DinhBoLinh;

public class DinhLien extends DinhBoLinh {
    public void clickToElem(){
        System.out.println(protectedProp);
        System.out.println(getProtectedProp());
        System.out.println(static_publicProp);
        System.out.println(static_protectedProp);
    }
    public static void main(String[] args) {
        DinhLien dinhLien = new DinhLien();
        dinhLien.clickToElem();
        dinhLien.getStatic_protectedProp();
        static_publicProp = "";
        static_protectedProp = "";

    }
}

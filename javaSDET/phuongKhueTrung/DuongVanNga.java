package phuongKhueTrung;

import phuongHoaXuan.DinhBoLinh;

public class DuongVanNga {

    public static void main(String[] args) {
        DinhBoLinh dinhBoLinh = new DinhBoLinh();
        System.out.println(dinhBoLinh.publicProp);
        System.out.println(dinhBoLinh.getPublicProp());

        //truy cập biến private thông qua get
        System.out.println(dinhBoLinh.getPrivateProp());
    }
}

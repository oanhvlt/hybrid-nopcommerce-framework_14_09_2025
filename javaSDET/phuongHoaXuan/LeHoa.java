package phuongHoaXuan;

public class LeHoa {
    public static void main(String[] args) {
        DinhBoLinh dinhBoLinh = new DinhBoLinh();
        dinhBoLinh.defaultProp = "default";
        dinhBoLinh.defaultProp = "Le hoa";
        System.out.println( dinhBoLinh.getDefaultProp());

    }
}

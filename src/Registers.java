public class Registers {
    private static byte[] R = new byte[64]; //general purpose registers

    private static boolean[] SREG = new boolean[] {false,false,false,false,false,false,false,false};

    private static short pc = -1;

    public static short getPc() {
        return pc;
    }

    public static void setPc(short pc) {
        Registers.pc = pc;
    }

    public static byte[] getR() {
        return R;
    }

    public static void setR(byte[] r) {
        R = r;
    }

    public static boolean[] getSREG() {
        return SREG;
    }

    public static void setSREG(boolean[] SREG) {
        Registers.SREG = SREG;
    }

    public static boolean getCarry(){
        return SREG[3];
    }

    public static void setCarry(boolean carry){
        Registers.SREG[3] = carry;
    }

    public static boolean getOverflow(){
        return SREG[4];
    }

    public static void setOverflow(boolean carry){
        Registers.SREG[4] = carry;
    }

    public static boolean getNegative(){
        return SREG[5];
    }

    public static void setNegative(boolean carry){
        Registers.SREG[5] = carry;
    }

    public static boolean getSign(){
        Registers.setSign(SREG[4] ^ SREG[5]);
        return SREG[6];
    }

    public static void setSign(boolean carry){
        Registers.SREG[6] = carry;
    }

    public static boolean getZero(){
        return SREG[7];
    }

    public static void setZero(boolean carry){
        Registers.SREG[7] = carry;
    }

}

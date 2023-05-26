public class Registers {
    private static byte[] R = new byte[64]; //general purpose registers

    private static boolean[] SREG = new boolean[] {false,false,false,false,false,false,false,false};

    private static int pc = 0;

    public static int getPc() {
        return pc;
    }

    public static void setPc(int pc) {
        Registers.pc = pc;
    }

    public static void incrementPC(){
        Registers.pc++;
    }

    public static byte[] getR() {
        return R;
    }

    public static void setR(int address, byte value) {
        R[address] = value;
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
        return SREG[6];
    }

    public static void setSign(){
        Registers.SREG[6] = SREG[4] ^ SREG[5];
    }

    public static boolean getZero(){
        return SREG[7];
    }

    public static void setZero(boolean carry){
        Registers.SREG[7] = carry;
    }

    public static void printR(){
        System.out.println("Content of each register is:");
        for(int i = 0; i < 64; i++){
            System.out.println("Address: " + i + "| Value: " + R[i]);
        }
        System.out.println("-------------------------------");

        System.out.println("Content of the flags: ");
        System.out.println("Carry flag: " + getCarry());
        System.out.println("Negative flag: " + getNegative());
        System.out.println("Overflow flag: " + getOverflow());
        System.out.println("Zero flag: " + getZero());
        System.out.println("Sign Flag: " + getSign());
        System.out.println("Program counter: " + getPc());

        System.out.println("------------------------------");
    }

}

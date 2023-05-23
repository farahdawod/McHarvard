public class InstructionSetArchitecture {
    public static void ADD(int R1, int R2) {
        Registers.setR(R1,(R1+R2));
    }

    public static void SUB(int R1, int R2) {
        Registers.setR(R1, (R1 - R2));
    }

    public static void MUL(int R1, int R2) {
        Registers.setR(R1, (R1 * R2));
    }

    public static void AND(int R1, int R2) {
        Registers.setR(R1, (R1 & R2));
    }

    public static void OR(int R1, int R2) {
        Registers.setR(R1, (R1 | R2));
    }

    public static void JR(int R1, int R2) {
        String concatenation = "" + R1 + R2;
        Registers.setPc(Integer.parseInt(concatenation));
    }

    public static void LDI(int R1, int IMM) {
        Registers.setR(R1, IMM);
    }

    public static void BEQZ(int R1, int IMM) {
        if (R1 == 0) {
            Registers.setPc(Registers.getPc() + 1 + IMM);
        }
    }
    public static void SLC(int R1, int IMM){
        Registers.setR(R1,R1 << IMM | R1 >>> 8 - IMM );
    }
    public static void SRC(int R1, int IMM){
        Registers.setR(R1,R1 >>> IMM | R1 << 8 - IMM);
    }
    public static void LB (int R1,int ADDRESS){
        Registers.setR(R1, DataMemory.getMemory()[ADDRESS]);
    }
    public static void SB (int R1,int ADDRESS){
        DataMemory.setMemory(ADDRESS,R1);
    }

    public static void main(String args[]) {
        int R1 = 30;
        int R2 = 10;
        int IMM =2;
        int ADDRESS=22;
        //ADD(R1,R2);
        //SUB(R1,R2);
        //MUL(R1,R2);
        //LDI(R1 , IMM);
        //BEQZ(R1,IMM);
        //JR(R1, R2);

        //System.out.println(Registers.getR()[R1]);
        //System.out.println(Registers.getPc());
        //System.out.println(R1);
    }

}


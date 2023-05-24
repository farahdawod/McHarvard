public class InstructionSetArchitecture {

    public static void ADD(int R1, int R2) { //R1 add,R2 val
        int temp = Registers.getR()[R1] + R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if (temp < 0) {
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(R1, (byte) temp);
        } else {
            Registers.setOverflow(true);
            Registers.setR(R1, (byte) temp);
        }
    }

    public static void SUB(int R1, int R2) { //R1 add,R2 val
        int temp = Registers.getR()[R1] - R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if (temp < 0) {
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(R1, (byte) temp);
        } else {
            Registers.setOverflow(true);
            Registers.setR(R1, (byte) temp);
        }
    }

    public static void MUL(int R1, int R2) { //R1 add,R2 val
        int temp = Registers.getR()[R1] * R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if (temp < 0) {
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(R1, (byte) temp);
        } else {
            Registers.setOverflow(true);
            Registers.setR(R1, (byte) temp);
        }
    }

    public static void AND(int R1, int R2) { //R1 add,R2 val
        int temp = Registers.getR()[R1] & R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if (temp < 0) {
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(R1, (byte) temp);
        } else {
            Registers.setOverflow(true);
            Registers.setR(R1, (byte) temp);
        }
    }

    public static void OR(int R1, int R2) { //R1 add,R2 val
        int temp = Registers.getR()[R1] | R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if (temp < 0) {
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(R1, (byte) temp);
        } else {
            Registers.setOverflow(true);
            Registers.setR(R1, (byte) temp);
        }
    }

    public static void JR(int R1, int R2) { //R1 val, R2 val
        String concatenation = "" + R1 + R2;
        Registers.setPc(Integer.parseInt(concatenation));
    }

    public static void LDI(int R1, byte IMM) { //R1 add, IMM val
        Registers.setR(R1, IMM);//
    }

    public static void BEQZ(int R1, byte IMM) { // R1 val, IMM val
        if (R1 == 0) {
            Registers.setPc(Registers.getPc() + IMM);
        }
    }

    public static void SLC(int R1, byte IMM) { //R1 add, IMM val
        Registers.setR(R1, (byte) (Registers.getR()[R1] << IMM | Registers.getR()[R1] >>> 8 - IMM));//
    }

    public static void SRC(int R1, byte IMM) { //R1 add, IMM val
        Registers.setR(R1, (byte) (Registers.getR()[R1] >>> IMM | Registers.getR()[R1] << 8 - IMM));//
    }

    public static void LB(int R1, byte MEM) { //R1 add, MEM val
        Registers.setR(R1, MEM);//
    }

    public static void SB(byte R1, int memADDRESS) { //R1 val, MEM add
        DataMemory.setMemory(memADDRESS,R1);//
    }

    public static void main(String args[]) {
        int R1 = 2;
        int R2 = 233;
        int IMM = 3;
        int ADDRESS = 22;
        byte least = (byte) 333;
        //System.out.println(least);
        //SB(R1,ADDRESS);
        //LB(R1,ADDRESS);
        //int[] a = new int[] {1, 2, 3, 4, 5, 6};
        //Datapath.setInstructionArray(a);
        //ADD(R1, R2);
        //SUB(R1,R2);
        //MUL(R1,R2);
        //LDI(R1 , IMM);
        //BEQZ(R1,IMM);
        //JR(R1, R2);
        //SRC(R1,IMM);
        //SLC(R1,IMM);
        //System.out.println(Registers.getR()[R1]);
        //System.out.println(Registers.getPc());
        //System.out.println(R1);
        // System.out.println(DataMemory.getMemory()[ADDRESS]);
    }

}


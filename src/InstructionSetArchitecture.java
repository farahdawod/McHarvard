public class InstructionSetArchitecture {
    public static void ADD(int R1, int R2) {
        int temp = R1 + R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if(temp<0){
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(Datapath.getInstructionArray()[1], temp);
        }
        else{
            byte tempB=(byte) temp;
            Registers.setOverflow(true);
            Registers.setR(Datapath.getInstructionArray()[1], tempB);
        }


    }

    public static void SUB(int R1, int R2) {
        int temp=R1 - R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if(temp<0){
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(Datapath.getInstructionArray()[1], temp);
        }
        else{
            byte tempB=(byte) temp;
            Registers.setOverflow(true);
            Registers.setR(Datapath.getInstructionArray()[1], tempB);
        }
    }

    public static void MUL(int R1, int R2) {
        int temp=R1*R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if(temp<0){
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(Datapath.getInstructionArray()[1], temp);
        }
        else{
            byte tempB=(byte) temp;
            Registers.setOverflow(true);
            Registers.setR(Datapath.getInstructionArray()[1], tempB);
        }
    }

    public static void AND(int R1, int R2) {
        int temp= R1&R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if(temp<0){
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(Datapath.getInstructionArray()[1], temp);
        }
        else{
            byte tempB=(byte) temp;
            Registers.setOverflow(true);
            Registers.setR(Datapath.getInstructionArray()[1], tempB);
        }
    }

    public static void OR(int R1, int R2) {
        int temp= R1|R2;
        if (temp == 0) {
            Registers.setZero(true);
        }
        if(temp<0){
            Registers.setNegative(true);
        }
        if (temp <= 255) {
            Registers.setR(Datapath.getInstructionArray()[1], temp);
        }
        else{
            byte tempB=(byte) temp;
            Registers.setOverflow(true);
            Registers.setR(Datapath.getInstructionArray()[1], tempB);
        }
    }

    public static void JR(int R1, int R2) {
        String concatenation = "" + R1 + R2;
        Registers.setPc(Integer.parseInt(concatenation));
    }

    public static void LDI(int R1, int IMM) {
        Registers.setR(Datapath.getInstructionArray()[1], Datapath.getInstructionArray()[5]);
    }

    public static void BEQZ(int R1, int IMM) {
        if (R1 == 0) {
            Registers.setPc(Registers.getPc() + 1 + Datapath.getInstructionArray()[5]);
        }
    }

    public static void SLC(int R1, int IMM) {
        Registers.setR(Datapath.getInstructionArray()[1], R1 << Datapath.getInstructionArray()[5] | R1 >>> 8 - Datapath.getInstructionArray()[5]);
    }

    public static void SRC(int R1, int IMM) {
        Registers.setR(Datapath.getInstructionArray()[1], R1 >>> Datapath.getInstructionArray()[5] | R1 << 8 - Datapath.getInstructionArray()[5]);
    }

    public static void LB(int R1, int ADDRESS) {
        Registers.setR(Datapath.getInstructionArray()[1], DataMemory.getMemory()[ADDRESS]);
    }

    public static void SB(int R1, int ADDRESS) {
        DataMemory.setMemory(ADDRESS, Datapath.getInstructionArray()[3]);
    }

    public static void main(String args[]) {
        int R1 = 2;
        int R2 = 233;
        int IMM = 3;
        int ADDRESS = 22;
        byte least= (byte)333;
        //System.out.println(least);
        //SB(R1,ADDRESS);
        //LB(R1,ADDRESS);
        //int[] a = new int[] {1, 2, 3, 4, 5, 6};
        //Datapath.setInstructionArray(a);
        ADD(R1,R2);
        //SUB(R1,R2);
        //MUL(R1,R2);
        //LDI(R1 , IMM);
        //BEQZ(R1,IMM);
        //JR(R1, R2);
        //SRC(R1,IMM);
        //SLC(R1,IMM);
        System.out.println(Registers.getR()[R1]);
        //System.out.println(Registers.getPc());
        //System.out.println(R1);
        // System.out.println(DataMemory.getMemory()[ADDRESS]);
    }

}


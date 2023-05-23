public class InstructionSetArchitecture {

    public static void ADD(int R1,int R2){
        R1= R1+R2;
    }
    public static void SUB(int R1,int R2){
        R1= R1-R2;
    }
    public static void MUL(int R1,int R2){
        R1=R1*R2;
    }
    public static void AND(int R1, int R2){
        R1=R1&R2;
    }
    public static void OR(int R1,int R2){
        R1=R1|R2;
    }
    public static void JR(int R1, int R2){
       //Registers.setPc(R1||R2);
    }
    public static void main (String args[]){
        int R1=33;
        int R2=35;
        AND(R1,R2);
        System.out.println(R1);
    }

}


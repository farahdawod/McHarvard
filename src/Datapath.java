public class Datapath {
    private int opcode;
    private static int[] instructionArray;

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public static int[] getInstructionArray() {
        return instructionArray;
    }

    public static void setInstructionArray(int[] instructionArray) {
        instructionArray = instructionArray;
    }



    public int Fetch(){
        int instruction = InstructionMemory.getMemory()[Registers.getPc()];
        Registers.incrementPC();
        return instruction;
    }

    public int[] Decode(int instruction){
        opcode = instruction & 0b1111000000000000;
        int address1 = instruction & 0b0000111111000000;
        int address2 = instruction & 0b0000000000111111;

        instructionArray = new int[] {opcode, 0, 0, 0, 0, 0};
        //opcode, address1, value1, address2, value2, immediate
        // 0    , 1       , 2     , 3       , 4     , 5


        switch(opcode){
            case 0,1,2,5,6,7:
                instructionArray[2] = Registers.getR()[address1]; //v1
                instructionArray[4] = Registers.getR()[address2]; //v2
                break;


            case 3,4,8,9:
                instructionArray[1] = address1;
                instructionArray[5] = address2;
                break;

            case 10:
                instructionArray[1] = address1;
                instructionArray[4] = DataMemory.getMemory()[address2];
                break;

            case 11:
                instructionArray[2] = Registers.getR()[address1];
                instructionArray[3] = address2;

            default: break;
        }

        return instructionArray;
    }


    public void bta3(){
        int instruction = Fetch();
        int array[] = Decode(instruction);
        Execute(array);

    }
    public void Execute(int[] array){
        switch (opcode){
            case 0:
                InstructionSetArchitecture.ADD(array[1],array[4]);
                break;
            case 1:
                InstructionSetArchitecture.SUB(array[1],array[4]);
                break;
            case 2:
                InstructionSetArchitecture.MUL(array[1],array[4]);
                break;
            case 3:
                InstructionSetArchitecture.LDI(array[1],array[5]);
                break;
            case 4:
                InstructionSetArchitecture.BEQZ(array[2],array[5]);
                break;
            case 5:
                InstructionSetArchitecture.AND(array[1],array[4]);
                break;
            case 6:
                InstructionSetArchitecture.OR(array[1],array[4]);
                break;
            case 7:
                InstructionSetArchitecture.JR(array[2],array[4]);
                break;
            case 8:
                InstructionSetArchitecture.SLC(array[1],array[5]);
                break;
            case 9:
                InstructionSetArchitecture.SRC(array[1],array[5]);
                break;
            case 10:
                InstructionSetArchitecture.LB(array[1],array[4]);
                break;
            case 11:
                InstructionSetArchitecture.SB(array[2],array[3]);
                break;
        }
    }
    public void executepipeline(int numofins, int numofstages) {
        int clkcycles = 3 + ((numofins - 1) * numofstages);
        for (int cycle = 1; cycle <= clkcycles; cycle++) {

        }


    }


}






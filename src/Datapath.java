public class Datapath {


    public int Fetch(){
        int instruction = InstructionMemory.getMemory()[Registers.getPc()];
        Registers.incrementPC();
        return instruction;
    }

    public int[] Decode(int instruction){
        int opcode = instruction & 0b1111000000000000;
        int address1 = instruction & 0b0000111111000000;
        int address2 = instruction & 0b0000000000111111;

        int[] instructionArray = new int[] {opcode, 0, 0, 0, 0, 0};
        //opcode, address1, value1, address2, value2, immediate
        // 0    , 1       , 2     , 3       , 4     , 5


        switch(opcode){
            case 0,1,2,5,6:
                instructionArray[2] = Registers.getR()[address1];
                instructionArray[4] = Registers.getR()[address2];
                break;

            case 7:
                instructionArray[1] = address1;
                instructionArray[3] = address2;
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

    public void Execute(){

    }
}


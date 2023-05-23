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
        //int imm = 0;

        int[] instructionArray = new int[] {opcode, 0, 0, 0};

        instructionArray[1] = Registers.getR()[address1];

        if(opcode < 3 || (opcode > 4 && opcode < 8)){ //if R
            instructionArray[2] = Registers.getR()[address2];
        }
        else if(opcode > 9) { //I load and store
            instructionArray[3] = 1;
        }
        return instructionArray;
    }

    public void Execute(){

    }
}


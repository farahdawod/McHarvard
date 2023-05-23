public class Datapath {



    public int Fetch(){
        int instruction = InstructionMemory.getMemory()[Registers.getPc()];
        Registers.incrementPC();
        return instruction;
    }

    public void Decode(int instruction){
        int opcode = instruction & 0b1111000000000000;
        int R1 = instruction & 0b0000111111000000;
        int R2 = 0;
        int immediate = 0;


    }
}

public class Datapath {



    public int Fetch(){
        int instruction = InstructionMemory.getMemory()[Registers.getPc()];
        Registers.incrementPC();
        return instruction;
    }
}

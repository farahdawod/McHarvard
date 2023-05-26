public class InstructionMemory {
    private static short[] memory = new short[1024]; //each row id 16 bits (2 bytes)

    public static short[] getMemory() {
        return memory;
    }

    public static void setMemory(int address, short value) {
        InstructionMemory.memory[address] = value;
    }

    public static void printMem(){
        System.out.println("Content of each instruction memory is:");
        for(int i = 0; i < 1024; i++){
            System.out.println("Address: " + i + "| Value: " + memory[i]);
        }
        System.out.println("-------------------------------");
    }
}

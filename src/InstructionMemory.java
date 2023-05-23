public class InstructionMemory {
    private static short[] memory = new short[1024]; //each row id 16 bits (2 bytes)

    public static short[] getMemory() {
        return memory;
    }

    public static void setMemory(short[] memory) {
        InstructionMemory.memory = memory;
    }
}

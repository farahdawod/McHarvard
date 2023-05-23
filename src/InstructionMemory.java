public class InstructionMemory {
    private static int[] memory = new int[1024]; //each row id 16 bits (2 bytes)

    public static int[] getMemory() {
        return memory;
    }

    public static void setMemory(int[] memory) {
        InstructionMemory.memory = memory;
    }
}

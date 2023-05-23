public class DataMemory {
    private static int[] memory = new int[2048]; //each row is 8 bits (1 int)

    public static int[] getMemory() {
        return memory;
    }

    public static void setMemory(int[] memory) {
        DataMemory.memory = memory;
    }
}
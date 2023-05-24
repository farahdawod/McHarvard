public class DataMemory {
    private static byte[] memory = new byte[2048]; //each row is 8 bits (1 byte)

    public static byte[] getMemory() {
        return memory;
    }

    public static void setMemory(int address, byte value) {
        DataMemory.memory[address] = value;
    }
}
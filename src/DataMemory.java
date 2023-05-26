public class DataMemory {
    private static byte[] memory = new byte[2048]; //each row is 8 bits (1 byte)

    public static byte[] getMemory() {
        return memory;
    }

    public static void setMemory(int address, byte value) {
        DataMemory.memory[address] = value;
    }

    public static void printMem(){
        System.out.println("Content of each data memory is:");
        for(int i = 0; i < 2048; i++){
            System.out.println("Address: " + i + "| Value: " + memory[i]);
        }
        System.out.println("-------------------------------");
    }
}
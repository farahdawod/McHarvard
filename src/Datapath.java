import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Datapath {

    public static void readProgram(){
        FileReader fileReader = null;
        BufferedReader reader = null;
        String line = "";

        try {
            fileReader = new FileReader("program1.txt");
            reader = new BufferedReader(fileReader);
            line = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(line != null){
            String instruction = "";
            String[] values = line.split(" ");
            String[] address1 = values[1].split("R");
            String[] add2;
            String temp;
            switch (values[0]){
                case "ADD":
                    instruction += "0000";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    add2 = values[2].split("R");
                    temp = Integer.toBinaryString((Integer.parseInt(add2[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "SUB":
                    instruction += "0001";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    add2 = values[2].split("R");
                    temp = Integer.toBinaryString((Integer.parseInt(add2[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "MUL":
                    instruction += "0010";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    add2 = values[2].split("R");
                    temp = Integer.toBinaryString((Integer.parseInt(add2[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "LDI":
                    instruction += "0011";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    temp = String.format("%6s",Integer.toBinaryString(Integer.parseInt(values[2]) + 64)).replace(' ','0');
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "BEQZ":
                    instruction += "0100";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    temp = Integer.toBinaryString(Integer.parseInt(values[2]) + 64);
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "AND":
                    instruction += "0101";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    add2 = values[2].split("R");
                    temp = Integer.toBinaryString((Integer.parseInt(add2[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "OR":
                    instruction += "0110";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    add2 = values[2].split("R");
                    temp = Integer.toBinaryString((Integer.parseInt(add2[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "JR":
                    instruction += "0111";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    add2 = values[2].split("R");
                    temp = Integer.toBinaryString((Integer.parseInt(add2[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "SLC":
                    instruction += "1000";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    temp = Integer.toBinaryString(Integer.parseInt(values[2]) + 64);
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "SRC":
                    instruction += "1001";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    temp = Integer.toBinaryString(Integer.parseInt(values[2]) + 64);
                    instruction += temp.substring(temp.length()-6,temp.length());
                    break;
                case "LB":
                    instruction += "1010";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    //temp = Integer.parseInt(values[2], 2) + "";
                    instruction += values[2];
                    break;
                case "SB":
                    instruction += "1011";
                    temp = Integer.toBinaryString((Integer.parseInt(address1[1]) + 64 ));
                    instruction += temp.substring(temp.length()-6,temp.length());
                    instruction += values[2];
                    break;
                default: break;
            }

            for(int i = 0; i < 1024; i++){
                if(InstructionMemory.getMemory()[i] == 0){
                    //int test = Integer.parseInt(instruction,2);
                    InstructionMemory.setMemory(i, (short) Integer.parseInt(instruction,2));
                    break;
                }
            }

            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            fileReader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public short Fetch(){
        short instruction = InstructionMemory.getMemory()[Registers.getPc()];
        System.out.println("Program Counter: " + Registers.getPc() + "\n Used to fetch the instruction from the instruction memory");
        Registers.incrementPC();
        System.out.println("Instruction: " + String.format("%16s",Integer.toBinaryString(instruction & 0xFFFF)).replace(' ','0') + " is being fetched");
        System.out.println("PC now: " + Registers.getPc() + "\n Fetching is finished");
        return instruction;
    }

    public int[] Decode(short instruction){
        int opcode = (instruction>>>12) & 0b1111;
        int address1 = (instruction>>>6) & 0b0000111111;
        int address2 = instruction & 0b0000000000111111;
        if (address2>31) address2=address2-64;

        System.out.println("Instruction: " + String.format("%16s",Integer.toBinaryString(instruction & 0xFFFF)).replace(' ','0') + " is being decoded");

        switch (opcode){
            case 0:
                System.out.println("Opcode: ADD");
                break;
            case 1:
                System.out.println("Opcode: SUB");
                break;
            case 2:
                System.out.println("Opcode: MUL");
                break;
            case 3:
                System.out.println("Opcode: LDI");
                break;
            case 4:
                System.out.println("Opcode: BEQZ");
                break;
            case 5:
                System.out.println("Opcode: AND");
                break;
            case 6:
                System.out.println("Opcode: OR");
                break;
            case 7:
                System.out.println("Opcode: JR");
                break;
            case 8:
                System.out.println("Opcode: SLC");
                break;
            case 9:
                System.out.println("Opcode: SRC");
                break;
            case 10:
                System.out.println("Opcode: LB");
                break;
            case 11:
                System.out.println("Opcode: SB");
                break;
            default: break;
        }

        int[] instructionArray = new int[] {opcode, 0, 0, 0, 0, 0, instruction};
        //opcode, address1, value1, address2, value2, immediate, instruction
        // 0    , 1       , 2     , 3       , 4     , 5        , 6

        instructionArray[1] = address1;
        instructionArray[3] = address2;


        switch(opcode){
            case 0,1,2,5,6,7:
                instructionArray[2] = Registers.getR()[address1]; //v1
                instructionArray[4] = Registers.getR()[address2]; //v2
                System.out.println("Value 1 from Register " + address1 + ": " + instructionArray[2]);
                System.out.println("Value 2 from Register " + address2 + ": " + instructionArray[4]);
                break;

            case 3,4,8,9:
                //instructionArray[1] = address1;
                instructionArray[5] = address2;
                System.out.println("Register Address: " + address1);
                System.out.println("Immediate value: " + address2);
                break;

            case 10:
                //instructionArray[1] = address1;
                instructionArray[4] = DataMemory.getMemory()[address2];
                System.out.println("Destination Register Address : " + address1);
                System.out.println("Memory address to get value from:  " + address2 );
                break;

            case 11:
                instructionArray[2] = Registers.getR()[address1];
                //instructionArray[3] = address2;
                System.out.println("Value 1 from register " + address1 + ": " + instructionArray[2]);
                System.out.println("Memory address 2: " + address2);

            default: break;
        }

        System.out.println("Decoding finished");

        return instructionArray;
    }

    public void Execute(int[] array){
        //opcode, address1, value1, address2, value2, immediate, instruction
        // 0    , 1       , 2     , 3       , 4     , 5        , 6
        switch (array[0]) {
            case 0 -> {
                System.out.println("Register at address " + array[1] + " contained the value " + array[2]);
                InstructionSetArchitecture.ADD(array[1], array[4]);
                System.out.println(array[4] + " which is the value at register address " + array[3] + " was added to the value at register address " + array[1]
                        + " and the new value is " + Registers.getR()[array[1]]);
            }
            case 1 -> {
                System.out.println("Register at address " + array[1] + " contained the value " + array[2]);
                InstructionSetArchitecture.SUB(array[1], array[4]);
                System.out.println(array[4] + " which is the value at register address " + array[3] + " was subtracted from the value at register address " + array[1]
                        + " and the new value is " + Registers.getR()[array[1]]);
            }
            case 2 -> {
                System.out.println("Register at address " + array[1] + "contained the value " + array[2]);
                InstructionSetArchitecture.MUL(array[1], array[4]);
                System.out.println(array[4] + " which is the value at register address " + array[3] + " was multiplied to the value at register address " + array[1]
                        + " and the new value is " + Registers.getR()[array[1]]);
            }
            case 3 -> {
                System.out.println("Register at address " + array[1] + " contained the value " + array[2]);
                InstructionSetArchitecture.LDI(array[1], (byte) array[5]);
                System.out.println(array[5] + " was loaded to the register at register address " + array[1]
                        + " and the new value is " + array[5]);
            }
            case 4 -> {
                System.out.println("Register at address " + array[1] + " contained the value " + array[2]);
                InstructionSetArchitecture.BEQZ(array[2], (byte) array[5]);
            }
            case 5 -> {
                System.out.println("Register at address " + array[1] + " contained the value " + array[2]);
                InstructionSetArchitecture.AND(array[1], array[4]);
                System.out.println(array[4] + "was ANDed to the value at register address " + array[1]
                        + "and the new value is " + array[2]);
            }
            case 6 -> {
                System.out.println("Register at address " + array[1] + " contained the value " + array[2]);
                InstructionSetArchitecture.OR(array[1], array[4]);
                System.out.println(array[4] + "was ORed to the value at register address " + array[1]
                        + "and the new value is " + array[2]);
            }
            case 7 -> {
                System.out.println("The PC Register contained the value " + Registers.getPc());
                InstructionSetArchitecture.JR(array[2], array[4]);
                System.out.println("The value " + array[2] + " at register address " + array[1] + " was concatenated to the value "
                        + array[4] + " at register address " + array[3] + " and stored in the PC register which is now: " + Registers.getPc());
            }
            case 8 -> {
                System.out.println("The value at register address " + array[1] + " is " + array[2]);
                InstructionSetArchitecture.SLC(array[1], (byte) array[5]);
                System.out.println("The value at register address " + array[1] + " is now " + array[2] +
                        " after circularly shifting it to the left with the immediate value " + array[5]);
            }
            case 9 -> {
                System.out.println("The value at register address " + array[1] + " is " + array[2]);
                InstructionSetArchitecture.SRC(array[1], (byte) array[5]);
                System.out.println("The value at register address " + array[1] + " is now " + array[2] +
                        " after circularly shifting it to the right with the immediate value " + array[5]);
            }
            case 10 -> {
                System.out.println("Value " + DataMemory.getMemory()[array[3]] + " is stored in MEMORY[" + array[3] + "]");
                InstructionSetArchitecture.LB(array[1], (byte) array[4]);
                System.out.println("Value " + array[4] + " was loaded from MEMORY[" + array[3] + "] to register address [" + array[1] + "]");
            }
            case 11 -> {
                System.out.println("Value " + DataMemory.getMemory()[array[3]] + " is stored in MEMORY[" + array[3] + "]");
                InstructionSetArchitecture.SB((byte) array[2], array[3]);
                System.out.println("Value " + array[2] + " was stored from register address [" + array[1] + "] to MEMORY[" + array[3] + "]");
            }
        }

        System.out.println("Instruction: " + String.format("%16s",Integer.toBinaryString(array[6] & 0xFFFF)).replace(' ','0') + " executed");
    }

    public void executepipeline(int numofins) {
        int clkcycles = 3 + ((numofins - 1) * 1);
        short instruction = 0;
        int[] array = new int[6];

        for(int i = 0; i < clkcycles; i++) {
            System.out.println("Clock cycle: " + (i+1));
            if(i == 0){
                instruction = Fetch();
            }
            else if(i == 1){
                array = Decode(instruction);
                instruction = Fetch();
            }
            else if(i == clkcycles - 2){
                if (i==j) Execute(array);
                else {
                    j--;
                }
                array = Decode(instruction);
            }
            else if(i == clkcycles - 1){
                if (i==j) Execute(array);
                else {
                    j--;
                }
            }
            else{
                if (i==j) Execute(array);
                else {
                    j--;
                }
                array = Decode(instruction);
                instruction = Fetch();
            }
            if (i==j) j=Registers.getPc();
        }
        Registers.printR();
        DataMemory.printMem();
        InstructionMemory.printMem();
    }

    public static void main(String[] args) {
        Datapath DP = new Datapath();
        Datapath.readProgram();
        DP.executepipeline(10);
    }

}









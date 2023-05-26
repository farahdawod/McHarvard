public class Datapath {


    public short Fetch(){
        short instruction = InstructionMemory.getMemory()[Registers.getPc()];
        System.out.println("Program Counter: " + Registers.getPc() + "\n Used to fetch the instruction from the instruction memory");
        Registers.incrementPC();
        System.out.println("Instruction: " + instruction + " is being fetched");
        System.out.println("PC now: " + Registers.getPc() + "\n Fetching is finished");
        return instruction;
    }

    public int[] Decode(short instruction){
        int opcode = instruction & 0b1111000000000000;
        int address1 = instruction & 0b0000111111000000;
        int address2 = instruction & 0b0000000000111111;

        System.out.println("Instruction: " + instruction + " is being decoded");

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
                System.out.println("Register Address 1: " + address1);
                System.out.println("Immediate value: " + address2);
                break;

            case 10:
                //instructionArray[1] = address1;
                instructionArray[4] = DataMemory.getMemory()[address2];
                System.out.println("Register Address 1: " + address1);
                System.out.println("Value 2 from memory address " + address2 + ": " + instructionArray[4]);
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
        switch (array[0]){
            case 0:
                System.out.println("Register at address "+ array[1]+ "contained the value " + array[2]);
                InstructionSetArchitecture.ADD(array[1],array[4]);
                System.out.println(array[4] + "was added to the value at register address " + array[1]
                                + "and the new value is " + array[2]);
                break;
            case 1:
                System.out.println("Register at address "+ array[1]+ "contained the value " + array[2]);
                InstructionSetArchitecture.SUB(array[1],array[4]);
                System.out.println(array[4] + "was subtracted from the value at register address " + array[1]
                        + "and the new value is " + array[2]);
                break;
            case 2:
                System.out.println("Register at address "+ array[1]+ "contained the value " + array[2]);
                InstructionSetArchitecture.MUL(array[1],array[4]);
                System.out.println(array[4] + "was multiplied to the value at register address " + array[1]
                        + "and the new value is " + array[2]);
                break;
            case 3:
                System.out.println("Register at address "+ array[1]+ "contained the value " + array[2]);
                InstructionSetArchitecture.LDI(array[1],(byte) array[5]);
                System.out.println(array[4] + "was loaded to the register at register address " + array[1]
                        + "and the new value is " + array[2]);
                break;
            case 4:
                System.out.println("Register at address "+ array[1]+ "contained the value " + array[2]);
                InstructionSetArchitecture.BEQZ(array[2],(byte) array[5]);
                break;
            case 5:
                System.out.println("Register at address "+ array[1]+ "contained the value " + array[2]);
                InstructionSetArchitecture.AND(array[1],array[4]);
                System.out.println(array[4] + "was ANDed to the value at register address " + array[1]
                        + "and the new value is " + array[2]);
                break;
            case 6:
                System.out.println("Register at address "+ array[1]+ "contained the value " + array[2]);
                InstructionSetArchitecture.OR(array[1],array[4]);
                System.out.println(array[4] + "was ORed to the value at register address " + array[1]
                    + "and the new value is " + array[2]);
                break;
            case 7:
                System.out.println("The PC Register contained the value "+ Registers.getPc());
                InstructionSetArchitecture.JR(array[2],array[4]);
                System.out.println("The value" +array[2]+" at register address "+ array[1]+ "was concatenated to the value "
                +array[4]+ "at register address "+ array[3] + "and stored in the PC register which is now: " + Registers.getPc());
                break;
            case 8:
                InstructionSetArchitecture.SLC(array[1],(byte) array[5]);
                break;
            case 9:
                InstructionSetArchitecture.SRC(array[1],(byte) array[5]);
                break;
            case 10:
                InstructionSetArchitecture.LB(array[1], (byte) array[4]);
                break;
            case 11:
                InstructionSetArchitecture.SB((byte) array[2],array[3]);
                break;
        }

        System.out.println("Instruction: " + array[6] + " is being executed");
    }

    public void executepipeline(int numofins) {
        int clkcycles = 3 + ((numofins - 1) * 3);
        short instruction = 0;
        int[] array = new int[6];

        for(int i = 0; i < clkcycles; i++) {
            System.out.println("Clock cycle: " + i+1);

            if(i == 0){
                instruction = Fetch();
            }
            else if(i == 1){
                array = Decode(instruction);
                instruction = Fetch();
            }
            else if(i == clkcycles - 2){
                Execute(array);
                array = Decode(instruction);
            }
            else if(i == clkcycles - 1){
                Execute(array);
            }
            else{
                Execute(array);
                array = Decode(instruction);
                instruction = Fetch();
            }
        }
        Registers.printR();
        DataMemory.printMem();
        InstructionMemory.printMem();
    }

}









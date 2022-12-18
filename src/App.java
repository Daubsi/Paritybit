import java.util.Random;

public class App {
    public static Random rand = new Random();

    public static int[][] createSampleData(int nBytes){
        int[][] data = new int[nBytes][8];
        
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length-1; j++){
                data[i][j] = rand.nextInt(2);
            }
        }
        return data;
    }

    public static void printData(int[][] data, String headline, String Spacer){
        System.out.println(headline);
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                System.out.print(data[i][j] + Spacer);
            }
            System.out.println("");
        }
    }

    public static int[][] addParityBit(int[][] data){
        for(int i = 0; i < data.length; i++){
            int counter = 0;
            for(int j = 0; j < data[i].length; j++){
                if(data[i][j] == 1){
                    counter++;
                }
            }
            if(counter % 2 == 1){
                data[i][7] = 1;
            }
        }
        return data;
    }

    public static int[][] addWrongBit(int[][] data, int nWrongBits){
        int[][] wrongBits = new int[nWrongBits][2];
        for(int i = 0; i < nWrongBits; i++){
            int x = rand.nextInt(data.length);
            int y = rand.nextInt(8);
            if(data[x][y] == 1){
                data[x][y] = 0;
            }else{
                data[x][y] = 1;
            }
            wrongBits[i][0] = x;
            wrongBits[i][1] = y;
        }
        printData(wrongBits, "Changed Bits", " ");
        return data;
    }

    public static int DetectWrongByte(int[][] data, int nWrongBits){
        int wrongByte = -1;
        for(int i = 0; i < data.length; i++){
            int counter = 0;
            for(int j = 0; j < 8; j++){
                if(data[i][j] == 1){
                    counter++;
                }
            }
            if(counter % 2 == 1){
                wrongByte = i;
            }
        }
        return wrongByte;
    }

    public static void main(String[] args) throws Exception {
        int[][] data = createSampleData(10);
        printData(data, "Data without Parity Bit", "");
        addParityBit(data);
        printData(data, "Data with Parity Bit", "");
        addWrongBit(data, 1);
        printData(data, "Data with Mistake", "");
        System.out.print("Wrong Bit detected in Byte: " + DetectWrongByte(data, 1));
    }
}

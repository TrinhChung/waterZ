import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
    public static int UCLN(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a == 0 || b == 0) {
            return a + b;
        } else {
            return UCLN(b, a % b);
        }
    }
    public static int fullWater(int x,int y,int stateX,int stateY,int z, int step) {
        if (stateX == z || stateY == z) {
            return step;
        } else if (stateX == x) {//L1
            return fullWater(x, y, 0, stateY, z, step + 1);
        } else if (stateY == 0) {//L2
            return  fullWater(x,y,stateX,y, z,step + 1);
        } else if (stateX < x && stateY > 0) {//L3
            if (stateX + stateY <= x) {
                return fullWater(x ,y,stateX + stateY, 0,z, step + 1);
            } else {
                return fullWater(x,y, x, stateY -(x- stateX), z, step + 1);
            }
        }
        return -1;
    }

    public static void readFileTxt(String fileContent) {
        Scanner r = new Scanner(fileContent).useDelimiter("\\A");
        String s = r.nextLine().trim().replaceAll("\\s+", " ");
        System.out.println(s);
        while (r.hasNextLine()) {
            s = r.nextLine().trim().replaceAll("\\s+", " ");
            System.out.println(s);
        }
    }

    public static int water(int x, int y, int z) {
        boolean check = z % UCLN(x,y) == 0;
        if (z > x && z > y) {
            check = false;
        }
        if (check) {
            if (x > y) {
                int m = x;
                x= y;
                y = m;
            }
            return fullWater(x,y,0,0,z,0);
        } else {
            return -1;
        }
    }
    public static void main(String[] args) {

        try {
            File myFile = new File("src/input.txt");
            Scanner r = new Scanner(myFile).useDelimiter("\\A");
            String[] n = r.nextLine().split(" ", 3);
            int test  = Integer.parseInt(n[0]);
            int i = 1;
            while (r.hasNextLine() && test >0) {
                String[] data = r.nextLine().split(" ", 3);

                System.out.println(water(Integer. parseInt(data[0]), Integer. parseInt(data[1]),Integer. parseInt(data[2])));
//                test--;
            }
            r.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

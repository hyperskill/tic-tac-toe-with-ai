import java.util.*;

public class Main {
    public static void main(String[] args) {

        Random r = new Random();
        while (true){
            System.out.println(r.nextInt(3));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }




    }

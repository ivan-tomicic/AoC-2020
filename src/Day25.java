import java.util.*;

public class Day25 {

    static long mod = 20201227;
    static long cardPublicKey = 16616892;
    static long doorPublicKey = 14505727;
    static int subjectNumber = 7;

    public static void main(String[] args) {

        long doorLoops = calculateLoopSize(doorPublicKey);
        long cardLoops = calculateLoopSize(cardPublicKey);

        long encriptionKey = 1;
        for(int i = 0; i < doorLoops; i++) {
            encriptionKey *= cardPublicKey;
            encriptionKey %= mod;
        }

        System.out.println("Encription key is: " + encriptionKey);

        encriptionKey = 1;
        for(int i = 0; i < cardLoops; i++) {
            encriptionKey *= doorPublicKey;
            encriptionKey %= mod;
        }

        System.out.println("Encription key is: " + encriptionKey);

        System.out.println("Door loops: " + doorLoops + " card loops: " + cardLoops);


    }

    public static long calculateLoopSize(long publicKey) {
        long loops = 0;
        long key = 1;
        while(true) {
            if(key == publicKey) break;
            key *= subjectNumber;
            key %= mod;
            loops++;
        }
        return loops;
    }
}

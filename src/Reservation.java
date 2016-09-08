import java.io.File;
import java.util.Scanner;

/**
 * Created by Jennica on 07/09/2016.
 */
public class Reservation {
    
    public static void main(String[] args) {
        int N = 100;
        int answer;
        int[][] map;

        try {
            File file = new File("resources/sample.txt");
            Scanner scanner = new Scanner(file);

            int testCasesNo = scanner.nextInt();

            for ( int no = 1; no <= testCasesNo; no++ ) {
                int n = scanner.nextInt();
                int k = scanner.nextInt();

//                System.out.println(n + " " + k);

                map = new int[n][n];

                for ( int x = 0; x < n; x++ ) {
                    for ( int y = 0; y < n; y++ ) {
                        map[x][y] = scanner.nextInt();

//                        System.out.print(map[x][y]);
                    }
//                    System.out.println();
                }

                /**
                 * Check conditions
                 */
                if ( n >= 5 && n <= 99 && n % 2 != 0 && k >= 1 && k <= n ) {
                    // Get the center of the seats
                    int x = n / 2;
                    int y = n / 2;

                    System.out.println("center: (" + x + "," + y + ")");

                    int[] answers = new int[N];
                    int count = 0;
                    // Check by row to find the available start locations
                    for ( int y1 = 0; y1 < n; y1++ ) {
                        for ( int x1 = 0; x1 < n; x1++ ) {
                            if ( getAvailability(map, y1, x1, n, k) ) {
//                                System.out.println("Available: " + "(" + x1 + "," + y1 + ") = "
//                                        + getMinimumDistance(x, y, x1, y1, k));
                                answers[count] = getMinimumDistance(x, y, x1, y1, k);
                                count++;
                            }
                        }
                    }

                    if ( count > 0 ) {
                        answer = answers[0];

                        for ( int b = 1; b < count; b++ ) {
                            if ( answer > answers[b] )
                                answer = answers[b];
                        }
                    } else {
                        answer = -1;
                    }

                    System.out.println("case #" + no + " " + answer);
                }

            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private static boolean getAvailability(int[][] map, int y1, int x1, int n, int k) {
        if ( x1 + k > n )
            return false;

        // Check for the first seat availability
        if(map[y1][x1] == 0) {
            return false;
        }

        // Check for next consecutive seat in a row
        for ( int col = 1; col < k; col++ ) {
            if ( map[y1][x1+col] == 0 )
                return false;
        }

        return true;
    }

    private static int getMinimumDistance(int x, int y, int x1, int y1, int k) {
        int minimumSum = 0;
        for ( int i = 0; i < k; i++ ) {
            minimumSum += (x - (x1 + i)) * (x - (x1 + i)) + (y - y1) * (y - y1);
        }

        return minimumSum;
    }
}

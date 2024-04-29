import java.util.*;

public class HeightMap {
    public static void main(String[] args){
        DiamondSquare(1, 0);
    }

    public static void DiamondSquare(int n, double stdDev){
        //Create array of valid size
        int size = (int)Math.pow(2, n) + 1;
        double[][] heightMapArray = new double[size][size];

        Random r = new Random();

        //Initialize corner values
        heightMapArray[0][0] = r.nextGaussian();
        heightMapArray[0][size - 1] = 2.0f;
        heightMapArray[size - 1][0] = 3.0f;
        heightMapArray[size - 1][size - 1] = 4.0f;

        //Definition of step
        int step = (int)Math.floor(size/2);

        //Iterate over n
        for(int it = 0; it < n; it++){
            //Diamond - Square Steps
            Diamond(heightMapArray, step);
            Square(heightMapArray, step);
            //After every iteration, the step halves
            step /= 2;

            //Printing Utils
            PrintingUtilities.PrintArray(heightMapArray);
            System.out.println();
            try{Thread.sleep(2000);} catch (Exception e) {}
        }
    }

    public static void Diamond(double[][] arr, int step){
        //iterate over top-left corners
        for(int i = 0; i < arr.length - 1; i+= 2 * step){
            for(int j = 0; j < arr[0].length - 1; j += 2 * step){
                //find center of square
                arr[i + step][j + step] = (
                    arr[i][j] + arr[i + step * 2][j] + 
                    arr[i][j + step * 2] + arr[i + step * 2][j + step * 2]) / 4;
            }
        }
    }

    public static void Square(double[][] arr, int step){
        //iterate over the diamond centers
        for(int i = 0; i < arr.length; i+= step){
            //alternating between starting on index 0 or index step
            int alt_start = (i + step) % (2 * step);
            for(int j = alt_start; j < arr[0].length; j += 2 * step){
                //calculate the average based on the available diamond vertices
                double sum = 0;
                double num_el = 0;
                if(i - step >= 0){sum += arr[i - step][j]; num_el += 1;}
                if(i + step < arr.length){sum += arr[i + step][j]; num_el += 1;}
                if(j - step >= 0){sum += arr[i][j - step]; num_el += 1;}
                if(j + step < arr.length){sum += arr[i][j + step]; num_el += 1;}

                //assign diamond center to calculated average
                arr[i][j] = sum / num_el;
            }
        }
    }

}

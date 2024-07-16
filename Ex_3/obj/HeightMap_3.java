import java.util.Random;

public class HeightMap_3 {

    public static double[][] DiamondSquare(int n, double roughness){
        Random random = new Random();

        //Create array of valid size
        int size = (int)Math.pow(2, n) + 1;
        double[][] heightMapArray = new double[size][size];

        //Initialize corner values
        heightMapArray[0][0] = random.nextGaussian();
        heightMapArray[0][size - 1] = random.nextGaussian();
        heightMapArray[size - 1][0] = random.nextGaussian();
        heightMapArray[size - 1][size - 1] = random.nextGaussian();

        //Definition of step
        int step = (int)Math.floor(size/2);
        int iterations = 0;

        //Iterate until step is 1
        while(step >= 1){
            //Diamond - Square Steps
            Diamond(heightMapArray, step, roughness, iterations);
            Square(heightMapArray, step, roughness, iterations);

            //After every iteration, the step halves
            step /= 2;
            iterations++;
        }

        return heightMapArray;
    }

    public static void Diamond(double[][] arr, int step, double roughness, int iterations){
        Random random = new Random();
        double scale = Math.pow(roughness, iterations);
        //iterate over top-left corners
        for(int i = 0; i < arr.length - 1; i+= 2 * step){
            for(int j = 0; j < arr[0].length - 1; j += 2 * step){
                //find center of square
                arr[i + step][j + step] = (
                    arr[i][j] + 
                    arr[i + step * 2][j] + 
                    arr[i][j + step * 2] + 
                    arr[i + step * 2][j + step * 2]) 
                    / 4;
                
                arr[i + step][j + step] += random.nextGaussian() * scale;
            }
        }
    }

    public static void Square(double[][] arr, int step, double roughness, int iterations){
        Random random = new Random();
        double scale = Math.pow(roughness, iterations);
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
                arr[i][j] = (sum / num_el) + random.nextGaussian() * scale;
            }
        }
    }

}

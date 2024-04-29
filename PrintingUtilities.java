public class PrintingUtilities {
    
    public static void PrintArray(float[][] arr){
        for(int i = 0 ; i < arr.length; i++){
            for(int j = 0 ; j < arr[0].length; j++){
                System.out.print(String.format("%.4f", arr[i][j]));
                if(j < arr.length - 1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

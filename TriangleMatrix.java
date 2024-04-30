import java.util.Arrays;

public class TriangleMatrix {

    public static void main (String[] args){
        double[][] hMap = HeightMap.DiamondSquare(2, 2, 5);
        getMatrix(hMap);
    }

    public static int[][] getMatrix(double[][] hMap){
        //Number of Triangles is two times the number of 2*2 squares
        int size = 2 *(hMap.length-1) * (hMap.length-1);

        //Matrix with 3 vertex indexes per triangle
        int[][] triMatrix = new int[size][3];
        int triCounter = 0;

        for(int i = 0; i < hMap.length - 1; i++){
            for(int j = 0; j < hMap.length - 1; j++){
                int topLeftVertexIndex = i * hMap.length + j;

                int[] vertexMatrixA = {
                    topLeftVertexIndex, 
                    topLeftVertexIndex + 1, 
                    topLeftVertexIndex + hMap.length
                };

                int[] vertexMatrixB = {
                    topLeftVertexIndex + 1, 
                    topLeftVertexIndex + hMap.length, 
                    topLeftVertexIndex + hMap.length + 1
                };


                triMatrix[triCounter] = vertexMatrixA;
                triMatrix[triCounter + 1] = vertexMatrixB;

                triCounter += 2;

            }
        }

        System.out.println(Arrays.deepToString(triMatrix));
        return triMatrix;
    }
}

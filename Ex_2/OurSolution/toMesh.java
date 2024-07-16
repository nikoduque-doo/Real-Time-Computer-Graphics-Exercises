public class toMesh {

    public static record Mesh(double[][] vertices, int[][] faces){};

    public static Mesh getMesh(double[][] arr, double scaleX, double scaleY, double scaleZ){
        int[][] faces = getTriangleMatrix(arr);
        double[][] vertices = getVertexMatrix(arr, scaleX, scaleY, scaleZ);

        return new Mesh(vertices, faces);
    }

    public static int[][] getTriangleMatrix(double[][] hMap){
        //Number of Triangles is two times the number of 2*2 squares
        int size = 2 *(hMap.length-1) * (hMap.length-1);

        //Matrix with 3 vertex indexes per triangle
        int[][] triMatrix = new int[size][3];
        int triCounter = 0;

        for(int i = 0; i < hMap.length - 1; i++){
            for(int j = 0; j < hMap.length - 1; j++){
                int topLeftVertexIndex = i * hMap.length + j + 1;

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

        return triMatrix;
    }

    public static double[][] getVertexMatrix(double[][] arr, double scaleX, double scaleY, double scaleZ){
        
        double[][] vertexMatrix = new double[arr.length * arr.length][3];
        int vertexCount = 0;
        double scale = arr.length;

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                double[] vertexCoordinates = {
                    scaleX / scale * j,
                    scaleY / scale * i,
                    scaleZ * arr[i][j]
                };

                vertexMatrix[vertexCount] = vertexCoordinates;
                vertexCount++;
            }
        }

        return vertexMatrix;
    }
}

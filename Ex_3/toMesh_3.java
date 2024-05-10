public class toMesh_3 {

    public static record Mesh(double[][] vertices, int[][] faces, double[][] normals, double[][] textureCoordinates){};

    public static Mesh getMesh(double[][] arr, double scaleX, double scaleY, double scaleZ){
        int[][] faces = getTriangleMatrix(arr);
        // double[][] vertices = getVertexMatrix(arr, scaleX, scaleY, scaleZ);
        // double[][] normals = getNormalMatrix(arr, scaleX, scaleY);
        // double[][] textureCoordinates = getTextureCoordinates(arr, scaleX, scaleY);
        double[][] vertexMatrix = new double[arr.length * arr.length][3];
        double[][] normals = new double[arr.length * arr.length][3];
        double[][] textCoord = new double[arr.length * arr.length][2];

        int vertexCount = 0;
        double scale = arr.length;

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                //get Vertex Coordinates
                double[] vertexCoordinates = {
                    scaleX / scale * j,
                    scaleY / scale * i,
                    scaleZ * arr[i][j]
                };

                vertexMatrix[vertexCount] = vertexCoordinates;

                //Get Vertex Normals
                //Avoids if statements, edge cases utilize the existing vertices and the evaluated vertex
                int left = j > 0 ? -1 : 0;
                int right = j < arr.length - 1 ? 1 : 0;
                int up = i > 0 ? -1 : 0;
                int down = i < arr.length - 1 ? 1 : 0;

                //inverse scale
                double[] vertexNormals = {
                    scale/scaleX * (arr[i][j + right] - arr[i][j + left]) / (right - left),
                    scale/scaleY * (arr[i + down][j] - arr[i + up][j]) / (down - up),
                    1
                };

                normalizeVector(vertexNormals);

                normals[vertexCount] = vertexNormals;

                //Get texture coordinates
                textCoord[vertexCount] = new double[]{(double) i/scale, (double) j/scale};

                vertexCount++;
            }
        }

        return new Mesh(vertexMatrix, faces, normals, textCoord);
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

    
    public static void normalizeVector(double[] vector){
        double norm = 0;

        for(int i = 0; i < vector.length; i++){
            norm += vector[i] * vector[i];
        }

        norm = Math.sqrt(norm);

        for(int i = 0; i < vector.length; i++){
            vector[i] /= norm;
        }
    }
}

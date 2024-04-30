import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class toObj {

    public static void main(String[] args) {
        int n = 4;
        double scaleX = 10, scaleY = 10, scaleZ = 5, roughness = 0.5;
        String filename = "terrain.obj";

        double[][] heightMap = HeightMap.DiamondSquare(n, roughness);
        toMesh.Mesh mesh = toMesh.getMesh(heightMap, scaleX, scaleY, scaleZ);
        String content = meshToString(mesh);
        writeToFile(filename, content);

    }

    public static String meshToString(toMesh.Mesh mesh){
        double[][] vertices = mesh.vertices();
        int[][] faces = mesh.faces();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < vertices.length; i++){
            sb.append("v " + vertices[i][0] + " " + vertices[i][1] + " " + vertices[i][2] + "\n");
        }

        for(int i = 0; i < faces.length; i++){
            sb.append("f " + faces[i][0] + " " + faces[i][1] + " " + faces[i][2] + "\n");
        }

        return sb.toString();
    }

    public static void writeToFile(String filename, String content){
        try{
            Files.writeString(Paths.get(filename), content);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

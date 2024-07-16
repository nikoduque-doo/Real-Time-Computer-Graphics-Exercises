import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class toObj_3 {

    public static void main(String[] args) {
        int n = 7;
        double scaleX = 10, scaleY = 10, scaleZ = 2, roughness = 0.5;
        String filename = "terrain.obj";

        double[][] heightMap = HeightMap_3.DiamondSquare(n, roughness);
        toMesh_3.Mesh mesh = toMesh_3.getMesh(heightMap, scaleX, scaleY, scaleZ);
        String content = meshToString(mesh);
        writeToFile(filename, content);

    }

    public static String meshToString(toMesh_3.Mesh mesh){
        double[][] vertices = mesh.vertices();
        int[][] faces = mesh.faces();
        double[][] normals = mesh.normals();
        double[][] textureCoordinates = mesh.textureCoordinates();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < vertices.length; i++){
            sb.append("v " + vertices[i][0] + " " + vertices[i][1] + " " + vertices[i][2] + "\n");
        }

        for(int i = 0; i < normals.length; i++){
            sb.append("vn " + normals[i][0] + " " + normals[i][1] + " " + normals[i][2] + "\n");
        }

        for(int i = 0; i < textureCoordinates.length; i++){
            sb.append("vt " + textureCoordinates[i][0] + " " + textureCoordinates[i][1] + "\n");
        }

        for(int i = 0; i < faces.length; i++){
            sb.append(
                "f " + tripleString(faces[i][0]) + " " + 
                tripleString(faces[i][1]) + " " + tripleString(faces[i][2]) + "\n"
                );
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

    public static String tripleString(double s){
        return s + "/" + s + "/" + s;
    }
}

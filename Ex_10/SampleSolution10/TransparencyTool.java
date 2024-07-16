package com.tumcg;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class TransparencyTool {
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter("planes.obj", StandardCharsets.US_ASCII);

            float planeDepthFrontToBack[] = {
                    0.0f, 4.0f, 8.0f
            };
            float planeDepthBackToFront[] = {
                    8.0f, 4.0f, 0.0f
            };
            float planeDepth[] = planeDepthBackToFront;//planeDepthFrontToBack
            float left = -4.0f;
            float right = 4.0f;
            float bottom = -4.0f;
            float top = 4.0f;

            for (int i = 0; i < 3; i++) {
                writer.println("v " + left + " " + planeDepth[i] + " " + bottom);
                writer.println("v " + right + " " + planeDepth[i] + " " + bottom);
                writer.println("v " + right + " " + planeDepth[i] + " " + top);
                writer.println("v " + left + " " + planeDepth[i] + " " + top);

                int idx0 = i * 4 + 1;
                int idx1 = i * 4 + 2;
                int idx2 = i * 4 + 3;
                int idx3 = i * 4 + 4;
                writer.println("f " + idx0 + " " + idx1 + " " + idx2);
                writer.println("f " + idx0 + " " + idx2 + " " + idx3);
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package se.tribestar.mage.backend.gl;

import se.tribestar.mage.math.Vector3;

/**
 * Created by Andreas Stjerndal on 06-Jan-2015.
 */
public class CubeVertices extends Vertices3{


    public CubeVertices(GLGraphics glGraphics, int maxVertices, int maxIndices, boolean hasColor, boolean hasTexCoords, boolean hasNormals) {
        super(glGraphics,maxVertices,maxIndices,hasColor,hasTexCoords,hasNormals);
    }

    public CubeVertices(GLGraphics glGraphics, boolean hasColor, boolean hasTexCoords, boolean hasNormals){

        super(glGraphics,36,42,hasColor,hasTexCoords,hasNormals);

        color = new Vector3(1,0,0);
        alpha = 1.0f;

        float[] vertices = {
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha,  0, 1, 0, 0, 1,
                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 1, 0, 0, 1,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 0, 0, 0, 1,
                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0, 0, 0, 1,
                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 1, 1, 0, 0,
                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 1, 1, 0, 0,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 0, 1, 0, 0,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0, 1, 0, 0,
                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 1, 0, 0, -1,
                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 1, 0, 0, -1,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 0, 0, 0, -1,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0, 0, 0, -1,
                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 1, -1, 0, 0,
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 1, -1, 0, 0,
                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 0, -1, 0, 0,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0, -1, 0, 0,
                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 1, 0, 1, 0,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 1, 0, 1, 0,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 0, 0, 1, 0,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0, 0, 1, 0,
                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 1, 0, -1, 0,
                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 1, 0, -1, 0,
                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 0, 0, -1, 0,
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0, 0, -1, 0 };

        short[] indices = {
                0, 1, 2, 2, 3, 0,
                4, 5, 6, 6, 7, 4,
                8, 9, 10, 10, 11, 8,
                12, 13, 14, 14, 15, 12,
                16, 17, 18, 18, 19, 16,
                20, 21, 22, 22, 23, 20,
                24, 25, 26, 26, 27, 24
        };

//        super(glGraphics, vertices.length / 8, indices.length, false, true, true);

        setVertices(vertices,0,vertices.length);
        setIndices(indices,0,indices.length);
        
    }



}

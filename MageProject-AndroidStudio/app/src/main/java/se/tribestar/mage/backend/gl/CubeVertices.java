package se.tribestar.mage.backend.gl;

/**
 * Created by Andreas Stjerndal on 06-Jan-2015.
 */
public class CubeVertices extends Vertices3{


    public CubeVertices(GLGraphics glGraphics, int maxVertices, int maxIndices, boolean hasColor, boolean hasTexCoords, boolean hasNormals) {
        super(glGraphics,maxVertices,maxIndices,hasColor,hasTexCoords,hasNormals);
    }

    public CubeVertices(GLGraphics glGraphics){

        super(glGraphics,24,36,true,true,true);

        float[] vertices = {
                -0.5f, -0.5f, 0.5f, 0, 1, 0, 0, 1,
                0.5f, -0.5f, 0.5f, 1, 1, 0, 0, 1,
                0.5f, 0.5f, 0.5f, 1, 0, 0, 0, 1,
                -0.5f, 0.5f, 0.5f, 0, 0, 0, 0, 1,
                0.5f, -0.5f, 0.5f, 0, 1, 1, 0, 0,
                0.5f, -0.5f, -0.5f, 1, 1, 1, 0, 0,
                0.5f, 0.5f, -0.5f, 1, 0, 1, 0, 0,
                0.5f, 0.5f, 0.5f, 0, 0, 1, 0, 0,
                0.5f, -0.5f, -0.5f, 0, 1, 0, 0, -1,
                -0.5f, -0.5f, -0.5f, 1, 1, 0, 0, -1,
                -0.5f, 0.5f, -0.5f, 1, 0, 0, 0, -1,
                0.5f, 0.5f, -0.5f, 0, 0, 0, 0, -1,
                -0.5f, -0.5f, -0.5f, 0, 1, -1, 0, 0,
                -0.5f, -0.5f, 0.5f, 1, 1, -1, 0, 0,
                -0.5f, 0.5f, 0.5f, 1, 0, -1, 0, 0,
                -0.5f, 0.5f, -0.5f, 0, 0, -1, 0, 0,
                -0.5f, 0.5f, 0.5f, 0, 1, 0, 1, 0,
                0.5f, 0.5f, 0.5f, 1, 1, 0, 1, 0,
                0.5f, 0.5f, -0.5f, 1, 0, 0, 1, 0,
                -0.5f, 0.5f, -0.5f, 0, 0, 0, 1, 0,
                -0.5f, -0.5f, -0.5f, 0, 1, 0, -1, 0,
                0.5f, -0.5f, -0.5f, 1, 1, 0, -1, 0,
                0.5f, -0.5f, 0.5f, 1, 0, 0, -1, 0,
                -0.5f, -0.5f, 0.5f, 0, 0, 0, -1, 0 };

        short[] indices = {
                0, 1, 2, 2, 3, 0,
                4, 5, 6, 6, 7, 4,
                8, 9, 10, 10, 11, 8,
                12, 13, 14, 14, 15, 12,
                16, 17, 18, 18, 19, 16,
                20, 21, 22, 22, 23, 20,
                24, 25, 26, 26, 27, 24
        };
        
        setVertices(vertices,0,vertices.length);
        setIndices(indices,0,indices.length);
        
    }



}

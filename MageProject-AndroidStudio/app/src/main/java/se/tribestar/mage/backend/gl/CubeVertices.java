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

        float[] vertices = { −0.5f, -0.5f, 0.5f, 0, 1,
                0.5f, -0.5f, 0.5f, 1, 1,
                0.5f, 0.5f, 0.5f, 1, 0,
                -0.5f, 0.5f, 0.5f, 0, 0,
                0.5f, -0.5f, 0.5f, 0, 1,
                0.5f, -0.5f, -0.5f, 1, 1,
                0.5f, 0.5f, -0.5f, 1, 0,
                0.5f, 0.5f, 0.5f, 0, 0,
                0.5f, -0.5f, -0.5f, 0, 1,
                -0.5f, -0.5f, -0.5f, 1, 1,
                -0.5f, 0.5f, -0.5f, 1, 0,
                0.5f, 0.5f, -0.5f, 0, 0,
                -0.5f, -0.5f, -0.5f, 0, 1,
                -0.5f, -0.5f, 0.5f, 1, 1,
                -0.5f, 0.5f, 0.5f, 1, 0,
                -0.5f, 0.5f, -0.5f, 0, 0,
                -0.5f, 0.5f, 0.5f, 0, 1,
                0.5f, 0.5f, 0.5f, 1, 1,
                0.5f, 0.5f, -0.5f, 1, 0,
                -0.5f, 0.5f, -0.5f, 0, 0,
                -0.5f, -0.5f, 0.5f, 0, 1,
                0.5f, -0.5f, 0.5f, 1, 1,
                0.5f, -0.5f, -0.5f, 1, 0,
                -0.5f, -0.5f, -0.5f, 0, 0
        };
        short[] indices = { 0, 1, 3, 1, 2, 3,
                4, 5, 7, 5, 6, 7,
                8, 9, 11, 9, 10, 11,
                12, 13, 15, 13, 14, 15,
                16, 17, 19, 17, 18, 19,
                20, 21, 23, 21, 22, 23,
        };
        setVertices(vertices,0,vertices.length);
        setIndices(indices,0,indices.length);
        
    }



}

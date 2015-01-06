package se.tribestar.mage.backend.gl;

import se.tribestar.mage.math.Vector3;

/**
 * Created by Andreas Stjerndal on 06-Jan-2015.
 */
public class CubeVertices extends Vertices3{
    public static final int NUM_VERTICES = 24;
    public static final int NUM_INDICES = 42;


    public CubeVertices(GLGraphics glGraphics, int maxVertices, int maxIndices, boolean hasColor, boolean hasTexCoords, boolean hasNormals) {
        super(glGraphics,maxVertices,maxIndices,hasColor,hasTexCoords,hasNormals);
    }

    public CubeVertices(GLGraphics glGraphics, boolean hasColor, boolean hasTexCoords, boolean hasNormals){

        //super(glGraphics,36,42,hasColor,hasTexCoords,hasNormals);

        color = new Vector3(1f,0f,0f);
        alpha = 1.0f;

/*        float[] vertices = {
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
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0, 0, -1, 0 };*/

        float[] vertices = getVertices(hasColor, hasTexCoords, hasNormals);
        setup(glGraphics,NUM_VERTICES, NUM_INDICES, hasColor, hasTexCoords, hasNormals);

        short[] indices = {
                0, 1, 2, 2, 3, 0,
                4, 5, 6, 6, 7, 4,
                8, 9, 10, 10, 11, 8,
                12, 13, 14, 14, 15, 12,
                16, 17, 18, 18, 19, 16,
                20, 21, 22, 22, 23, 20,
                24, 25, 26, 26, 27, 24
        };


        if(hasColor){
            vertices = getColorVertices();
            setup(glGraphics,24,42, hasColor, hasTexCoords, hasNormals);
        }
        else if(hasTexCoords && hasNormals){
            vertices = getTextureNormalVertices();
            setup(glGraphics,24, 42, hasColor, hasTexCoords, hasNormals);
        }


//        super(glGraphics, vertices.length / 8, indices.length, false, true, true);
        setVertices(vertices,0,vertices.length);
        setIndices(indices, 0, indices.length);
        
    }

    private float[] getVertices(boolean hasColor, boolean hasTexCoords, boolean hasNormals) {
        if(hasColor) {
            if(hasTexCoords) {
                if(hasNormals)
                    return getColorTextureNormalVertices();
                else
                    return getColorTextureVertices();
            } else if (hasNormals)
                return getColorNormalVertices();
            else
                return getColorVertices();
        } else if(hasTexCoords) {
            if(hasNormals)
                return getTextureNormalVertices();
            else
                return getTextureVertices();
        } else
            throw new IllegalStateException("Invalid state, Cube must have either color or texture");
    }

    private float[] getColorVertices(){

        float[] vertices = {
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha,
                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha,
                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha,

                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha,
                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha,

                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha,
                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha,

                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha,
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha,
                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha,

                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha,

                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha,
                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha,
                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha,
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha
        };

        return vertices;
    }

    private float[] getColorTextureNormalVertices(){

        float[] vertices = {
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 1, 0, 0, 1,
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
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0, 0, -1, 0
        };

        return vertices;
    }

    private float[] getColorNormalVertices(){

        float[] vertices = {
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0, 1,
                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0, 1,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0, 1,
                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0, 1,

                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 0, 0,
                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 0, 0,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 0, 0,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 0, 0,

                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0, -1,
                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0, -1,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0, -1,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0, -1,

                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, -1, 0, 0,
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, -1, 0, 0,
                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, -1, 0, 0,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, -1, 0, 0,

                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 1, 0,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 1, 0,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 1, 0,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 1, 0,

                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, -1, 0,
                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, -1, 0,
                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, -1, 0,
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, -1, 0
        };

        return vertices;
    }

    private float[] getColorTextureVertices(){

        float[] vertices = {
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 1,
                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 1,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 0,
                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0,

                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 1,
                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 1,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 0,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0,

                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 1,
                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 1,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 0,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0,

                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 1,
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 1,
                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 0,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0,

                -0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 1,
                0.5f, 0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 1,
                0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 0,
                -0.5f, 0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 0,

                -0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 0, 1,
                0.5f, -0.5f, -0.5f, color.x, color.y, color.z, alpha, 1, 1,
                0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 1, 0,
                -0.5f, -0.5f, 0.5f, color.x, color.y, color.z, alpha, 0, 0,
        };

        return vertices;
    }



    private float[] getTextureNormalVertices(){

        float[] vertices = {
                -0.5f, -0.5f, 0.5f, 0, 1, 0, 0, 1,
                0.5f, -0.5f, 0.5f, 1, 1, 0, 0, 1,
                0.5f, 0.5f, 0.5f, 1, 0, 0, 0, 1,
                -0.5f, 0.5f, 0.5f, 0, 0, 0, 0, 1,

                0.5f, -0.5f, 0.5f, 0, 1, 1, 0, 0,
                0.5f, -0.5f, -0.5f,  1, 1, 1, 0, 0,
                0.5f, 0.5f, -0.5f, 1, 0, 1, 0, 0,
                0.5f, 0.5f, 0.5f,0, 0, 1, 0, 0,

                0.5f, -0.5f, -0.5f, 0, 1, 0, 0, -1,
                -0.5f, -0.5f, -0.5f, 1, 1, 0, 0, -1,
                -0.5f, 0.5f, -0.5f, 1, 0, 0, 0, -1,
                0.5f, 0.5f, -0.5f,  0, 0, 0, 0, -1,

                -0.5f, -0.5f, -0.5f, 0, 1, -1, 0, 0,
                -0.5f, -0.5f, 0.5f, 1, 1, -1, 0, 0,
                -0.5f, 0.5f, 0.5f,  1, 0, -1, 0, 0,
                -0.5f, 0.5f, -0.5f,  0, 0, -1, 0, 0,

                -0.5f, 0.5f, 0.5f,  0, 1, 0, 1, 0,
                0.5f, 0.5f, 0.5f,  1, 1, 0, 1, 0,
                0.5f, 0.5f, -0.5f,  1, 0, 0, 1, 0,
                -0.5f, 0.5f, -0.5f, 0, 0, 0, 1, 0,

                -0.5f, -0.5f, -0.5f, 0, 1, 0, -1, 0,
                0.5f, -0.5f, -0.5f,  1, 1, 0, -1, 0,
                0.5f, -0.5f, 0.5f,  1, 0, 0, -1, 0,
                -0.5f, -0.5f, 0.5f,  0, 0, 0, -1, 0 };

        return vertices;
    }

    private float[] getTextureVertices(){

        float[] vertices = {
                -0.5f, -0.5f, 0.5f, 0, 1,
                0.5f, -0.5f, 0.5f, 1, 1,
                0.5f, 0.5f, 0.5f, 1, 0,
                -0.5f, 0.5f, 0.5f, 0, 0,

                0.5f, -0.5f, 0.5f, 0, 1,
                0.5f, -0.5f, -0.5f,  1, 1,
                0.5f, 0.5f, -0.5f, 1, 0,
                0.5f, 0.5f, 0.5f,0, 0,

                0.5f, -0.5f, -0.5f, 0, 1,
                -0.5f, -0.5f, -0.5f, 1, 1,
                -0.5f, 0.5f, -0.5f, 1, 0,
                0.5f, 0.5f, -0.5f,  0, 0,

                -0.5f, -0.5f, -0.5f, 0, 1,
                -0.5f, -0.5f, 0.5f, 1, 1,
                -0.5f, 0.5f, 0.5f,  1, 0,
                -0.5f, 0.5f, -0.5f,  0, 0,

                -0.5f, 0.5f, 0.5f,  0, 1,
                0.5f, 0.5f, 0.5f,  1, 1,
                0.5f, 0.5f, -0.5f,  1, 0,
                -0.5f, 0.5f, -0.5f, 0, 0,

                -0.5f, -0.5f, -0.5f, 0, 1,
                0.5f, -0.5f, -0.5f,  1, 1,
                0.5f, -0.5f, 0.5f,  1, 0,
                -0.5f, -0.5f, 0.5f,  0, 0};

        return vertices;
    }



}

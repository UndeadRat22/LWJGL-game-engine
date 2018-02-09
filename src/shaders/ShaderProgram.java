package shaders;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.assimp.AIMatrix4x4;
import org.lwjgl.ovr.OVRMatrix4f;
import primitives.Vector3f;

import java.io.*;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public abstract class ShaderProgram {
    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public ShaderProgram(String vertexFileName, String fragmentFileName){
        vertexShaderID = loadShader(vertexFileName, GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFileName, GL_FRAGMENT_SHADER);
        programID = glCreateProgram();
        glAttachShader(programID, vertexShaderID);
        glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        glLinkProgram(programID);
        glValidateProgram(programID);
        getAllUniformLocations();
    }

    protected abstract void getAllUniformLocations();
    protected abstract void bindAttributes();

    protected int getUniformLocation(String unfiformName) {
        return glGetUniformLocation(programID, unfiformName);
    }

    protected void loadMatrix(int location, Matrix4f matrix){
        glUniformMatrix4fv(location, false, matrix.get(matrixBuffer));
    }

    protected void loadBoolean(int location, boolean value){
        glUniform1i(location, value ? 1 : 0);
    }

    protected void loadFloat(int location, float value){
        glUniform1f(location, value);
    }

    protected void loadVector3f(int location, Vector3f vec){
        glUniform3f(location, vec.x, vec.y, vec.z);
    }


    protected void bindAttribute(int attribute, String variableName){
        glBindAttribLocation(programID, attribute, variableName);
    }

    public void start(){
        glUseProgram(programID);
    }
    public  void stop(){
        glUseProgram(0);
    }

    public void discardProgram(){
        stop();
        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
        glDeleteProgram(programID);
    }

    private static int loadShader(String file, int type) {
        StringBuilder src = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                src.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Could not read file " + file + "!");
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderId = glCreateShader(type);
        glShaderSource(shaderId, src);
        glCompileShader(shaderId);
        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0)
        {
            System.err.println(glGetShaderInfoLog(shaderId, 500));
            System.err.println("Could not compile shader " + file + "!");
            System.exit(-1);
        }
        return shaderId;
    }
}


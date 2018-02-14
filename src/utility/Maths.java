package utility;

import engine.Engine;
import objects.components.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import primitives.Transform;

public class Maths {
    public static Matrix4f createTransformationMatrix(Transform transform) {
        Matrix4f matrix = new Matrix4f();
        matrix.translate(transform.getPosition());
        Vector3f rotation = transform.getRotation();
        matrix.rotate((float) Math.toRadians(rotation.x), 1.0f, 0.0f, 0.0f);
        matrix.rotate((float) Math.toRadians(rotation.y), 0.0f, 1.0f, 0.0f);
        matrix.rotate((float) Math.toRadians(rotation.z), 0.0f, 0.0f, 1.0f);
        matrix.scale(transform.getScale());

        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f matrix = new Matrix4f();
        Vector3f rotation = camera.getTransform().getRotation();
        matrix.rotate((float) Math.toRadians(rotation.x), 1.0f, 0.0f, 0.0f);
        matrix.rotate((float) Math.toRadians(rotation.y), 0.0f, 1.0f, 0.0f);
        matrix.rotate((float) Math.toRadians(rotation.z), 0.0f, 0.0f, 1.0f);
        Vector3f position = camera.getTransform().getPosition();
        Vector3f inversePos = new Vector3f(-position.x, -position.y, -position.z);
        matrix.translate(inversePos);
        return matrix;
    }

    public static Matrix4f createProjectionMatrix(float FAR_PLANE, float NEAR_PLANE, float FOV){
        float aspecpectRatio = (float) Engine.display.getDimensions().x / Engine.display.getDimensions().y;
        float yscale = (float) ((1f/Math.tan(Math.toRadians((FOV/2f))))*aspecpectRatio);
        float xscale = yscale / aspecpectRatio;
        float frustumLen = FAR_PLANE - NEAR_PLANE;

        Matrix4f projectionMatrix = new Matrix4f();
        projectionMatrix.m00(xscale);
        projectionMatrix.m11(yscale);
        projectionMatrix.m22(-(FAR_PLANE + NEAR_PLANE)/frustumLen);
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-(2 * FAR_PLANE * NEAR_PLANE)/frustumLen);
        projectionMatrix.m33(0);
        return projectionMatrix;
    }
}

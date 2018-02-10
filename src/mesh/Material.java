package mesh;

public class Material {
    public float reflectivity;
    public float shineDampening;

    public Material(float reflectivity, float shineDampening){
        this.reflectivity = reflectivity;
        this.shineDampening = shineDampening;
    }

    @Override
    public String toString() {
        return reflectivity + " " + shineDampening;
    }
}

package authservice.authservice.dtos.write;

public class AddFeatureToPathWriteDto {

    private String method;

    private String path;

    private String feature;

    /*
     * Getters and Setters y
     */

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}

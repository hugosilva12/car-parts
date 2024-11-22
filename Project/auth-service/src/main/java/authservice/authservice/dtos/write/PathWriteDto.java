package authservice.authservice.dtos.write;

import lombok.ToString;


@ToString
public class PathWriteDto {
    private String method;

    private String path;

    /*
     * Getters and Setters
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
}

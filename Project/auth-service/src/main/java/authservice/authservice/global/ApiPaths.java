package authservice.authservice.global;

public  class ApiPaths {

    public static class UserPath {
        public final static String USER ="/api/auth/users";
        public final static String ADD_FEATURE ="/add-feature";
        public final static String WITH_FEATURE ="/with-features";

        public static String getPathFromRoot(String path) {
            return USER.concat(path);
        }
    }

    public static class FeaturePath {
        public final static String FEATURE ="/api/auth/features";

        public static String getPathFromRoot(String path) {
            return FEATURE.concat(path);
        }
    }

    public static class Path {
        public final static String PATH ="/api/auth/paths";

        public final static String ADD_FEATURE  ="/add-feature";

        public final static String REMOVE_FEATURE  ="/remove-feature";


        public static String getPathFromRoot(String path) {
            return PATH.concat(path);
        }
    }

    public static class TokenPath {
        public final static String PATH ="/api/auth/token";

        public static String getPathFromRoot(String path) {
            return PATH.concat(path);
        }
    }


}

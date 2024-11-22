package com.example.userservice.global;

public  class ApiPaths {

    public static class ClientPath {
        public final static String CLIENT ="/api/users/clients";


        public static String getPathFromRoot(String path) {
            return CLIENT.concat(path);
        }
    }

    public static class EmployeePath {
        public final static String EMPLOYEE ="/api/users/employees";

        public static String getPathFromRoot(String path) {
            return EMPLOYEE.concat(path);
        }
    }


}

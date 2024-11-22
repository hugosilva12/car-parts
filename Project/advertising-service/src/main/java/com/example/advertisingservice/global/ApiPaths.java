package com.example.advertisingservice.global;

public  class ApiPaths {

    public static class CarPath {
        public final static String ADVERTISING ="/api/advertisement/ad";

        public static String getPathFromRoot(String path) {
            return ADVERTISING.concat(path);
        }
    }




}

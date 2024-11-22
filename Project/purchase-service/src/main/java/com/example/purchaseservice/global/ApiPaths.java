package com.example.purchaseservice.global;

public  class ApiPaths {

    public static class CarPath {
        public final static String CAR ="/api/purchases/cars";

        public static String getPathFromRoot(String path) {
            return CAR.concat(path);
        }
    }




}

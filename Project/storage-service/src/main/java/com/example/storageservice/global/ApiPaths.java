package com.example.storageservice.global;

public  class ApiPaths {

    public static class CarPath {
        public final static String SECTIONS ="/api/storage/sections";

        public static String getPathFromRoot(String path) {
            return SECTIONS.concat(path);
        }
    }




}

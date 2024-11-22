package com.example.precariousservice.global;

public  class ApiPaths {

    public static class CarPath {
        public final static String ITEM_CAR_PRICE ="/api/prices/itemprices";

        public static String getPathFromRoot(String path) {
            return ITEM_CAR_PRICE.concat(path);
        }
    }




}

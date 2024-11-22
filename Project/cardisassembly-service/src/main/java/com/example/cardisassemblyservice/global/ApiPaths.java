package com.example.cardisassemblyservice.global;

public  class ApiPaths {

    public static class CarPath {
        public final static String ITEM_CAR ="/api/cardisassembly/itemcar";

        public static String getPathFromRoot(String path) {
            return ITEM_CAR.concat(path);
        }
    }

    public static class ItemPath {
        public final static String CATEGORY="/api/cardisassembly/category";

        public static String getPathFromRoot(String path) {
            return CATEGORY.concat(path);
        }
    }



}

package com.example.salesservice.global;

public  class ApiPaths {

    public static class CarPath {
        public final static String SALE ="/api/sales/sale";

        public static String getPathFromRoot(String path) {
            return SALE.concat(path);
        }
    }




}

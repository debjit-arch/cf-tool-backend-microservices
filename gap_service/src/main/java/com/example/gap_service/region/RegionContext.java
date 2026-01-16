package com.example.gap_service.region;

public class RegionContext {

    private static final ThreadLocal<String> REGION = new ThreadLocal<>();

    public static void set(String region) {
        REGION.set(region);
    }

    public static String get() {
        return REGION.get();
    }

    public static void clear() {
        REGION.remove();
    }
}

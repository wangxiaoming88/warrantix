package com.warrantix.main;

public class GlobalConfig {

    private static GlobalConfig instance = null;
    private int defaultPluginIndex = 1;

    public static GlobalConfig getInstance() {
        if (instance == null)
            instance = new GlobalConfig();

        return instance;
    }


    public int getDefaultPluginIndex() {
        return defaultPluginIndex;
    }

    public void setDefaultPluginIndex(int defaultPluginIndex) {
        this.defaultPluginIndex = defaultPluginIndex;
    }

    public String getPluginName(int index) {
        switch (index) {
            case 0:
                return "warrantix";
            case 1:
                return "hero";
            case 2:
                return "goorej";
            case 3:
                return "samsung";
            case 4:
                return "eureka";
            case 5:
                return "lg";
            case 6:
                return "mahindra";
            case 7:
                return "micromax";
            case 8:
                return "voltas";
            default:
                return "warrantix";
        }
    }


}
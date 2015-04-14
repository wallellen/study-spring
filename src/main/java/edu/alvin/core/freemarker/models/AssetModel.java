package edu.alvin.core.freemarker.models;

import org.springframework.stereotype.Component;

@Component
public class AssetModel {
    private String assetsPath;

    public void setAssetsPath(String assetsPath) {
        this.assetsPath = assetsPath;
    }

    public String css(String fileName) {
        return assetsPath + "css/" + fileName;
    }

    public String js(String fileName) {
        return assetsPath + "js/" + fileName;
    }
}

package com.innov8tif.emassample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thet Paing Tun on 23/7/2018.
 */
public class EmasModel  {
    private List<ContentModel> contentModelList;
    private String documentPath;
    private double confidenceLevel;
    private String detectedFacePath;
    private double threshold;

    public EmasModel() {
    }


    public List<ContentModel> getContentModelList() {
        return contentModelList;
    }

    public void setContentModelList(List<ContentModel> contentModelList) {
        this.contentModelList = contentModelList;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public double getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(double confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getDetectedFacePath() {
        return detectedFacePath;
    }

    public void setDetectedFacePath(String detectedFacePath) {
        this.detectedFacePath = detectedFacePath;
    }

    @Override
    public String toString() {
        return "EmasModel{" +
                "contentModelList=" + contentModelList +
                ", documentPath='" + documentPath + '\'' +
                ", confidenceLevel=" + confidenceLevel +
                ", detectedFacePath='" + detectedFacePath + '\'' +
                ", threshold=" + threshold +
                '}';
    }
}
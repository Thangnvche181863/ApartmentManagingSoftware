/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author PC
 */
public class MediaData {

    private String mediaTag;
    private String altText;

    public MediaData() {
    }

    public MediaData(String mediaTag, String altText) {
        this.mediaTag = mediaTag;
        this.altText = altText;
    }

    public String getMediaTag() {
        return mediaTag;
    }

    public void setMediaTag(String mediaTag) {
        this.mediaTag = mediaTag;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }
    
    
}

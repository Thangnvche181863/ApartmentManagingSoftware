/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 *
 * @author PC
 */
public class HtmlSanitizer {

    public static String sanitize(String html) {
        Document document = Jsoup.parse(html);
        // Remove any unwanted tags, e.g., <script>
        document.select("script").remove();

        // Check if there is an image or video tag
        Element img = document.select("img").first();
        Element video = document.select("video").first();

        // Remove the first image or video from the content for separate display
        if (img != null) {
            img.remove();
        } else if (video != null) {
            video.remove();
        }

        return document.body().html(); // Return sanitized HTML
    }

    public static String extractFirstImageOrVideo(String html) {
        Document document = Jsoup.parse(html);
        Element img = document.select("img").first();
        Element video = document.select("video").first();

        if (img != null) {
            return img.outerHtml(); // Return the <img> tag
        } else if (video != null) {
            return video.outerHtml(); // Return the <video> tag
        }

        return ""; // No image or video found
    }
}

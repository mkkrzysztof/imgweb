package imageweb;

public class Image {
    private String imageBase64;
    private int brightness;


    public void setImageBase64(String image) {
        this.imageBase64=image;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getBrightness() {
        return brightness;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}

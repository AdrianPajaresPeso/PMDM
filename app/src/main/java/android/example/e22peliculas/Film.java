package android.example.e22peliculas;

import java.io.Serializable;

public class Film{

    private String name;
    private String urlThumnail;
    private String type;
    private String urlWeb;

    public Film(){}

    public Film(String name, String urlImg, String type, String urlWeb) {

        this.name = name;
        this.urlThumnail = urlImg;
        this.type = type;
        this.urlWeb = urlWeb;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImg() {
        return urlThumnail;
    }

    public void setUrlImg(String urlImg) {
        this.urlThumnail = urlImg;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", urlThumnail='" + urlThumnail + '\'' +
                ", type='" + type + '\'' +
                ", urlWeb='" + urlWeb + '\'' +
                '}';
    }
}

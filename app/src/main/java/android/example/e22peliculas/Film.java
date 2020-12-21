package android.example.e22peliculas;

public class Film {

    private String name;
    private String urlImg;
    private String type;

    public Film(){}

    public Film(String name, String urlImg, String type) {
        this.name = name;
        this.urlImg = urlImg;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}

package Classes;

import android.graphics.drawable.Drawable;

public class Person {

    private String name;
    private Integer image;

    public Person(String name, Integer image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}

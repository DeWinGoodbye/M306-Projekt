package view;

public class Schueler {
    String firstName;
    String name;
    String image;
    String klasse;

    public Schueler(){

    }

    public Schueler(String firstName, String name, String image, String klasse) {
        this.firstName = firstName;
        this.name = name;
        this.image = image;
        this.klasse = klasse;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }
}


package cu.xkoders.presentationcard.models;



import java.util.Random;

import cu.xkoders.presentationcard.R;

/**
 * Created by reinier.leyva on 10/08/2016.
 */
public class ContactoBK {
    private int imagen;
    private String nombre;
    private String url_face;
    private String url_web;
    private String[] phone_list;

    public ContactoBK( ) {

    }

    public ContactoBK(int imagen, String nombre, String url_face, String url_web, String[] phone_list) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.url_face = url_face;
        this.url_web = url_web;
        this.phone_list = phone_list;
    }

    public ContactoBK(int imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;

    }

    public ContactoBK(String nombre) {
        this.nombre = nombre;
        this.imagen = getRandomGirlDrawable();
    }

    public ContactoBK(String name, int imagen)
    {
        this.imagen = getRandomGirlDrawable();
        this.nombre = name;
    }

    public String getUrl_face() {
        return url_face;
    }

    public void setUrl_face(String url_face) {
        this.url_face = url_face;
    }

    public String getUrl_web() {
        return url_web;
    }

    public void setUrl_web(String url_web) {
        this.url_web = url_web;
    }

    public String[] getPhone_list() {
        return phone_list;
    }

    public void setPhone_list(String[] phone_list) {
        this.phone_list = phone_list;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private int getRandomGirlDrawable() {
        Random rnd = new Random();
        switch (rnd.nextInt(8)) {
            default:
            case 0:
                return R.drawable.avatar;
            case 1:
                return R.drawable.avatar;
            case 2:
                return R.drawable.avatar;
            case 3:
                return R.drawable.avatar;
            case 4:
                return R.drawable.avatar;
            case 5:
                return R.drawable.avatar;
            case 6:
                return R.drawable.avatar;
            case 7:
                return R.drawable.avatar;
        }
    }
}

package cu.xkoders.presentationcard.datasource;

public class Script {

    public static final String TABLA_NAME = "bitKard_contactos";
    public static final String ID = "id";
    public static final String ID_CONTACTO = "id_contacto";
    public static final String EMAIL = "email";
    public static final String AVATAR = "avatar";
    public static final String NAME = "name";
    public static final String WEB = "web";
    public static final String SOCIAL = "social";
    public static final String PHONE = "phone";

    public static final String SCRIPT_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLA_NAME + ";";
    public static final String SCRIPT_CREAR_TABLA = "CREATE TABLE" + TABLA_NAME + "( "
            + ID + " INTEGER PRIMARY KEY, "
            + ID_CONTACTO + " TEXT, "
            + EMAIL + " TEXT, "
            + AVATAR + " TEXT, "
            + NAME + " TEXT, "
            + WEB + " TEXT, "
            + SOCIAL + " TEXT, "
            + PHONE + " TEXT"
            + ");";

    public static final String[] columns = new String[]{Script.ID,
            Script.ID_CONTACTO,
            Script.EMAIL,
            Script.AVATAR,
            Script.NAME,
            Script.WEB,
            Script.SOCIAL,
            Script.PHONE};

    public static final String[] SCRIPT_INSERT_CREATE = new String[]{
            "INSERT INTO " + TABLA_NAME + " (" + ID + ", " + ID_CONTACTO + ", " + EMAIL + ", " + AVATAR + "," + NAME+ "," + WEB+ "," + SOCIAL+ ","+PHONE+") " +
                    "VALUES (1,'PONER_ID_CALL','reinier.leyva@get.hlg.tur.cu','AVATAR','Reinier Leyva Avila','https://www.accs.co.cu','https://www.facebook.com/comunicadorescubanos','24471206');",

    };
}

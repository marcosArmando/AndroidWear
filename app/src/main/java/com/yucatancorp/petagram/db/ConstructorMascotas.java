package com.yucatancorp.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.yucatancorp.petagram.R;
import com.yucatancorp.petagram.pojo.Mascota;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by marcos on 29/03/2017.
 */

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDaros(){

  /*      ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.chivo_1, R.drawable.dog_bone_50, "Catty", "5"));
        mascotas.add(new Mascota(R.drawable.gato_1, R.drawable.dog_bone_50, "Ronny", "5"));
        mascotas.add(new Mascota(R.drawable.perro_1, R.drawable.dog_bone_50, "Tobby", "5"));
        mascotas.add(new Mascota(R.drawable.gato_2, R.drawable.dog_bone_50, "Fuppy", "5"));
        mascotas.add(new Mascota(R.drawable.perro_2, R.drawable.dog_bone_50, "Lanny", "5"));

        return mascotas;*/

        BaseDatos db = new BaseDatos(context);

        if (!hayBaseDatos(context, ConstantesBaseDatos.DATABASE_NAME))
        {
            insertarCincoContactos(db);
        }
        return db.obtenerTodasLasMascotas();
    }

    public void insertarCincoContactos(BaseDatos db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Catty");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.chivo_1);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_HUESO, R.drawable.dog_bone_50);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Rokky");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.gato_1);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_HUESO, R.drawable.dog_bone_50);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Tommy");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro_1);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_HUESO, R.drawable.dog_bone_50);

        db.insertarContacto(contentValues);


        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Yoqqy");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.gato_2);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_HUESO, R.drawable.dog_bone_50);

        db.insertarContacto(contentValues);


        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Bossy");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro_2);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_HUESO, R.drawable.dog_bone_50);

        db.insertarContacto(contentValues);

    }

    public void darLikeMascota(Mascota mascota)
    {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascotas(mascota);
    }

    private static boolean hayBaseDatos(Context context, String dbNombre) {
        File dbFile = context.getDatabasePath(dbNombre);
        return dbFile.exists();
    }
}

package com.ulp.archivos.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.ulp.archivos.model.Usuario;

import java.io.*;

public class ApiClient {

//    private static String fileName = "Access.obj";

    public static void guardar(Context context, Usuario usuario) {

        try {
            File archivo = new File(context.getFilesDir(), "Access.obj");

            FileOutputStream fos = new FileOutputStream(archivo);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(usuario);
            bos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error de archivo (guardar)", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error de IO (guardar)", Toast.LENGTH_SHORT).show();
        }

    }

    public static Usuario leer(Context context) {

        try {
            File archivo = new File(context.getFilesDir(), "Access.obj");

            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            Usuario usuario = (Usuario) ois.readObject();
            fis.close();
            return usuario;

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error de archivo (leer)", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error de IO (leer)", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "No existe un objeto de esa clase", Toast.LENGTH_SHORT).show();
        }
        return null;

    }

    public static Usuario login(Context context, String mail, String pass) {

        try {
            File archivo = new File(context.getFilesDir(), "Access.obj");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            if (archivo.length() != 0) {
                FileInputStream fis = new FileInputStream(archivo);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);

                Usuario usuario = (Usuario) ois.readObject();
                fis.close();

                if (mail.equals(usuario.getMail()) && pass.equals(usuario.getPassword())) {
                    return usuario;
                }
            }

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error de archivo (login)", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error de IO (login)", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "No existe un objeto de esa clase", Toast.LENGTH_SHORT).show();
        }
        return null;

    }

}

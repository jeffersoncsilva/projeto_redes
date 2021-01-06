package com.projetos.redes.bd;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InicializaBancoDeDados {
    private Context mContext;

    public InicializaBancoDeDados(Context context){
        mContext = context;
    }

    /**
     * Verifica se o banco de dados está na memória.
     * @return falso caso o banco não esteja na memória.
     */
    public boolean existeBancoDeDadosMemoria(){
        File database = mContext.getDatabasePath(DbHelper.DATA_BASE_NAME);
        return database.exists();
    }

    public void copiaBanco() {
        try {
            File file = new File("/data/data/com.projetos.redes/databases");
            if(!file.exists()){
                file.mkdir();
                Log.d("InicializaBancoDeDados","diretorio criado com sucesso!!");
            }
            InputStream inputStream = mContext.getAssets().open(DbHelper.DATA_BASE_NAME);
            OutputStream outputStream = new FileOutputStream("/data/data/com.projetos.redes/databases/DbLexico");
            byte[] buff = new byte[1024];
            int legth = 0;
            while ((legth = inputStream.read(buff))>0){
                outputStream.write(buff,0,legth);
            }
            outputStream.flush();
            outputStream.close();
            Toast.makeText(mContext,"Banco copiado com sucesso!",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.projetos.redes.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.projetos.redes.R;
import com.projetos.redes.adapters.UsoDeInternetAdapter;
import com.projetos.redes.bd.BancoDeDados;
import com.projetos.redes.modelos.UsoDeInternet;
import java.util.ArrayList;
import java.util.List;

public class UsoDeInternetActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView rc_netusage;
    private static String tag = "NetUsageActivity";
    private UsoDeInternetAdapter adapter;
    private Button bt_reloadData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netusage);

        rc_netusage = findViewById(R.id.rc_data);
        adapter = new UsoDeInternetAdapter(new ArrayList<UsoDeInternet>(), this);
        rc_netusage.setLayoutManager( new LinearLayoutManager(this)) ;
        rc_netusage.setAdapter(adapter);
        bt_reloadData = findViewById(R.id.bt_usodados);
        bt_reloadData.setOnClickListener(this);
        new CarregaDadosDoBanco(getApplicationContext()).execute();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bt_usodados){
            new CarregaDadosDoBanco(getApplicationContext()).execute();
        }
    }

    protected class CarregaDadosDoBanco extends AsyncTask<Void, Void, Void>{
        private Context context;
        List<UsoDeInternet> c = new ArrayList<UsoDeInternet>();

        public CarregaDadosDoBanco(Context con){
            context = con;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bt_reloadData.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            BancoDeDados banco = new BancoDeDados(context);
            c = banco.pegarDadosUsoInternet();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.setLst(c);
            adapter.notifyDataSetChanged();
            bt_reloadData.setEnabled(true);
        }
    }
}
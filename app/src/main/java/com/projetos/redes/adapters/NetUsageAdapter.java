package com.projetos.redes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projetos.redes.R;
import com.projetos.redes.models.NetworkUsage;

import java.util.List;

public class NetUsageAdapter extends RecyclerView.Adapter<NetUsageAdapter.HolderNet> {
    private List<NetworkUsage> data;
    private Context c;

    public NetUsageAdapter(List<NetworkUsage> data, Context con){
        this.data = data;
        this.c = con;
    }

    public void setLst(List<NetworkUsage> lsr){
        this.data = lsr;
    }

    @NonNull
    @Override
    public HolderNet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.rc_element_netusage, parent, false);
        return new HolderNet(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNet h, int position) {
        NetworkUsage nu = data.get(position);
        h.dt_inicio.setText(String.format(c.getString(R.string.dt_inicio), nu.getDt_inicio()));
        h.dt_fim.setText(String.format(c.getString(R.string.dt_fim), nu.getDt_fim()));
        long wifi = nu.getBytes_wifi();
        long mobile = nu.getBytes_mobile();
        if(position >= 1){
            NetworkUsage n = data.get(position-1);
            wifi -= n.getBytes_wifi();
            mobile -= n.getBytes_mobile();
        }
        h.tx_wifi.setText(String.format(c.getString(R.string.tx_wifi), convertMb(wifi)));
        h.tx_mobile.setText(String.format(c.getString(R.string.tx_mobile), convertMb(mobile)));
        h.tx_total.setText(c.getString(R.string.tx_total)+convertMb(wifi+mobile));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private String convertMb(long size){
        long n = 1000;
        String s = "";
        double kb = size / n;
        double mb = kb / n;
        double gb = mb / n;
        double tb = gb / n;
        if(size < n) {
            s = size + " Bytes";
        } else if(size >= n && size < (n * n)) {
            s =  String.format("%.2f", kb) + " KB";
        } else if(size >= (n * n) && size < (n * n * n)) {
            s = String.format("%.2f", mb) + " MB";
        } else if(size >= (n * n * n) && size < (n * n * n * n)) {
            s = String.format("%.2f", gb) + " GB";
        } else if(size >= (n * n * n * n)) {
            s = String.format("%.2f", tb) + " TB";
        }
        return s;
    }

    protected class HolderNet extends RecyclerView.ViewHolder{
        public TextView dt_inicio, dt_fim,  tx_wifi, tx_mobile, tx_total;
        public HolderNet(@NonNull View v){
            super(v);
            dt_inicio = v.findViewById(R.id.tx_dt_inicio);
            dt_fim  = v.findViewById(R.id.tx_dt_fim);
            tx_mobile  = v.findViewById(R.id.tx_mobile);
            tx_wifi  = v.findViewById(R.id.tx_wifi);
            tx_total  = v.findViewById(R.id.tx_total);
        }
    }
}

package com.projetos.redes.modelos;

import com.projetos.redes.utilidades.Data;

public class MensagemUsuario {
    private final String autor;
    private final String mensagem;
    private final Data data;


    public MensagemUsuario(Data data, String autor, String msg){
        this.data = data;
        this.autor = autor;
        this.mensagem = msg;
    }

    public String getAutor() {
        return autor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Data getData() {
        return data;
    }

    public String toString(){
        return data + " " + autor + " " + mensagem;
    }
}

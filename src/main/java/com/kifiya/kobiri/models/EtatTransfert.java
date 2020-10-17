package com.kifiya.kobiri.models;

public enum EtatTransfert {

    ENCOURS("En cours"),
    RENDU("Rendu"),
    DESACTIVE("Desactive");

    private String label;

    public String getLabel(){
        return label;
    }

    EtatTransfert(String label){
        this.label = label;
    }

}

package com.github.Jay.Adventura.logika;
import java.util.*;
/**
 * Třída batoh se stará o výpis informací důležitých pro hráče.
 * Vypisuje se obsah baťůžku.
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
class PrikazBatoh implements IPrikaz {
    private static final String NAZEV = "batoh";
    private HerniPlan plan;
    private String Text;
    
    /**
     * Konstruktor, nastavení herního plánu.
    */    
    public PrikazBatoh(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Tato metoda se stará o veškerou funkcionalitu uvedenou v základním popisu třídy.
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        Text = "";
        Map<String, Vec> seznam;
        seznam = plan.getBatoh().vratSeznamVeci();
     if(seznam.size()==0)
     {
         Text = "Batoh je smutně prázdný.";
     }
     else
     {
         for (String key : seznam.keySet()) 
         {
                Text += " " + key;
         }
     }
                return "U sebe v batohu máš tyto věci: \n " + Text;
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}


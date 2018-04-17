/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Jay.Adventura.logika;



/*******************************************************************************
 * Instance třídy vec představují ...
 *
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private String url;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost,String url)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.url = url;
    }
    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev(){
        return nazev;
    }

    public boolean jePrenositelna(){
        return prenositelnost;
    }
    
    
    
    //== Soukromé metody (instancí i třídy) ========================================

    // Metoda vrací název obrázku s koncovkou
    public String getUrl() {
        return url;
    }

}

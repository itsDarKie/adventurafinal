package com.github.Jay.Adventura.logika;

/*******************************************************************************
 * Instance třídy {@code Postava} představují ...
 * The {@code Postava} class instances represent ...
 *
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
public class Postava
{
    private String nazev;
    private String text;
    
    public Postava(String nazev, String text)
    {
        this.nazev = nazev;
        this.text = text;
    }
    
    public String getNazev()
    {
        return this.nazev;
    }
    
    public String getText()
    {
        return this.text;
    }

}

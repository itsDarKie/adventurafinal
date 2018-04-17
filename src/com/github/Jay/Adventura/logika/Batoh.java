/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Jay.Adventura.logika;
import java.util.*;
/*******************************************************************************
 * Batoh představuje třídu, do které se ukládají jednotlivé věci - nese záznam toho co má člověk  u sebe.
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
public class Batoh
{
    private Map<String, Vec> seznamVeci;
    private int kapacita = 4;
    // konstruktor
    public Batoh()
    {
    seznamVeci = new HashMap<>();
    }
    // přidání věci
    public boolean vlozVec(Vec vec)
    {
         if(kapacita > seznamVeci.size())
         {
             this.seznamVeci.put(vec.getNazev(),vec);
             return true;
         }
         else
         {
             return false;
         }
    }
    // vrátí seznam věcí z batohu
    public Map<String, Vec> vratSeznamVeci()
    {
        return this.seznamVeci;
    }
    // vyhodí věc z batohu
    public Vec vyhodVec(String nazev)
    {
        return seznamVeci.remove(nazev);
    }
    // Detekovací metoda, která řekne jestli věc v batohu je či není.
    public Boolean obsahujeVec(String nazev)
    {
      boolean hledana = false;
      Vec pomocna = null;
      pomocna = this.seznamVeci.get(nazev);
      if(pomocna != null)
      {
          hledana = true;
      }
     return hledana;
    }
    // Vrací kapacitu batohu
    public int getKapacita()
    {
        return this.kapacita;
    }
}

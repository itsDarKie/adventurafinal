/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Jay.Adventura.logika;



/*******************************************************************************
 * Instance třídy Seber představují ...
 *
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
public class PrikazSeber implements IPrikaz{
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "seber". Pokuď je věc v místnosti a máme volnou kapacitu a jde zvednout, tak jí seberem..
     *
     *@param parametry - to co chce hráč sebrat
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            
            return "Co mám sebrat? Musíš zadat správný název věci.";
        }

        String nazevSbiraneVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbirana = aktualniProstor.odeberVec(nazevSbiraneVeci);

        if (sbirana == null) {
            return "Taková věc tady kolem není!";
        }
        else {
            if(sbirana.jePrenositelna())
            {
               if(plan.getBatoh().vlozVec(sbirana))
               {
                return "Sebral jsi " + sbirana.getNazev();
               }
               else
               {
                   aktualniProstor.vlozVec(sbirana);
                   return "Tuto věc sebrat můžeš, ale máš plný batoh! Vyhoď něco.";
               }
            }
            else{
                aktualniProstor.vlozVec(sbirana);
                return "To neuzvedneš, je to moc těžké!";
            }
        }
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

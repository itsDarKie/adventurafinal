package com.github.Jay.Adventura.logika;
/**
 * Tato Třída se stará o vyhazování věcí.
 * Prvně zjistí, zda hráč zadal jakou věc chce vyhodit.
 * Následně zjistí, jestli má danou věc baťůžku.
 * Pokuď ano tak jí vyhodí
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
class PrikazPoloz implements IPrikaz {
    private static final String NAZEV = "poloz";
    private HerniPlan plan;
    
    /**
     * Konstruktor třídy, který nastavuje herní plán.
    */    
    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Tato metoda se stará o veškerou funkcionalitu popsanou v základním popisu třídy.
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if(parametry.length == 0){
            return "Co mám vyhodit? Musíš zadat název věci.";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        if(plan.getBatoh().obsahujeVec(nazevVeci))
        {
         Vec vyhozena = plan.getBatoh().vyhodVec(nazevVeci);
         aktualniProstor.vlozVec(vyhozena);
         return "Polozil jsi " + vyhozena.getNazev();
        }
        else
        {
         return "Zadaná věc se v batohu nevyskytuje ";   
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

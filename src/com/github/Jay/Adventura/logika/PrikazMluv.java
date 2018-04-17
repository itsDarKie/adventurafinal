package com.github.Jay.Adventura.logika;

/**
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    /**
 
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) 
        {
            return "S kým si mám povídat? Nevím";
        }
        
        String cil = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        if(aktualniProstor.jePostavaVProstoru(cil))
        {
            Postava postava = aktualniProstor.odeberPostavu(cil);
            String text = postava.getText();
            aktualniProstor.vlozPostavu(postava);
            return text;
        }
        else
        {
            return "Tato postava tu není.";
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
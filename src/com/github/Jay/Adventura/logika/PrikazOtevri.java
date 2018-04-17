package com.github.Jay.Adventura.logika;

/**
 *  Třída PrikazOtevri implementuje pro hru příkaz otevri.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
class PrikazOtevri implements IPrikaz {
    private static final String NAZEV = "otevri";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazOtevri(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda která zajišťuje otevírání lednice
     * 
     *@param parametry - parametrem co se ma otevrit
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) 
        {
            return "Co mám otevřít ? Nevím";
        }

        String vec = parametry[0];
        Prostor aktualni = plan.getAktualniProstor();
        if(aktualni.getNazev().equals("sklep"))
        {
            if(vec.equals("dvere"))
            {
                if(plan.getNadvori())
                {
                    return "Dveře už jsou otevřeny. Je zbytečné je znovu otevírat";
                }
                else
                {
                    if(plan.getBatoh().obsahujeVec("zlaty_klic") && plan.getBatoh().obsahujeVec("stribrny_klic"))
                    {
                        plan.Otevri();
                        return "Otevřel jsi dveře na nádvoří!";
                    }
                    else
                    {
                        return "K otevření potřebuješ zlatý a stříbrný klíč.";
                    }   
                }
            }
            else
            {
                return "Otevřít se dají jen dveře.";
            }
        }
        else
        {
            return "Otevírat se dá se jen ve sklepě.";
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

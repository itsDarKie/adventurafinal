package com.github.Jay.Adventura.logika;

/**
 *  Třída PrikazBojuj implementuje pro hru příkaz bojuj.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
class PrikazBojuj implements IPrikaz {
    private static final String NAZEV = "bojuj";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazBojuj(HerniPlan plan) {
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
            return "S kým mám bojovat ? Nevím - zadej cíl";
        }
        
        String cil = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if(aktualniProstor.jePostavaVProstoru(cil))
        {
            if(cil.equals("boss"))
            {
                if(plan.getBatoh().obsahujeVec("mec"))
                {
                    aktualniProstor.odeberPostavu(cil);
                    plan.getHra().setKonecHry(true);
                    return "Zabil jsi bosse a tím vyhrál hru! - konec hry";
                }
                else
                {
                    plan.getHra().setKonecHry(true);
                    return "Nebyl jsi ozbrojen - boss tě zabil - konec hry";
                }
            }
            else
            {
                return "Bojovat lze jen s bossem na nádvoří!";
            }
        }
        else
        {
            return "Tato osoba v místnosti není!";
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
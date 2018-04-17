

import com.github.Jay.Adventura.logika.Batoh;
import com.github.Jay.Adventura.logika.Hra;
import com.github.Jay.Adventura.logika.Prostor;
import com.github.Jay.Adventura.logika.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    @Test       // Testování výherního scénáře
    public void testVyhry() 
    {
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi zbrojnice");
        hra1.zpracujPrikaz("seber mec");
        hra1.zpracujPrikaz("seber brneni");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi vinny_sklep");
        hra1.zpracujPrikaz("jdi sklep");
        
        // Protože jsou klíče náhodně rozložený, vytvoříme si je - malý hack
        Vec klic1 = new Vec("zlaty_klic",true,"");
        Vec klic2 = new Vec("stribrny_klic",true,"");
        hra1.getHerniPlan().getAktualniProstor().vlozVec(klic1);
        hra1.getHerniPlan().getAktualniProstor().vlozVec(klic2);
        hra1.zpracujPrikaz("seber zlaty_klic");
        hra1.zpracujPrikaz("seber stribrny_klic");
        
        hra1.zpracujPrikaz("otevri dvere");
        hra1.zpracujPrikaz("jdi nadvori");
        assertEquals(false, hra1.konecHry()); // Kontrola že konec hry eště nenastal
        assertEquals("Zabil jsi bosse a tím vyhrál hru! - konec hry", hra1.zpracujPrikaz("bojuj boss"));
        assertEquals(true, hra1.konecHry()); // Zabili jsme bosse - vyhrali jsme
    }
    @Test 
    public void testProhryBossMec() 
    {
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi zbrojnice");
        //hra1.zpracujPrikaz("seber mec");     // zapomenem meč
        hra1.zpracujPrikaz("seber brneni");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi vinny_sklep");
        hra1.zpracujPrikaz("jdi sklep");
        
        // Protože jsou klíče náhodně rozložený, vytvoříme si je - malý hack
        Vec klic1 = new Vec("zlaty_klic",true,"");
        Vec klic2 = new Vec("stribrny_klic",true,"");
        hra1.getHerniPlan().getAktualniProstor().vlozVec(klic1);
        hra1.getHerniPlan().getAktualniProstor().vlozVec(klic2);
        hra1.zpracujPrikaz("seber zlaty_klic");
        hra1.zpracujPrikaz("seber stribrny_klic");
        
        hra1.zpracujPrikaz("otevri dvere");
        hra1.zpracujPrikaz("jdi nadvori");
        assertEquals(false, hra1.konecHry()); // Kontrola že konec hry eště nenastal
        assertEquals("Nebyl jsi ozbrojen - boss tě zabil - konec hry", hra1.zpracujPrikaz("bojuj boss"));
        assertEquals(true, hra1.konecHry()); // Neměli jsme meč - boss nás zabil.
    }
    @Test 
    public void testProhryBrneni() 
    {
        hra1.zpracujPrikaz("jdi chodba");
        //hra1.zpracujPrikaz("jdi zbrojnice"); // nevezmem si brnění a meč je irelevantní
        //hra1.zpracujPrikaz("seber mec");     
        //hra1.zpracujPrikaz("seber brneni");
        //hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi vinny_sklep");
        hra1.zpracujPrikaz("jdi sklep");
        
        // Protože jsou klíče náhodně rozložený, vytvoříme si je - malý hack
        Vec klic1 = new Vec("zlaty_klic",true,"");
        Vec klic2 = new Vec("stribrny_klic",true,"");
        hra1.getHerniPlan().getAktualniProstor().vlozVec(klic1);
        hra1.getHerniPlan().getAktualniProstor().vlozVec(klic2);
        hra1.zpracujPrikaz("seber zlaty_klic");
        hra1.zpracujPrikaz("seber stribrny_klic");
        
        hra1.zpracujPrikaz("otevri dvere");
        assertEquals(false, hra1.konecHry()); // Kontrola že konec hry eště nenastal
        hra1.zpracujPrikaz("jdi nadvori");
        assertEquals(true, hra1.konecHry()); // Boss nás zabil šípem při vkročení do místnosti.
    }
    @Test 
    public void testSeberAPoloz() 
    {
        Batoh batoh = hra1.getHerniPlan().getBatoh();
        Vec vec = new Vec("vec",true,"");
        Vec vec2 = new Vec("nesebratelna",false,"");
        hra1.getHerniPlan().getAktualniProstor().vlozVec(vec); // testovana vec
        hra1.getHerniPlan().getAktualniProstor().vlozVec(vec2); // testovana vec2
        
        assertEquals(0, batoh.vratSeznamVeci().size());
        hra1.zpracujPrikaz("seber vec");
        assertEquals(1, batoh.vratSeznamVeci().size());
        hra1.zpracujPrikaz("seber vec"); // opětovný pokus o sebrání
        assertEquals(1, batoh.vratSeznamVeci().size()); // věc už jsme sebrali, v mistnosti není, tak sme ji znovu nevzali
        hra1.zpracujPrikaz("poloz vec");
        assertEquals(0, batoh.vratSeznamVeci().size()); // polozeni zlata - prazdny batoh
        hra1.zpracujPrikaz("seber vec"); // opětovný pokus o sebrání
        assertEquals(1, batoh.vratSeznamVeci().size()); // věc sme znovu sebrali
        hra1.zpracujPrikaz("seber nesebratelna"); // test sebrání nesebratelné věci
        assertEquals(1, batoh.vratSeznamVeci().size()); // Věc jsme nesebrali
    }
    @Test //Testujeme že klíče nejsou spawnuty v lokaci nadvoří - protože by pak hra nebyla dohratelná
    public void testKlicu() 
    {
        for(Prostor prostor : hra1.getHerniPlan().getProstory())
        {
            if(prostor.getNazev().equals("nadvori"))
            {
               assertEquals(null, prostor.odeberVec("zlaty_klic")); 
               assertEquals(null, prostor.odeberVec("stribrny_klic")); 
            }
        }
    }
}




import com.github.Jay.Adventura.logika.Prostor;
import com.github.Jay.Adventura.logika.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
public class ProstorTest
{
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
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě",0,0);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku",0,0);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }


    @Test
    public void test()
    {
        Prostor prostor1 = new com.github.Jay.Adventura.logika.Prostor("a", null,0,0);
        Vec vec1 = new com.github.Jay.Adventura.logika.Vec("a", true,"");
        prostor1.vlozVec(vec1);
        assertSame(vec1, prostor1.odeberVec("a"));
        assertNull(prostor1.odeberVec("b"));
    }
}


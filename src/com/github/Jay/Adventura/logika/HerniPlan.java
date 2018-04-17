package com.github.Jay.Adventura.logika;
import java.util.Random;
import java.util.*;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     pham01 - Manh Duy Phan
 *@version    4.1.1
 */
public class HerniPlan extends Observable{
    
    private Prostor aktualniProstor;
    private Batoh batoh;
    private Hra hra;
    private boolean nadvori; // rika zda je zamknuto ci otevreno
    private Set<Prostor> prostory; // všechny prostory v jednom setu
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        prostory = new HashSet<>();
        zalozProstoryHry();
        batoh = new Batoh();
        this.hra = hra;
        nadvori = false; // zamknuto
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor start = new Prostor("start","start - zacatek hry",0,300);
        Prostor chodba = new Prostor("chodba", "Tmavá chodba s pochodní",200,300);
        Prostor zbrojnice = new Prostor("zbrojnice", "Zbrojnice se starou výzbrojí",500,300);
        Prostor vinny_sklep = new Prostor("vinny_sklep", "Sklep plný vína, u stolku sedí opilý sluha",300,200);
        Prostor vez = new Prostor("vez", "Tajemná věž, venku nejde nic vidět",500,0);
        Prostor sklep = new Prostor("sklep","Sklep plný starých věcí",0,0);
        Prostor nadvori = new Prostor("nadvori","Čeká tě něco velkého",300,0);
        
        prostory.add(start);
        prostory.add(chodba);
        prostory.add(zbrojnice);
        prostory.add(vinny_sklep);
        prostory.add(vez);
        prostory.add(sklep);
        prostory.add(nadvori);
        
        
        Postava boss = new Postava ("boss","Jak se opovažuješ mne rušit, za to zaplatíš");
        Postava sluha = new Postava ("sluha","Nezapomeň se ozbrojit předtím než dojdeš na nádvoří");
        
        vinny_sklep.vlozPostavu(sluha);
        nadvori.vlozPostavu(boss);
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        start.setVychod(chodba);
        chodba.setVychod(start);
        chodba.setVychod(zbrojnice);
        chodba.setVychod(vinny_sklep);
        zbrojnice.setVychod(chodba);
        vinny_sklep.setVychod(chodba);
        vinny_sklep.setVychod(vez);
        vinny_sklep.setVychod(sklep);
        vez.setVychod(vinny_sklep);
        sklep.setVychod(vinny_sklep);
        sklep.setVychod(nadvori);
        nadvori.setVychod(sklep);
        
        
        
                
        aktualniProstor = start;  // hra začíná na startu
        
        Vec mec = new Vec("mec",true,"mec.jpg");
        Vec brneni = new Vec("brneni",true,"brneni.jpg");
        Vec klic1 = new Vec("zlaty_klic",true,"klic1.jpg");
        Vec klic2 = new Vec("stribrny_klic",true,"klic2.jpg");
        Vec vino = new Vec("vino",true,"vino.jpg");
        Vec sud = new Vec("sud",false,"sud.jpg");
        Vec postel = new Vec("postel",false,"postel.jpg");
        Vec kameny = new Vec("kameny",true,"kameny.jpg");
        Vec dvere = new Vec("dvere",false,"dvere.jpg");
        
        start.vlozVec(postel);
        zbrojnice.vlozVec(mec);
        zbrojnice.vlozVec(brneni);
        vinny_sklep.vlozVec(vino);
        vinny_sklep.vlozVec(sud);
        vez.vlozVec(kameny);
        sklep.vlozVec(dvere);
        
        // Uložení klíčů do náhodného prostoru
        Random rand = new Random();
        int pom1 = rand.nextInt(((prostory.size()-2) - 0) + 1) + 0;
        int pom2 = rand.nextInt(((prostory.size()-2) - 0) + 1) + 0;
        
        int index = 0;
        
        for(Prostor prostor : prostory)
        {
            if(index == pom1)
            {
                prostor.vlozVec(klic1);
            }
            if(index == pom2)
            {
                prostor.vlozVec(klic2);
            }
            index++;
        }
        
        
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;

    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    // metoda vrací odkaz na batoh
    public Batoh getBatoh() 
    {
       return this.batoh;
    }
    // metoda vrací odkaz na hru
    public Hra getHra()
    {
       return this.hra;
    }
    
    public boolean getNadvori()
    {
        return this.nadvori;
    }
    
    public void Otevri()
    {
        this.nadvori = true;
    }
    // Metoda vrátí seznam všech prostorů
    public Set<Prostor> getProstory()
    {
        return this.prostory;
    }
    // Metoda říká programu, že se herní plán změnil a má se znovu načíst.
    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }

    }


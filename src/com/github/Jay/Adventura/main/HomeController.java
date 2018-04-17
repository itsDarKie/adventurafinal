package com.github.Jay.Adventura.main;


import java.util.Observable;
import java.util.Observer;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


import com.github.Jay.Adventura.logika.Hra;
import com.github.Jay.Adventura.logika.Prostor;
import com.github.Jay.Adventura.logika.Vec;
import java.util.Set;
import javafx.geometry.Orientation;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky
 *
 */
public class HomeController extends GridPane implements Observer, Initializable {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
        @FXML private ListView<Object> listVychodu = new ListView<>();
	@FXML private ListView<Object> listVeciMistnost = new ListView<>();
        @FXML private ListView<Object> listVeciBatoh = new ListView<>();
	@FXML private ImageView rytir;
	
	private Hra hra;
        private ObservableList<Object> veciMistnost = FXCollections.observableArrayList();
        private ObservableList<Object> veciBatoh = FXCollections.observableArrayList();
        private ObservableList<Object> vychody = FXCollections.observableArrayList();
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() 
        {
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		if(hra.konecHry()) 
                {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);
		}
                hra.getHerniPlan().notifyObservers();
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	@Override
        public void initialize(URL url, ResourceBundle rb)  
        {
                hra = new Hra();
		vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
                
                
                listVeciMistnost.setItems(veciMistnost);
                listVeciBatoh.setItems(veciBatoh);
                listVychodu.setItems(vychody);
                
                listVeciMistnost.setOrientation(Orientation.HORIZONTAL);
                listVeciBatoh.setOrientation(Orientation.HORIZONTAL);
                
		hra.getHerniPlan().addObserver(this);
                hra.getHerniPlan().notifyObservers();
	}
        
        @FXML public void Batoh() 
        {

                    Map<String, Vec> seznam = hra.getHerniPlan().getBatoh().vratSeznamVeci();
                    int index = listVeciBatoh.getSelectionModel().getSelectedIndex();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String nazevVeci : seznam.keySet()) 
                    {
                       if(pomocna == index)
                       {
                           nazev = nazevVeci;
                       }
                       pomocna++;
                    }

            if(!hra.konecHry())
            {
            vstupniText.setText("poloz " + nazev);
            odesliPrikaz();
            }
        }
        
        @FXML public void Mistnost() 
        {
            String nazev = listVychodu.getSelectionModel().getSelectedItem().toString();
            if(!hra.konecHry())
            {
            vstupniText.setText("jdi " + nazev);
            odesliPrikaz();
            }
        }
        
        @FXML public void VecVMistnosti() 
        {
                    Map<String, Vec> seznam = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();
                    int index = listVeciMistnost.getSelectionModel().getSelectedIndex();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String nazevVeci : seznam.keySet()) 
                    {
                       if(pomocna == index)
                       {
                           nazev = nazevVeci;
                       }
                       pomocna++;
                    }
            if(!hra.konecHry())
            {
            vstupniText.setText("seber " + nazev);
            odesliPrikaz();
            }
        }
        
        @FXML public void novaHra() 
        {
                hra = new Hra();
		vystup.setText(hra.vratUvitani());
		vstupniText.setDisable(false);
                hra.getHerniPlan().addObserver(this);
                hra.getHerniPlan().notifyObservers();
        }
        
        @FXML public void konecHry() 
        {
            vstupniText.setText("konec");
            odesliPrikaz();
        }
        
         @FXML public void Napoveda() 
        {
            Stage stage = new Stage();
            stage.setTitle("Nápověda");
            
            WebView webView = new WebView();               
            webView.getEngine().load(com.github.Jay.Adventura.main.Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
            
            stage.setScene(new Scene(webView, 1200, 650));
            stage.show();
        }

	@Override
	public void update(Observable arg0, Object arg1) 
        {
		rytir.setX(hra.getHerniPlan().getAktualniProstor().getPosX());
		rytir.setY(hra.getHerniPlan().getAktualniProstor().getPosY());
            
                veciMistnost.clear();
                veciBatoh.clear();
                vychody.clear();
                
                
		Set<Prostor> seznamProstoru = hra.getHerniPlan().getAktualniProstor().getVychody();
                for(Prostor prostor : seznamProstoru)
                {
                    vychody.add(prostor.getNazev());
                }
                
                Map<String, Vec> seznamBatohu = hra.getHerniPlan().getBatoh().vratSeznamVeci();
                for (String nazevVeci : seznamBatohu.keySet()) 
                {
                    Vec pomocna = seznamBatohu.get(nazevVeci);
                    ImageView obrazek = new ImageView(new Image(com.github.Jay.Adventura.main.Main.class.getResourceAsStream("/zdroje/"+pomocna.getUrl()), 100, 100, false, false));
                    veciBatoh.add(obrazek);
                }
                
                Map<String, Vec> seznamMistnost = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();
                for (String nazevVeci : seznamMistnost.keySet()) 
                {
                    Vec pomocna = seznamMistnost.get(nazevVeci);
                    ImageView obrazek = new ImageView(new Image(com.github.Jay.Adventura.main.Main.class.getResourceAsStream("/zdroje/"+pomocna.getUrl()), 100, 100, false, false));
                    veciMistnost.add(obrazek);
                }
	}

}

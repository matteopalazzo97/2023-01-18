/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.nyc;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import it.polito.tdp.nyc.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProvider"
    private ComboBox<String> cmbProvider; // Value injected by FXMLLoader

    @FXML // fx:id="txtDistanza"
    private TextField txtDistanza; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtStringa"
    private TextField txtStringa; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtTarget"
    private ComboBox<?> txtTarget; // Value injected by FXMLLoader

    @FXML
    void doAnalisiGrafo(ActionEvent event) {
    	
    	this.txtResult.appendText("\n\n");
    	this.txtResult.appendText("Vertici con più vicini:\n");
    	
    	Map<String, Integer> mappaMax = this.model.getNodiMax();
    	
    	for(String s: mappaMax.keySet()) {
    		this.txtResult.appendText("\n" + s + ", # vicini: " + mappaMax.get(s));
    	}
    	
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	String provider;
    	
    	if(this.cmbProvider.getValue() == null) {
    		this.txtResult.setText("Seleziona un valore dalla tendina.");
    		return;
    	} else {
    		provider = this.cmbProvider.getValue();
    	}
    	
    	Double x;
    	
    	if(this.txtDistanza.getText() == "") {
    		this.txtResult.setText("Inserisci un valore per la distanza.");
    		return;
    	}
    	
    	try {
    		x = Double.parseDouble(this.txtDistanza.getText());
    	} catch (NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.setText("Inserire un valore numerico per la distanza.");
    		return;
    	}
    	
    	this.model.creaGrafo(provider, x);
    	
    	this.txtResult.setText("Grafo creato.\n");
    	this.txtResult.appendText("Vertici: " + this.model.getNumVertici());
    	this.txtResult.appendText("\nArchi:   " + this.model.getNumArchi());

    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProvider != null : "fx:id=\"cmbProvider\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDistanza != null : "fx:id=\"txtDistanza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStringa != null : "fx:id=\"txtStringa\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
    	this.model = model;
    	
    	this.cmbProvider.getItems().addAll(this.model.tendina());
    }
}

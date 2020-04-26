package hellofx;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;


public class CoolController {
	
	BlueMarble bm = new BlueMarble();
	
    @FXML
    private ImageView background;
    
    @FXML
    private CheckBox blackNWhiteCheckbox;
	
    @FXML
    private MenuItem ChooseImageByDate;
    
    @FXML
    private MenuItem advancedButton;

    @FXML
    private DatePicker datePickerBox;

    @FXML
    private Button confirmDate;
    
    @FXML
    private Button testButton;
    
    @FXML
    private Rectangle popupBackground;
    
    @FXML
    private CheckBox hdCheckbox;
    
    @FXML
    private AnchorPane warningPopupPane;

    @FXML
    void confirmDate(ActionEvent event) throws IOException {  
    	
    	
    	
    	LocalDate enhancedStartDate = LocalDate.parse("2018-06-01");
    	
    	if (!datePickerBox.getValue().plusDays(-1).isBefore(bm.getDate())) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Warning");
    		alert.setHeaderText(null);
    		alert.setContentText("Date is in the future.");
    		hdCheckbox.setSelected(false);
    		alert.showAndWait();
    		throw new RuntimeException("Chosen date invalid. Date set to the futue");
    	}  
    	
    	if (datePickerBox.getValue().isBefore(enhancedStartDate) && hdCheckbox.isSelected()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Warning");
    		alert.setHeaderText(null);
    		alert.setContentText("Picture was captured prior to June 1st 2018, only the standard photo is available!");
    		hdCheckbox.setSelected(false);
    		alert.showAndWait();
    	}
    	

    	
    	datePickerBox.setVisible(false);
    	confirmDate.setVisible(false);
    	popupBackground.setVisible(false);
    	hdCheckbox.setVisible(false);    	    
    	blackNWhiteCheckbox.setVisible(false);

    	try {   		
    	bm.setDate(datePickerBox.getValue().toString().replace('-', '/'));
    	System.out.println(datePickerBox.getValue().minusDays(0));
    	Image image = new Image(bm.specificImage(datePickerBox.getValue().toString(), hdCheckbox.isSelected()));
        
    	
    	if (blackNWhiteCheckbox.isSelected()) {
    	    BufferedImage buffBoi = ImageIO.read(bm.specificImage(datePickerBox.getValue().toString(), hdCheckbox.isSelected()));
    		File outputfile = new File("image.png");
    		bm.blackAndWhite(buffBoi);
    	    ImageIO.write(buffBoi, "png", outputfile);
    	    BufferedImage myImg = ImageIO.read(bm.blackAndWhite(buffBoi));
    	    image = SwingFXUtils.toFXImage(myImg, null);
    	    background.setImage(image);
    	}
		background.setImage(image);
		hdCheckbox.setSelected(false);
    	blackNWhiteCheckbox.setSelected(false);
		//throw new RuntimeException("Chosen date invalid. Date set to the futue");
    	}

    	
  	catch (RuntimeException e) {
  		
  		e.printStackTrace();
  		if (hdCheckbox.isSelected()) {
  			Alert alert = new Alert(AlertType.INFORMATION);
  			alert.setTitle("Warning");
    		alert.setHeaderText(null);
    		alert.setContentText("HD version doesn't exist for this particular date!");
    		hdCheckbox.setSelected(false);
    		alert.showAndWait();
  		} else {
  			Alert alert = new Alert(AlertType.INFORMATION);
  			alert.setTitle("Warning");
    		alert.setHeaderText(null);
    		alert.setContentText("Image doesn't exist yet.");
    		hdCheckbox.setSelected(false);
    		alert.showAndWait();
  		}
  		
   		}
    	
    }
    
    @FXML
    void showDatePicker(ActionEvent event) {
    	hdCheckbox.setSelected(false);
    	blackNWhiteCheckbox.setSelected(false);
    	blackNWhiteCheckbox.setVisible(false);
    	hdCheckbox.setVisible(false);
    	datePickerBox.setVisible(true);
    	confirmDate.setVisible(true);
    	popupBackground.setVisible(true);	
    }
    
    @FXML
    void showAdvancedMenu(ActionEvent event) {
    	hdCheckbox.setSelected(false);
    	blackNWhiteCheckbox.setSelected(false);
    	blackNWhiteCheckbox.setVisible(true);
    	datePickerBox.setVisible(true);
    	confirmDate.setVisible(true);
    	popupBackground.setVisible(true);
    	hdCheckbox.setVisible(true);
    }

}

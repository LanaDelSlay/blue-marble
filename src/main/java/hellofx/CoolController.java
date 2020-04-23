package hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class CoolController {
	
	BlueMarble bm = new BlueMarble();
    @FXML
    private ImageView background;
	
    @FXML
    private MenuItem ChooseImageByDate;

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
    void confirmDate(ActionEvent event) {   	
    	if (!datePickerBox.getValue().plusDays(-1).isBefore(bm.getDate())) {
    		throw new RuntimeException("Chosen date invalid. Date set to the futue");
    	}  
    	
    	if (hdCheckbox.isSelected()) {
    		bm.setEnhanced(true);
    	} else bm.setEnhanced(false);
    	
    	datePickerBox.setVisible(false);
    	confirmDate.setVisible(false);
    	popupBackground.setVisible(false);
    	hdCheckbox.setVisible(false);
    	Image image = new Image(bm.getMostRecentImage());
    	bm.setDate(datePickerBox.getValue().toString());
		background.setImage(image);
		//throw new RuntimeException("Chosen date invalid. Date set to the futue");
    }
    
    @FXML
    void showDatePicker(ActionEvent event) {
    	datePickerBox.setVisible(true);
    	confirmDate.setVisible(true);
    	popupBackground.setVisible(true);	
    }
    
    @FXML
    void showAdvancedMenu(ActionEvent event) {
    	datePickerBox.setVisible(true);
    	confirmDate.setVisible(true);
    	popupBackground.setVisible(true);
    	hdCheckbox.setVisible(true);
    }

}

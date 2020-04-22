package hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    void confirmDate(ActionEvent event) {
    	datePickerBox.setVisible(false);
    	confirmDate.setVisible(false);
    	popupBackground.setVisible(false);
    	//Image image = new Image(bm.getMostRecentImage());
    	System.out.println("Pressed");
    	//background.setImage(image);
    	bm.setDate(datePickerBox.getValue().toString());
    	System.out.println(datePickerBox.getValue().toString());
    	Image image = new Image(bm.getImage());
		background.setImage(image);
    }
    
    @FXML
    void showDatePicker(ActionEvent event) {
    	datePickerBox.setVisible(true);
    	confirmDate.setVisible(true);
    	popupBackground.setVisible(true);
    	
    	
    }

}

package hellofx;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import javafx.scene.image.Image;

public class BlueMarble {
	//https://epic.gsfc.nasa.gov/archive/natural/2015/10/31/png/epic_1b_20151031074844.png
	private String API_KEY = "7u1nv3v73ROS0u2F65J7w14pnGpjzwCv6cruBzes";
	private String dateAsString;
	private String quality = "natural";
	private String caption;
	private String nasaImageName;
	private LocalDate today = java.time.LocalDate.now();
  //  private Date date = new Date();  


	public static InputStream getMostRecentImage() {
		BlueMarble blueMarble = new BlueMarble();
		blueMarble.setDate(LocalDate.now().minusDays(1).toString());
		return blueMarble.getImage();
		
	}
	
	public InputStream specificImage(String date, boolean isHD)  {
		BlueMarble bm = new BlueMarble();
		
		bm.setDate(date);
		if (isHD) {
			bm.quality = "enhanced";
		} 
		return bm.getImage();
		
	}
	
	public LocalDate getDate() {
		return today;
	}
	
	public File blackAndWhite(BufferedImage img) {

		try {
			BufferedImage image = img;
			 BufferedImage result = new BufferedImage(
	                    image.getWidth(),
	                    image.getHeight(),
	                    BufferedImage.TYPE_INT_RGB);

	            Graphics2D graphic = result.createGraphics();
	            graphic.drawImage(image, 0, 0, Color.WHITE, null);

	            
	            for (int i = 0; i < result.getHeight(); i++) {
	                for (int j = 0; j < result.getWidth(); j++) {
	                    Color c = new Color(result.getRGB(j, i));
	                    int red = (int) (c.getRed() * 0.299);
	                    int green = (int) (c.getGreen() * 0.587);
	                    int blue = (int) (c.getBlue() * 0.114);
	                    Color newColor = new Color(
	                            red + green + blue,
	                            red + green + blue,
	                            red + green + blue);
	                    result.setRGB(j, i, newColor.getRGB());
	                }
	            }
	            File BWoutput = new File("/tmp/test.png");
	            ImageIO.write(result, "png", BWoutput);
	            return BWoutput;
		} catch (IOException e) {
            e.printStackTrace();
        }
		return null;
		
	}
	
	public void setDate(String date) {
		this.dateAsString = date;
	}
	
	public InputStream getImage() {
		try {
			getMetaData();

			URL url = new URL("https://api.nasa.gov/EPIC/archive/" + quality + "/" + dateAsString.replace('-', '/')
					+ "/png/" + this.nasaImageName + ".png?api_key=" + API_KEY);
			return url.openStream();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void getMetaData() throws IOException, MalformedURLException {
		String metaQueryURL = "https://epic.gsfc.nasa.gov/api/" + quality + "/date/" + dateAsString;
		InputStream metaInfoStream = new URL(metaQueryURL).openStream();
		String metaInfoJSON = IOUtils.toString(metaInfoStream, "UTF-8").replace("[", "");
		//System.out.println(metaInfoJSON);
		metaInfoStream.close();
		JSONObject json = new JSONObject(metaInfoJSON);
		this.nasaImageName = (String) json.get("image");
		this.caption = (String) json.get("caption");
	}

	public String getCaption() {
		return this.caption;
	}

	public void setEnhanced(boolean b) {
		if (b = true) {
			this.quality = "enhanced";
		} else this.quality = "natural";
		
	}
}

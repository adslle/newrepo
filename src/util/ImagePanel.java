package util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	
	private BufferedImage pic;
	private File f;
	public BufferedImage getPic() {
		return pic;
	}

	public void setPic(BufferedImage pic) {
		this.pic = pic;
	}

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

	public ImagePanel(File f) { 
	   try {      
		   this.f=f;
	      pic = ImageIO.read(f); 
	   } catch (IOException ex) {
	      
	   }
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(pic, 0, 0,this.getWidth(),this.getHeight(), null); // see javadoc for more info on the parameters            
	}
	public void setPic(Image pic,File f) {
	    this.pic = (BufferedImage) pic; 
	    this.f=f;
	    repaint();   
	}

}
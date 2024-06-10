package GUI;
import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Label(String text, int x, int y, int width, int height, int fontSize){
		super(text);
		setBounds(x,y,width,height);
		setFont(new Font("Arial", Font.PLAIN, fontSize));
	}
}

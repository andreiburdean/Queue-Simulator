package GUI;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Button extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Button(String text, int x, int y, int width, int height, int fontSize) {
        super(text);
        setBounds(x, y, width, height);
        setFont(new Font("Arial", Font.PLAIN, fontSize));
    }
    
    public void addActionListener(ActionListener buttonClickListener) {
        super.addActionListener(buttonClickListener);
    }
}

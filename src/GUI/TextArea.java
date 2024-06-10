package GUI;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class TextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TextArea(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Arial", Font.PLAIN, 15));
        setEditable(false);
    }
    
	public void setSimArea(String info) {
        this.setText(info);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

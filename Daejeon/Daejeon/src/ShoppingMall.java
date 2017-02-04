import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ShoppingMall extends JFrame implements ActionListener{
	private JLabel l1, l2;
	private JTextField t1;
	private JPasswordField pt1;
	private JPanel p1;
	private JButton b1;
	
	public ShoppingMall(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p1=new JPanel(new GridLayout(0, 3));
		
		l1=new JLabel("아이디");
		t1=new JTextField(10);
		l2=new JLabel("비밀번호");
		pt1=new JPasswordField(10);
		
		b1=new JButton("LOGIN");
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ShoppingMall();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

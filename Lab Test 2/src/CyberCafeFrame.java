import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CyberCafeFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private CyberCafe cyberCafe = new CyberCafe();
	private JButton btnStartUsage = new JButton("start usage");
	private JButton btnEndUsage = new JButton("end usage");
	private JButton btnViewTotal = new JButton("view usage");
	private double total;
	
	public CyberCafeFrame() 
	{
		super("Cyber Cafe Management System");
		
		GridLayout layout = new GridLayout(3, 1, 0, 0);
		
		btnStartUsage.addActionListener(this);
		btnEndUsage.addActionListener(this);
		btnViewTotal.addActionListener(this);
		
		this.add(btnStartUsage);
		this.add(btnEndUsage);
		this.add(btnViewTotal);
		
		this.setSize(350, 200);
		this.setResizable(false);
		this.setLayout(layout);
		this.setLocationRelativeTo(null);
		
		// BONUS MARK 1 (At First Load, Don't let user close on exit first) - Maybe Just open the shop
		// No customer yet
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object source = event.getSource();
		
		if(source == btnStartUsage)
		{
			int computerNo = cyberCafe.getAvailableComputerNo();
			
			//System.out.println(computerNo);
			
			if(computerNo == -1)
			{
				JOptionPane.showMessageDialog(this, "Sorry, all computers are being used.", "Cyber Cafe Management System", JOptionPane.WARNING_MESSAGE);
			}
			else if(computerNo != -1)
			{
				String userIC = JOptionPane.showInputDialog(this, "Please enter the IC number:","Cyber Cafe Management System", JOptionPane.QUESTION_MESSAGE);
				
				if(userIC.isEmpty() == false)
				{
					User user = new User(userIC);
					
					// Start the Usage
					cyberCafe.startUsage(computerNo, user);
					
					int computerCount = computerNo+1;
					
					// Success Message
					JOptionPane.showMessageDialog(this, "The user " + userIC + " is assigned computer number " + computerCount + ".", "Cyber Cafe Management System", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else if(source == btnEndUsage)
		{
			if(cyberCafe.isEmpty()) 
			{
				JOptionPane.showMessageDialog(this, "There are no computers being used.", "Cyber Cafe Management System", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				String[] availableIC = cyberCafe.getIcNos();
				
				//System.out.println(availableIC);
		        Object selectedUser = JOptionPane.showInputDialog(this, "Please select the IC number:", "Cyber Cafe Management System", JOptionPane.QUESTION_MESSAGE, null, availableIC, availableIC[0]);

		        if(selectedUser != null)
		        {
		        	//System.out.println(selectedUser);
		        	total = cyberCafe.endUsage(selectedUser.toString());
		        	
		        	JOptionPane.showMessageDialog(this, "Usage fee for user " + selectedUser.toString() + " is RM" + total + ".", "Cyber Cafe Management System", JOptionPane.INFORMATION_MESSAGE);
		        }
		        
		        // Check if any computer being used (-1 means still got computer in used, else no computer in used)
		        if(cyberCafe.getAvailableComputerNo() != -1)
		        {
		        	//BONUS MARK 2
		    		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        }
			}
		}
		else if(source == btnViewTotal)
		{
			JOptionPane.showMessageDialog(this, "Total usage fee received is RM " + total + ".", "Cyber Cafe Management System", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void main(String[] args) 
	{
		new CyberCafeFrame();
	}

}

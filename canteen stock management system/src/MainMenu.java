import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener
{   JFrame jf;
	JMenuBar mbar;
	JMenu m1,m2,m3,m4,m5,m6,m7,m8;
	JMenuItem m1_1,m1_2,m1_3,m1_4,m1_5,m2_1,m2_2,m2_3,m2_4,m2_5,m3_1,m3_2,m3_3,m3_4,m3_5,m3_6,m4_1,m4_2,m4_3,m4_4,m4_5,m5_1,m5_2,m5_3,m5_4,m5_5,m6_1,m6_2,m7_1,m8_1;
	JLabel l1,LogoColl;
	GridBagLayout gbl;

	public MainMenu()
	{
        jf=new JFrame();
		gbl=new GridBagLayout();
		jf.setLayout(gbl);

		l1=new JLabel("CANTEEN STOCK MANAGEMENT SYSTEM");
		l1.setFont(new Font("Times New Roman",Font.BOLD,20));
		jf.add(l1);

		mbar = new JMenuBar();
		jf.setJMenuBar(mbar);

		m1=new JMenu("distributor");
		mbar.add(m1);
		m1_1 = new JMenuItem("Add New distributor.",new ImageIcon("images//addnew.png"));
		m1.add(m1_1);
		m1_2 = new JMenuItem("search distributor.",new ImageIcon("images//search.png"));
		m1.add(m1_2);
		m1_3 = new JMenuItem("Update distributor.",new ImageIcon("images//update.png"));
		m1.add(m1_3);
		m1_4 = new JMenuItem("Delete distributor.",new ImageIcon("images//delete.png"));
		m1.add(m1_4);
	    m1_5 = new JMenuItem("List of distributor.",new ImageIcon("images//all.png"));
		m1.add(m1_5);

		m2=new JMenu("Vegetables");
		mbar.add(m2);
		m2_1 = new JMenuItem("Add New Vegetables ",new ImageIcon("images//addnew.png"));
		m2.add(m2_1);
		m2_2 = new JMenuItem("search Vegetables",new ImageIcon("images//search.png"));
		m2.add(m2_2);
		m2_3 = new JMenuItem("Update Vegetables",new ImageIcon("images//update.png"));
		m2.add(m2_3);
		m2_4 = new JMenuItem("Delete Vegetables",new ImageIcon("images//delete.png"));
		m2.add(m2_4);
	    m2_5 = new JMenuItem("list of Vegetables",new ImageIcon("images//all.png"));
		m2.add(m2_5);
		
		
		m3=new JMenu("Grocery");
		mbar.add(m3);
		m3_1 = new JMenuItem("Add New Grocery",new ImageIcon("images//addnew.png"));
		m3.add(m3_1);
		m3_2 = new JMenuItem("search Grocery",new ImageIcon("images//search.png"));
		m3.add(m3_2);
		m3_3 = new JMenuItem("Update Grocery",new ImageIcon("images//update.png"));
		m3.add(m3_3);
		m3_4 = new JMenuItem("Delete Grocery",new ImageIcon("images//delete.png"));
		m3.add(m3_4);
	    m3_5 = new JMenuItem("list of Grocery",new ImageIcon("images//all.png"));
		m3.add(m3_5);


		m4=new JMenu("About");
		mbar.add(m4);
		m4_1 = new JMenuItem("About System",new ImageIcon("images//help.png"));
		m4.add(m4_1);

		m5=new JMenu("Exit");
		mbar.add(m5);
		m5_1 = new JMenuItem("Exit",new ImageIcon("images//exit.png"));
		m5.add(m5_1);

		

		
        m1_1.addActionListener(this);
		m1_2.addActionListener(this);
		m1_3.addActionListener(this);
		m1_4.addActionListener(this);
    	m1_5.addActionListener(this);

		m2_1.addActionListener(this);
		m2_2.addActionListener(this);
		m2_3.addActionListener(this);
		m2_4.addActionListener(this);
	    m2_5.addActionListener(this);


        m3_1.addActionListener(this);
		m3_2.addActionListener(this);
		m3_3.addActionListener(this);
		m3_4.addActionListener(this);
    	m3_5.addActionListener(this);

		m4_1.addActionListener(this);
		
	
		
		m5_1.addActionListener(this);
	
	


		jf.setTitle("Main Menu");
		jf.setLocation(20,20);
	    jf.setSize(900,700);
	    jf.setResizable(false);
		jf.getContentPane().setBackground(Color.cyan);
		jf.setVisible(true);

	}

	public void actionPerformed(ActionEvent ae)
	{

	  if(ae.getSource()==m1_1)
		{
		  new AddNewDistributor();
		}
		else if(ae.getSource()==m1_2)
		{
		 new SearchDistributor();
		}
		else if(ae.getSource()==m1_3)
		{
		new UpdateDistributor();
		}
		else if(ae.getSource()==m1_4)
		{
		new DeleteDistributor();
		}
	    else if(ae.getSource()==m1_5)
		{
		  new DistributorList();
		}


		else if(ae.getSource()==m2_1)
		{
			new AddNewVegetable();
		}
		else if(ae.getSource()==m2_2)
		{
			new SearchVegetable();
		}
		else if(ae.getSource()==m2_3)
		{
			new UpdateVegetable();
		}
		else if(ae.getSource()==m2_4)
		{
			new DeleteVegetable();
		}
		else if(ae.getSource()==m2_5)
		{
			new VegetableList();
		}

		else if(ae.getSource()==m3_1)
		{
		  new AddNewGrocery();
		}
		else if(ae.getSource()==m3_2)
		{
		 new SearchGrocery();
		}
		else if(ae.getSource()==m3_3)
		{
		new UpdateGrocery();
		}
		else if(ae.getSource()==m3_4)
		{
		new DeleteGrocery();
		}
	    else if(ae.getSource()==m3_5)
		{
		  new GroceryList();
		}
	  
		else if(ae.getSource()==m4_1)
		{
	        new About();
		}

		else if(ae.getSource()==m5_1)
		{
		  System.exit(0);
		}
	

  }

	public static void main(String args[])
	{
		new MainMenu();
	}
}
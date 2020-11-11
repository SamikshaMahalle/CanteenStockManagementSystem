import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class AddNewDistributor extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField t1,t2,t3,t4,t5,tr,tq;
	JLabel l1,l2,l3,l4,l5,l6;
	JButton b0,b1,b2;
	Font f;
    Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

	AddNewDistributor()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,20);
		jf.setLayout(null);

	    l6=new JLabel("New Distributor details");
	    l6.setFont(new Font("Times New Roman",Font.BOLD,25));
	    l6.setBounds(250,50,300,40);l6.setForeground(Color.blue);
	    jf.add(l6);

		 l1= new JLabel("Distributor id ");
		// l1.setFont(f);
		 l1.setBounds(150,120,130,25);
		 jf.add(l1);

		 t1=new JTextField(20);
		 t1.setBounds(320,120,100,25);t1.setToolTipText("Enter distributor id");
		 jf.add(t1);

		l2 = new JLabel("Distributor name*");
		//l2.setFont(f);
        l2.setBounds(150,160,170,25);
		jf.add(l2);

		t2=new JTextField(20);
		t2.setBounds(320,160,200,25);t2.setToolTipText("Enter distributor name");
		jf.add(t2);

		l3 = new JLabel("Distributor address*");
		//l3.setFont(f);
        l3.setBounds(150,200,170,25);
		jf.add(l3);

		t3=new JTextField(20);
		t3.setBounds(320,200,250,25);t3.setToolTipText("Enter distributor address");
		jf.add(t3);

		l4 = new JLabel("Distributor phone no*");
		//l4.setFont(f);
        l4.setBounds(150,240,170,25);
		jf.add(l4);

		t4=new JTextField(20);
		t4.setBounds(320,240,100,25);t4.setToolTipText("Enter distributor phone no");
		jf.add(t4);

		l5 = new JLabel("Distributor email id*");
		//l5.setFont(f);
        l5.setBounds(150,280,170,25);
		jf.add(l5);

		t5=new JTextField(20);
		t5.setBounds(320,280,200,25);t5.setToolTipText("Enter distributor email id");
		jf.add(t5);

	    b0 = new JButton("Save",new ImageIcon("images//save.png"));
        b0.setBounds(150,330,110,35);b0.setToolTipText("click to save distributor details");
		jf.add(b0);b0.addActionListener(this);

		b1 = new JButton("Clear",new ImageIcon("images//clear.png"));
		b1.setBounds(300,330,110,35);b1.setToolTipText("click to clear all textfilds");
	    jf.add(b1); b1.addActionListener(this);

        b2= new JButton("All",new ImageIcon("images//all.png"));
		b2.setBounds(450,330,110,35);b2.setToolTipText("click to view all distributor details");
		jf.add(b2); b2.addActionListener(this);

	    scrlPane.setBounds(0,380,900,600);
        jf.add(scrlPane);
        tabGrid.setFont(new Font ("Times New Roman",0,15));

        model.addColumn("D_ID");
        model.addColumn("D_NAME");
        model.addColumn("D_ADDRESS");
        model.addColumn("D_PHONENO");
        model.addColumn("D_EMAILID");

	     jf.setTitle("Add New Distributor");
	     jf.setSize(900,700);
		 jf.setLocation(20,20);
		 jf.setResizable(false);
		 jf.getContentPane().setBackground(Color.cyan);
	     jf.setVisible(true);
	}

public void actionPerformed(ActionEvent ae)
	{
	if(ae.getSource()==b0)
	 {
	 	    String mob = t4.getText();
	       	String email=t5.getText();
	       	Pattern p=Pattern.compile("[_a-z_A-Z_0-9]+[0-9]*@[a-zA-Z0-9]+.[a-zA-Z0-9]+");
	       	Matcher m=p.matcher(email);
	        boolean matchFound=m.matches();

	    	if(((t2.getText()).equals(""))||((t3.getText()).equals(""))||((t4.getText()).equals(""))||((t5.getText()).equals("")))
	        {
		    JOptionPane.showMessageDialog(this,"* Detail are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
	        }
	        else if(!matchFound)
	        {
	       	  JOptionPane.showMessageDialog(this,"Invalid email id!","Warning!!!",JOptionPane.WARNING_MESSAGE);
	       	}
			else
			{
			  try
			  	 {
			Class.forName("com.mysql.jdbc.Driver");
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_stock?useSSL=false","root","root");
			System.out.println("Connected to database.");
            ps=con.prepareStatement("insert into distributor (dname,daddress,dphoneno,demailid)values(?,?,?,?)");
            ps.setString(1,t2.getText());
		    ps.setString(2,t3.getText());
		    ps.setString(3,t4.getText());
			ps.setString(4,t5.getText());
		  	ps.executeUpdate();

  int reply=JOptionPane.showConfirmDialog(null,"Distributor added successfully.Do you want add more distributor?","Added Distributor",JOptionPane.YES_NO_OPTION);

	             if (reply == JOptionPane.YES_OPTION)
	   			{
	   		       jf.setVisible(false);
	   		       new AddNewDistributor();
	   		    }
	   		  else if (reply == JOptionPane.NO_OPTION)
	   			{
	   			  jf.setVisible(false);
		        }con.close();
	          }
   catch(SQLException se)
   {
     System.out.println(se);
     JOptionPane.showMessageDialog(null,"SQL Error:"+se);
   }
  catch(Exception e)
  {
    System.out.println(e);
    JOptionPane.showMessageDialog(null,"Error:"+e);
  }
 }
}
  else if(ae.getSource()==b1)
     {//clear
          //t1.setText("");
          t2.setText("");
          t3.setText("");
          t4.setText("");
          t5.setText("");
      }
	    {//list
  	int r = 0;
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_stock?useSSL=false","root","root");
		System.out.println("Connected to database.");
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("select * from distributor group by did asc" );
          while(rs.next())
            {
            	model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5) });
            }
             con.close();
       }
      catch(SQLException se)
       {
        System.out.println(se);
        JOptionPane.showMessageDialog(null,"SQL Error:"+se);
       }
      catch(Exception e)
       {
       	   System.out.println(e);
          JOptionPane.showMessageDialog(null,"Error:"+e);
       }
	}
 }
public static void main(String args[])
	{
	      new AddNewDistributor();
	}
}

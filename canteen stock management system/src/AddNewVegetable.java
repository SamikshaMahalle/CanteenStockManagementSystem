import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
 
 
public class AddNewVegetable extends JFrame implements ActionListener
{
    JFrame jf;
    JTextField t1,t2,t3,t4,t5,t6,t8,t9,t10;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,ln;
    JButton b0,b1,b2;
    JComboBox vdname,vegtype;
    String d,did1,veg;
    Font f;
    Date date1;
    GregorianCalendar calendar;
    String strDate;
    Connection con;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
 
    AddNewVegetable()
    {
        jf=new JFrame();
        f = new Font("Times New Roman",Font.BOLD,15);
        jf.setLayout(null);
 
        ln=new JLabel("New Vegetable details");
        ln.setFont(new Font("Times New Roman",Font.BOLD,25));
        ln.setForeground(Color.blue);
        ln.setBounds(300,30,400,40);
        jf.add(ln);
 
        l1 = new JLabel("Vegetable Batch no*");
        //l1.setFont(f);
    l1.setBounds(50,100,200,25);
        jf.add(l1);
 
        t1 = new JTextField(20);
        t1.setBounds(250,100,100,25);t1.setToolTipText("Enter vegetable batch no");
        jf.add(t1);
 
        l2 = new JLabel("Vegetable name*");
        //l2.setFont(f);
    l2.setBounds(50,140,200,25);
        jf.add(l2);
 
        t2 = new JTextField(20);
        t2.setBounds(250,140,200,25);t2.setToolTipText("Enter vegetable name");
        jf.add(t2);
 
        l3 = new JLabel("Vegetable company*");
        //l3.setFont(f);
    l3.setBounds(50,180,200,25);
        jf.add(l3);
 
         t3 = new JTextField(20);
        t3.setBounds(250,180,200,25);t3.setToolTipText("Enter vegetable company");
        jf.add(t3);
 
        l4 = new JLabel("Vegetable quantity*");
        //l4.setFont(f);
   l4.setBounds(50,220,200,25);
        jf.add(l4);
 
        t4= new JTextField(20);
        t4.setBounds(250,220,100,25);t4.setToolTipText("Enter vegetable quantity");
        jf.add(t4);
 
        l5= new JLabel("Veg expiry date*");
        //l5.setFont(f);
    l5.setBounds(50,260,250,25);
        jf.add(l5);
 
        t5= new JTextField(20);
        t5.setBounds(250,260,100,25);t5.setToolTipText("Enter vegetable expiry date");
        jf.add(t5);
 
        l6= new JLabel("Veg purchase date*");
        //l6.setFont(f);
    l6.setBounds(50,300,260,25);
        jf.add(l6);
 
        t6= new JTextField(20);
        t6.setBounds(250,300,100,25);t6.setToolTipText("Enter vegetable expiry date");
        jf.add(t6);
 
        date1= new Date();
         calendar=new GregorianCalendar();
        calendar.setTime(date1);
        strDate =calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
        t6.setText(strDate);
 
        l7 = new JLabel("Vegetable type*");
        //l7.setFont(f);
   l7.setBounds(470,100,200,25);
        jf.add(l7);
 
        vegtype=new JComboBox();
        vegtype.addItem("--type--");
        vegtype.addItem("leafy vegetable");
        vegtype.addItem("fruits");
        vegtype.addItem("beans");
        vegtype.addItem("veggies");
         
         
        vegtype.setBounds(720,100,100,25);vegtype.setToolTipText("Select vegetable type");
        jf.add(vegtype);
        vegtype.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent ae)
           {
            veg =(String)vegtype.getSelectedItem();
           }
          });
 
        l8= new JLabel("Vegetables purchase price*");
        //l8.setFont(f);
    l8.setBounds(470,140,220,25);
        jf.add(l8);
 
        t8 = new JTextField(20);
        t8.setBounds(720,140,100,25);t8.setToolTipText("Enter vegetable purchase price");
        jf.add(t8);
 
        l9 = new JLabel("Vegetable Sale Price*");
        //l9.setFont(f);
   l9.setBounds(470,180,200,25);
        jf.add(l9);
 
        t9 = new JTextField(20);
        t9.setBounds(720,180,100,25);t9.setToolTipText("Enter vegetable sale price");
        jf.add(t9);
 
        l10 = new JLabel("Vegetable Rack No*");
        //l10.setFont(f);
  l10.setBounds(470,220,200,25);
        jf.add(l10);
 
        t10 = new JTextField(20);
        t10.setBounds(720,220,100,25);t10.setToolTipText("Enter vegetable rack no");
        jf.add(t10);
 
        l11 = new JLabel("Distributor name*");
        //l11.setFont(f);
    l11.setBounds(470,260,250,25);
        jf.add(l11);
 
 
        vdname=new JComboBox();
        vdname.setBounds(720,260,130,25);vdname.setToolTipText("select vegetable distributor name");
        jf.add(vdname);
        vdname.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent ae)
           {
            d =(String)vdname.getSelectedItem();
           }
          });//////////////////
 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_stock?useSSL=false","root","root");
            System.out.println("Connected to database.");
             ps=con.prepareStatement("select dname from distributor");
            rs=ps.executeQuery();
            while(rs.next())
            {
             String sname1=rs.getString(1);
             vdname.addItem(sname1);
            }
 
        con.close();
       }
       catch(SQLException se)
       {
       System.out.println(se);
      }
      catch(Exception e)
       {
       System.out.println(e);
       }
 
        b0 = new JButton("Save",new ImageIcon("images//save.png"));
        b0.setBounds(150,330,110,35);b0.setToolTipText("click to save vegetable details");
        jf.add(b0);b0.addActionListener(this);
 
        b1 = new JButton("Clear",new ImageIcon("images//clear.png"));
        b1.setBounds(300,330,110,35);b1.setToolTipText("click to clear all textfields");
        jf.add(b1); b1.addActionListener(this);
 
        b2= new JButton("All",new ImageIcon("images//all.png"));
        b2.setBounds(450,330,110,35);b2.setToolTipText("click to view all vegetable details");
        jf.add(b2); b2.addActionListener(this);
 
        scrlPane.setBounds(0,380,900,600);
        jf.add(scrlPane);
        tabGrid.setFont(new Font ("Times New Roman",0,15));
 
        model.addColumn("V_BNO");
        model.addColumn("V_NAME");
        model.addColumn("V_COMPANY");
        model.addColumn("V_QUANTITY");
        model.addColumn("V_EXPDATE");
        model.addColumn("V_PURDATE");
        model.addColumn("V_TYPE");
        model.addColumn("V_SALEPRICE");
        model.addColumn("V_PURPRICE");
        model.addColumn("V_RACKNO");
        model.addColumn("V_DID");
        model.addColumn("V_DNAME");
 
         jf.setTitle("Add New Vegetable ");
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
       try
        {
            if(((t1.getText()).equals(""))||((t2.getText()).equals(""))||((t3.getText()).equals(""))||((t4.getText()).equals(""))||((t5.getText()).equals(""))||((t6.getText()).equals(""))||
            ((t8.getText()).equals(""))||((t9.getText()).equals(""))||((t10.getText()).equals("")))
            {
            JOptionPane.showMessageDialog(this,"* Details are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                    float a=Float.parseFloat(t8.getText());
                    float b=Float.parseFloat(t9.getText());
                if(b<a)
                {
                JOptionPane.showMessageDialog(this,"sale price should be greater than puchase price!","Warning!!!",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
 
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_stock","root","root");
            System.out.println("Connected to database.");
 
            ps=con.prepareStatement("Select did from distributor where dname='"+d+"'");
              rs=ps.executeQuery();
              while(rs.next())
              {
                   did1=rs.getString(1);
              }
 ps=con.prepareStatement("insert into vegetable (vbno,vname,vcompany,vqty,vexpdate,vpurdate,vtype,vpurprice,vsaleprice,vrackno,did,dname)values(?,?,?,?,?,?,?,?,?,?,?,?)");
 
            ps.setString(1,t1.getText());
            ps.setString(2,t2.getText());
            ps.setString(3,t3.getText());
            ps.setInt(4,Integer.parseInt(t4.getText()));
            ps.setString(5,t5.getText());
            ps.setString(6,t6.getText());
            ps.setString(7,veg);
            ps.setFloat(8,Float.parseFloat(t8.getText()));
            ps.setFloat(9,Float.parseFloat(t9.getText()));
            ps.setString(10,t10.getText());
            ps.setInt(11,Integer.parseInt(did1));
            ps.setString(12,d);
              ps.executeUpdate();
 
  int reply=JOptionPane.showConfirmDialog(null,"Vegetable added successfully.Do you want add more Vegetable?","Added Vegetable",JOptionPane.YES_NO_OPTION);
 
                 if (reply == JOptionPane.YES_OPTION)
                   {
                      jf.setVisible(false);
                      new AddNewVegetable();
                   }
                 else if (reply == JOptionPane.NO_OPTION)
                   {
                     jf.setVisible(false);
                }
          }
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
  else if(ae.getSource()==b1)
      {    t1.setText("");
           t2.setText("");
           t3.setText("");
           t4.setText("");
           t5.setText("");
           t6.setText("");
           t8.setText("");
           t9.setText("");
           t10.setText("");
 
      }
 
    else if(ae.getSource()==b2)
    {//list
      int r = 0;
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_stock","root","root");
        System.out.println("Connected to database.");
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("SELECT * from vegetable" );
          while(rs.next())
            {
  model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12) });
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
          new AddNewVegetable();
    }
}


	

	

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class UpdateGrocery extends JFrame implements ActionListener
{
    JFrame jf;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,ln;
    JButton b0,b1,b2,b3;
    JComboBox gdname,groctype;
    String d,did1,groc;
    Font f;
    Connection con;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

    UpdateGrocery()
    {
        jf=new JFrame();
        f = new Font("Times New Roman",Font.BOLD,20);
        jf.setLayout(null);

        ln=new JLabel("Update Grocery");
        ln.setFont(new Font("Times New Roman",Font.BOLD,25));
        ln.setForeground(Color.blue);
        ln.setBounds(300,30,300,40);
        jf.add(ln);

        l1 = new JLabel("Grocery Batch no*");
        //l1.setFont(f);
  l1.setBounds(50,100,200,25);
        jf.add(l1);

        t1 = new JTextField(20);
        t1.setBounds(250,100,100,25);t1.setToolTipText("Enter grocery batch no");
        jf.add(t1);

        l2 = new JLabel("Grocery name*");
        //l2.setFont(f);
   l2.setBounds(50,140,200,25);
        jf.add(l2);

        t2 = new JTextField(20);
        t2.setBounds(250,140,200,25);t2.setToolTipText("Enter grocery name");
        jf.add(t2);

        l3 = new JLabel("Grocery company*");
        //l3.setFont(f);
  l3.setBounds(50,180,200,25);
        jf.add(l3);

         t3 = new JTextField(20);
        t3.setBounds(250,180,200,25);t3.setToolTipText("Enter grocery company");
        jf.add(t3);

        l4 = new JLabel("Grocery quantity*");
        //l4.setFont(f);
  l4.setBounds(50,220,200,25);
        jf.add(l4);

        t4= new JTextField(20);
        t4.setBounds(250,220,100,25);t4.setToolTipText("Enter grocery quantity");
        jf.add(t4);

        l5= new JLabel("Groc expiry date*");
        //l5.setFont(f);
  l5.setBounds(50,260,250,25);
        jf.add(l5);

        t5= new JTextField(20);
        t5.setBounds(250,260,100,25);t5.setToolTipText("Enter grocery expiry date");
        jf.add(t5);

        l6= new JLabel("Groc purchase date*");
        //l6.setFont(f);
  l6.setBounds(50,300,260,25);
        jf.add(l6);

        t6= new JTextField(20);
        t6.setBounds(250,300,100,25);t6.setToolTipText("Enter grocery expiry date");
        jf.add(t6);

        l7 = new JLabel("Grocery type*");
        //l7.setFont(f);
  l7.setBounds(470,100,200,25);
        jf.add(l7);

        t7 = new JTextField(20);
        t7.setBounds(720,100,100,25);t7.setToolTipText("Enter grocery type");
        jf.add(t7);

        groctype=new JComboBox();
        groctype.addItem("--type--");
        groctype.addItem("dairy");
        groctype.addItem("bakery");
        groctype.addItem("frozen food");
        groctype.addItem("grains");


        groctype.setBounds(600,100,100,25);groctype.setToolTipText("Select grocery type");
        jf.add(groctype);
        groctype.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent ae)
           {
            groc =(String)groctype.getSelectedItem();
            t7.setText(groc);
           }
          });



        l8= new JLabel("Grocery purchase price*");
        //l8.setFont(f);
 l8.setBounds(470,140,220,25);
        jf.add(l8);

        t8 = new JTextField(20);
        t8.setBounds(720,140,100,25);t8.setToolTipText("Enter grocery purchase price");
        jf.add(t8);

        l9 = new JLabel("Grocery sale price*");
    //    l9.setFont(f);
  l9.setBounds(470,180,200,25);
        jf.add(l9);

        t9 = new JTextField(20);
        t9.setBounds(720,180,100,25);t9.setToolTipText("Enter grocery sale price");
        jf.add(t9);

        l10 = new JLabel("Grocery rack no*");
        //l10.setFont(f);
l10.setBounds(470,220,200,25);
        jf.add(l10);

        t10 = new JTextField(20);
        t10.setBounds(720,220,100,25);t10.setToolTipText("Enter grocery rack no");
        jf.add(t10);

        l11 = new JLabel("Distributor name*");
        //l11.setFont(f);
    l11.setBounds(470,260,180,25);
        jf.add(l11);

        t11 = new JTextField(20);
        t11.setBounds(720,260,100,25);
        jf.add(t11);

        l12 = new JLabel("Distributor id");
    //    l12.setFont(f);
 l12.setBounds(470,300,180,25);
        jf.add(l12);

        t12 = new JTextField(20);
        t12.setBounds(720,300,100,25);
        jf.add(t12);


        gdname=new JComboBox();
        gdname.setBounds(600,260,110,25);gdname.setToolTipText("select grocery distributor name");
        jf.add(gdname);
        gdname.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent ae)
           {
            d =(String)gdname.getSelectedItem();
            t11.setText(d);
           }
          });

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_stock?useSSL=false","root","root");
            System.out.println("Connected to database.");
             ps=con.prepareStatement("select did,dname from distributor");
            rs=ps.executeQuery();
            while(rs.next())
            {
             did1=rs.getString(1);
             String sname1=rs.getString(2);
             gdname.addItem(sname1);
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

        b0 = new JButton("Open",new ImageIcon("images//open.png"));
        b0.setBounds(150,330,110,35);b0.setToolTipText("click to open grocery details");
        jf.add(b0);b0.addActionListener(this);

        b1 = new JButton("Update",new ImageIcon("images//update.png"));
        b1.setBounds(300,330,110,35);b1.setToolTipText("click to update grocery details");
        jf.add(b1); b1.addActionListener(this);

        b2= new JButton("Clear",new ImageIcon("images//clear.png"));
        b2.setBounds(450,330,110,35);b2.setToolTipText("click to clear all textfields");
        jf.add(b2); b2.addActionListener(this);

        b3 = new JButton("All",new ImageIcon("images//all.png"));
        b3.setBounds(600,330,110,35);b3.setToolTipText("click to view all grocery details");
        jf.add(b3); b3.addActionListener(this);

        scrlPane.setBounds(0,380,900,600);
        jf.add(scrlPane);
        tabGrid.setFont(new Font ("Times New Roman",0,15));

        model.addColumn("G_BNO");
        model.addColumn("G_NAME");
        model.addColumn("G_COMPANY");
        model.addColumn("G_QUANTITY");
        model.addColumn("G_EXPDATE");
        model.addColumn("G_PURDATE");
        model.addColumn("G_TYPE");
        model.addColumn("G_SALEPRICE");
        model.addColumn("G_PURPRICE");
        model.addColumn("G_RACKNO");
        model.addColumn("G_DID");
        model.addColumn("G_DNAME");


         jf.setTitle("Update Grocery");
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
                if(((t1.getText()).equals(""))&&((t2.getText()).equals("")))
            {
            JOptionPane.showMessageDialog(this,"Please enter grocery batchno or name !","Warning!!!",JOptionPane.WARNING_MESSAGE);
            }
            else
            {

          try
           {//fetch
          int foundrec = 0;
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_stock","root","root");
        System.out.println("Connected to database.");
            ps=con.prepareStatement("Select did from distributor where dname='"+d+"'");
              rs=ps.executeQuery();
              while(rs.next())
              {
                  String sid1=rs.getString(1);
               t12.setText(sid1);
              }

          ps=con.prepareStatement("select * from grocery where gname='"+t2.getText()+"' or gbno='"+t1.getText()+"' ");
          rs=ps.executeQuery();
          while(rs.next())
          {
            t1.setText(rs.getString(1));
            t2.setText(rs.getString(2));
            t3.setText(rs.getString(3));
            t4.setText(rs.getString(4));
            t5.setText(rs.getString(5));
            t6.setText(rs.getString(6));
            t7.setText(rs.getString(7));
            t8.setText(rs.getString(8));
            t9.setText(rs.getString(9));
            t10.setText(rs.getString(10));
            t12.setText(rs.getString(11));
            t11.setText(rs.getString(12));
             foundrec = 1;
           }
           if (foundrec == 0)
                {
                    JOptionPane.showMessageDialog(null,"Record is not available","Dialogs",JOptionPane.WARNING_MESSAGE);
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
  else if(ae.getSource()==b1)
       {
       try
        {
            if(((t1.getText()).equals(""))||((t2.getText()).equals(""))||((t3.getText()).equals(""))||((t4.getText()).equals(""))||((t5.getText()).equals(""))||((t6.getText()).equals(""))||((t7.getText()).equals(""))||((t8.getText()).equals(""))||((t9.getText()).equals(""))||((t10.getText()).equals(""))||((t11.getText()).equals("")))
            {
            JOptionPane.showMessageDialog(this,"* Detail are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_stock","root","root");
            System.out.println("Connected to database.");
            stmt=con.createStatement();
String str1="UPDATE grocery SET gbno='"+t1.getText()+"',gname='"+t2.getText()+"',gcompany='"+t3.getText()+"',gqty='"+t4.getText()+"',gexpdate='"+t5.getText()+"',gpurdate='"+t6.getText()+"',gtype='"+t7.getText()+"',gpurprice='"+t8.getText()+"',gsaleprice='"+t9.getText()+"',grackno='"+t10.getText()+"',did='"+t12.getText()+"',dname='"+t11.getText()+"' where gbno='"+t1.getText()+"'or gname='"+t2.getText()+"'";
        stmt.executeUpdate(str1);
        JOptionPane.showMessageDialog(null, "Record is updated");
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        t8.setText("");
        t9.setText("");
        t10.setText("");
        t11.setText("");
        t12.setText("");
        con.close();
       }
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
  else if(ae.getSource()==b2)
      {    t1.setText("");
           t2.setText("");
           t3.setText("");
           t4.setText("");
           t5.setText("");
           t6.setText("");
           t7.setText("");
           t8.setText("");
           t9.setText("");
           t10.setText("");
           t11.setText("");
           t12.setText("");
      }
 else if(ae.getSource()==b3)
    {//list
      int r = 0;
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_stock","root","root");
        System.out.println("Connected to database.");
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("SELECT * from grocery" );
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
  new UpdateGrocery();
 }
}


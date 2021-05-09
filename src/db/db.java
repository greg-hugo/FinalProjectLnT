package db;

import java.sql.*;  
//import headless
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import java.util.Scanner;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.InputMismatchException;

public class db extends JFrame{
 
 private static final long serialVersionUID = 1L;
 
 JTable table1 = new JTable();
 JTable table2 = new JTable();
 JTable table3 = new JTable();
 DefaultTableModel model = new DefaultTableModel();
 JScrollPane tableContainer1 = new JScrollPane(table1);
 JScrollPane tableContainer2 = new JScrollPane(table2);
 JScrollPane tableContainer3 = new JScrollPane(table3);
 
 JPanel jp = new JPanel();
 JPanel jp_insert = new JPanel();
 JPanel jp_view = new JPanel();
 JPanel jp_update = new JPanel();
 JPanel jp_delete = new JPanel();
 JButton insert = new JButton("Insert Menu");
 JButton view = new JButton("View Menu");
 JButton update = new JButton("Update Menu");
 JButton delete = new JButton("Delete Menu");
 JButton add = new JButton("Add");
 JButton edit = new JButton("Update");
 JButton destroy = new JButton("Delete");
 JButton back_insert = new JButton("Back");
 JButton back_view = new JButton("Back");
 JButton back_update = new JButton("Back");
 JButton back_delete = new JButton("Back");
 
 JLabel name = new JLabel("Name");
 JLabel price = new JLabel("Price");
 JLabel stock = new JLabel("Stock");
 JLabel price_edit = new JLabel("Price");
 JLabel stock_edit = new JLabel("Stock");
 JLabel id = new JLabel("ID");
 JLabel id_delete = new JLabel("ID");
 JLabel field_validation = new JLabel();
 String note = "Note: Click on the menu to select it";
 JLabel note_delete = new JLabel(note);
 JLabel note_update = new JLabel(note);

 JTextField form_code = new JTextField();
 JTextField form_name = new JTextField();
 JTextField form_price = new JTextField();
 JTextField form_stock = new JTextField();
 JTextField update_price = new JTextField();
 JTextField update_stock = new JTextField();
 JTextField delete_id = new JTextField();
 Color lightblack = new Color(13,13,13);
 
 private void emptyForm() {
	 form_name.setText(null);
	 form_price.setText(null);
	 form_stock.setText(null);
	 update_price.setText(null);
	 update_stock.setText(null);
	 delete_id.setText(null);
	 form_code.setText(null);
 }
 
 public void displayData()throws SQLException {
	 
	
	String dbURL = "jdbc:mysql://localhost:3306/bobacool";
	String username = "root";
	String password = "";
	Connection conn = DriverManager.getConnection(dbURL, username,password);
	 
	try {
 
 			String sql = "SELECT * From menu";
 			Statement state = conn.createStatement();
 			ResultSet result = state.executeQuery(sql);
    
 			while (result.next()){
 				model.addRow(new Object[] {result.getString(1), result.getString(2),result.getInt(3),result.getInt(4)});
 			}
	}catch(SQLException err) {
			System.out.println("ERROR: "+ err.getMessage());
 }
	 
 }
 
 db()throws SQLException{
	 
	 String dbURL = "jdbc:mysql://localhost:3306/bobacool";
	 String username = "root";
	 String password = "";
	 Connection conn = DriverManager.getConnection(dbURL, username,password);
		 
	 setTitle("BobaCool");
	 setSize(720, 720);
	 setLocationRelativeTo(null);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 setVisible(true);
	 setResizable(false);
	
	 getContentPane().add(jp);
	 
	 jp.setBackground(lightblack);
	 jp_insert.setBackground(lightblack);
	 jp_view.setBackground(lightblack);
	 jp_update.setBackground(lightblack);
	 jp_delete.setBackground(lightblack);
	 
	 //warna untuk components
	 name.setForeground(Color.white);
	 price.setForeground(Color.white);
	 stock.setForeground(Color.white);
	 id.setForeground(Color.white);
	 id_delete.setForeground(Color.white);
	 price_edit.setForeground(Color.white);
	 stock_edit.setForeground(Color.white);
	 note_update.setForeground(Color.white);
	 note_delete.setForeground(Color.white);
	 note_update.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 note_delete.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 //
	 
	 //setup model dan table
	 model.addColumn("ID");
	 model.addColumn("Name");
	 model.addColumn("Price");
	 model.addColumn("Stock");
	 
	 table1.setModel(model);
	 table2.setModel(model);
	 table3.setModel(model);
	 table1.setDefaultEditor(Object.class, null);
	 table1.getColumnModel().getColumn(0).setPreferredWidth(100);
	 table1.getColumnModel().getColumn(1).setPreferredWidth(300);
	 table1.getColumnModel().getColumn(2).setPreferredWidth(100);
	 table1.getColumnModel().getColumn(3).setPreferredWidth(100);
	 table2.setDefaultEditor(Object.class, null);
	 table2.getColumnModel().getColumn(0).setPreferredWidth(100);
	 table2.getColumnModel().getColumn(1).setPreferredWidth(300);
	 table2.getColumnModel().getColumn(2).setPreferredWidth(100);
	 table2.getColumnModel().getColumn(3).setPreferredWidth(100);
	 table3.setDefaultEditor(Object.class, null);
	 table3.getColumnModel().getColumn(0).setPreferredWidth(100);
	 table3.getColumnModel().getColumn(1).setPreferredWidth(300);
	 table3.getColumnModel().getColumn(2).setPreferredWidth(100);
	 table3.getColumnModel().getColumn(3).setPreferredWidth(100);
	 jp_view.add(tableContainer1);
	 jp_update.add(tableContainer2);
	 jp_delete.add(tableContainer3);
	 //
	 
	 //Validasi textfield supaya hanya angka yang bisa dimasukkan untuk price dan stock
	 form_price.addKeyListener(new KeyAdapter() {
		 @Override
		 public void keyPressed(KeyEvent e) {
			 try {
				 Integer.parseInt(form_price.getText());
			 }catch(NumberFormatException err1) {
				 field_validation.setText("Input must be numeric!!");
			 }
		 }
	 });
	 
	 form_stock.addKeyListener(new KeyAdapter() {
		 @Override
		 public void keyPressed(KeyEvent e) {
			 try {
				 Integer.parseInt(form_stock.getText());
			 }catch(NumberFormatException err1) {
				 field_validation.setText("Input must be numeric!!");
			 }
		 }
	 });
	 
	 update_price.addKeyListener(new KeyAdapter() {
		 @Override
		 public void keyPressed(KeyEvent e) {
			 try {
				 Integer.parseInt(form_price.getText());
			 }catch(NumberFormatException err1) {
				 field_validation.setText("Input must be numeric!!");
			 }
		 }
	 });
	 
	 update_stock.addKeyListener(new KeyAdapter() {
		 @Override
		 public void keyPressed(KeyEvent e) {
			 try {
				 Integer.parseInt(form_stock.getText());
			 }catch(NumberFormatException err1) {
				 field_validation.setText("Input must be numeric!!");
			 }
		 }
	 });
	 //
	 
	 //masukin component buat tiap jpanel
	 jp.add(insert);
	 jp.add(view);
	 jp.add(update);
	 jp.add(delete);
	 
	 jp_insert.add(name);
	 jp_insert.add(form_name);
	 jp_insert.add(price);
	 jp_insert.add(form_price);
	 jp_insert.add(stock);
	 jp_insert.add(form_stock);
	 jp_insert.add(add);
	 jp_insert.add(back_insert);
	 
	 jp_view.add(back_view);
	 
	 jp_update.add(id);
	 jp_update.add(form_code);
	 jp_update.add(price_edit);
	 jp_update.add(update_price);
	 jp_update.add(stock_edit);
	 jp_update.add(update_stock);
	 jp_update.add(edit);
	 jp_update.add(back_update);
	 jp_update.add(note_update);
	  
	 jp_delete.add(id_delete);
	 jp_delete.add(delete_id);
	 jp_delete.add(destroy);
	 jp_delete.add(back_delete);
	 jp_delete.add(note_delete);
	 //
	 
	//atur form
		 form_code.setEditable(false);
		 delete_id.setEditable(false);
		 delete_id.setPreferredSize(new Dimension(210,24));
		 form_name.setPreferredSize(new Dimension(150,24));
		 form_price.setPreferredSize(new Dimension(150,24));
		 form_stock.setPreferredSize(new Dimension(150,24));
		 add.setPreferredSize(new Dimension(130,24));
	//
	 
	//layout manager masing-masing jpanel
	 SpringLayout sl_jp_delete = new SpringLayout();
	 sl_jp_delete.putConstraint(SpringLayout.SOUTH, tableContainer3, -120, SpringLayout.SOUTH, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.EAST, tableContainer3, -130, SpringLayout.EAST, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.NORTH, delete_id, 99, SpringLayout.NORTH, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.EAST, delete_id, -345, SpringLayout.EAST, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.NORTH, id_delete, 103, SpringLayout.NORTH, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.EAST, id_delete, -573, SpringLayout.EAST, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.EAST, destroy, -513, SpringLayout.EAST, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.NORTH, destroy, 603, SpringLayout.NORTH, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.EAST, back_delete, -19, SpringLayout.EAST, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.NORTH, back_delete, 10, SpringLayout.NORTH, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.NORTH, note_delete, 69, SpringLayout.NORTH, jp_delete);
	 sl_jp_delete.putConstraint(SpringLayout.WEST, note_delete, 129, SpringLayout.WEST, jp_delete);
	 jp_delete.setLayout(sl_jp_delete);
	 
	 SpringLayout sl_jp_view = new SpringLayout();
	 sl_jp_view.putConstraint(SpringLayout.SOUTH, tableContainer1, -120, SpringLayout.SOUTH, jp_view);
	 sl_jp_view.putConstraint(SpringLayout.EAST, tableContainer1, -130, SpringLayout.EAST, jp_view);
	 sl_jp_view.putConstraint(SpringLayout.EAST, back_view, -19, SpringLayout.EAST, jp_view);
	 sl_jp_view.putConstraint(SpringLayout.NORTH, back_view, 10, SpringLayout.NORTH, jp_view);
	 jp_view.setLayout(sl_jp_view);
	 
	 SpringLayout sl_jp_insert = new SpringLayout();
	 sl_jp_insert.putConstraint(SpringLayout.NORTH, form_stock, 211, SpringLayout.NORTH, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.SOUTH, form_stock, -53, SpringLayout.NORTH, add);
	 sl_jp_insert.putConstraint(SpringLayout.NORTH, add, 45, SpringLayout.SOUTH, stock);
	 sl_jp_insert.putConstraint(SpringLayout.WEST, add, 23, SpringLayout.WEST, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.SOUTH, add, 329, SpringLayout.NORTH, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.EAST, add, -22, SpringLayout.EAST, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.SOUTH, stock, -445, SpringLayout.SOUTH, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.NORTH, stock, 199, SpringLayout.NORTH, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.NORTH, name, -6, SpringLayout.NORTH, form_name);
	 sl_jp_insert.putConstraint(SpringLayout.WEST, name, 0, SpringLayout.WEST, price);
	 sl_jp_insert.putConstraint(SpringLayout.SOUTH, name, -11, SpringLayout.NORTH, price);
	 sl_jp_insert.putConstraint(SpringLayout.EAST, name, -6, SpringLayout.WEST, form_name);
	 sl_jp_insert.putConstraint(SpringLayout.NORTH, form_name, -97, SpringLayout.NORTH, stock);
	 sl_jp_insert.putConstraint(SpringLayout.WEST, form_name, 0, SpringLayout.WEST, form_price);
	 sl_jp_insert.putConstraint(SpringLayout.SOUTH, form_name, -24, SpringLayout.NORTH, form_price);
	 sl_jp_insert.putConstraint(SpringLayout.EAST, form_name, 0, SpringLayout.EAST, form_price);
	 sl_jp_insert.putConstraint(SpringLayout.NORTH, price, 150, SpringLayout.NORTH, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.SOUTH, price, -6, SpringLayout.NORTH, stock);
	 sl_jp_insert.putConstraint(SpringLayout.WEST, price, 10, SpringLayout.WEST, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.EAST, price, -6, SpringLayout.WEST, form_price);
	 sl_jp_insert.putConstraint(SpringLayout.NORTH, form_price, 6, SpringLayout.NORTH, price);
	 sl_jp_insert.putConstraint(SpringLayout.WEST, form_price, 0, SpringLayout.WEST, form_stock);
	 sl_jp_insert.putConstraint(SpringLayout.SOUTH, form_price, -25, SpringLayout.NORTH, form_stock);
	 sl_jp_insert.putConstraint(SpringLayout.EAST, form_price, 0, SpringLayout.EAST, form_stock);
	 sl_jp_insert.putConstraint(SpringLayout.WEST, form_stock, 66, SpringLayout.WEST, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.WEST, stock, 10, SpringLayout.WEST, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.EAST, stock, -6, SpringLayout.WEST, form_stock);
	 sl_jp_insert.putConstraint(SpringLayout.EAST, form_stock, -368, SpringLayout.EAST, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.EAST, back_insert, -19, SpringLayout.EAST, jp_insert);
	 sl_jp_insert.putConstraint(SpringLayout.NORTH, back_insert, 10, SpringLayout.NORTH, jp_insert);
	 jp_insert.setLayout(sl_jp_insert);
	 
	 SpringLayout sl_jp_update = new SpringLayout();
	 sl_jp_update.putConstraint(SpringLayout.WEST, tableContainer2, 59, SpringLayout.WEST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.EAST, tableContainer2, -245, SpringLayout.EAST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.WEST, edit, 59, SpringLayout.WEST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.EAST, edit, -245, SpringLayout.EAST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.WEST, update_stock, 59, SpringLayout.WEST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.EAST, update_stock, -245, SpringLayout.EAST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, edit, 27, SpringLayout.SOUTH, tableContainer2);
	 sl_jp_update.putConstraint(SpringLayout.SOUTH, edit, -40, SpringLayout.SOUTH, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.SOUTH, tableContainer2, -90, SpringLayout.SOUTH, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, tableContainer2, 23, SpringLayout.SOUTH, update_stock);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, update_stock, -4, SpringLayout.NORTH, stock_edit);
	 sl_jp_update.putConstraint(SpringLayout.EAST, stock_edit, -400, SpringLayout.EAST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.WEST, stock_edit, 12, SpringLayout.WEST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, form_code, -4, SpringLayout.NORTH, id);
	 sl_jp_update.putConstraint(SpringLayout.WEST, form_code, 0, SpringLayout.WEST, update_price);
	 sl_jp_update.putConstraint(SpringLayout.SOUTH, form_code, 19, SpringLayout.NORTH, id);
	 sl_jp_update.putConstraint(SpringLayout.EAST, form_code, -245, SpringLayout.EAST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.WEST, update_price, 59, SpringLayout.WEST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.EAST, id, -418, SpringLayout.EAST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.EAST, update_price, -245, SpringLayout.EAST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.WEST, id, 12, SpringLayout.WEST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.WEST, price_edit, 12, SpringLayout.WEST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.EAST, price_edit, -15, SpringLayout.WEST, update_price);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, update_price, -4, SpringLayout.NORTH, price_edit);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, price_edit, 65, SpringLayout.NORTH, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, stock_edit, 99, SpringLayout.NORTH, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, id, 30, SpringLayout.NORTH, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.EAST, back_update, -19, SpringLayout.EAST, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, back_update, 10, SpringLayout.NORTH, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.NORTH, note_update, 5, SpringLayout.NORTH, jp_update);
	 sl_jp_update.putConstraint(SpringLayout.WEST, note_update, 10, SpringLayout.WEST, jp_update);
	 jp_update.setLayout(sl_jp_update);
	 // 
	
	 //tombol pada jp untuk navigasi ke jpanel lain
	 insert.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	setContentPane(jp_insert);
	        	invalidate();
	        	validate();
	        }
	    });
	 
	 view.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	try {
					displayData();
				} catch (SQLException e1) {
					System.out.println("Error"+e1.getMessage());
				}
	        	
	        	setContentPane(jp_view);
	        	invalidate();
	        	validate();
	        }
	    });
	 //
	 
	 //buat dapat data di textfield ketika row table diklik
	 table2.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table2.rowAtPoint(evt.getPoint());
		        String code = table2.getValueAt(row, 0).toString();
		        form_code.setText(code);
		        
		        String price = table2.getValueAt(row, 2).toString();
		        update_price.setText(price);
		        
		        String stock = table2.getValueAt(row, 3).toString();
		        update_stock.setText(stock);
		    }
		});
	 //
	 
	 update.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        
	        	try {
					displayData();
				} catch (SQLException e1) {
					System.out.println("Error"+e1.getMessage());
				}
	        	setContentPane(jp_update);
	        	invalidate();
	        	validate();
	        }
	    });
	 
	//buat dapat data di textfield ketika row table diklik
	 table3.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table3.rowAtPoint(evt.getPoint());
		        String code = table3.getValueAt(row, 0).toString();
		        delete_id.setText(code);
		    }
		});
	 //
	 
	 delete.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	try {
					displayData();
				} catch (SQLException e1) {
					System.out.println("Error"+e1.getMessage());
				}
	        	setContentPane(jp_delete);
	        	invalidate();
	        	validate();
	        }
	    });
	 
	 
	 //tombol yang berhubungan untuk crud database
	 add.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	try {
	        		String code="BC-";
	        		Random r = new Random();
	        		for(int i=0; i<3; i++) {
	        			char num = (char)(r.nextInt(9) + '0');
	        			code += num;
	        		}
	        		
	        		String sql = "INSERT INTO menu (code,name, price,stock) VALUES (?,'"+form_name.getText()+"','"+form_price.getText()+"','"+form_stock.getText()+"')";
	        	    PreparedStatement statement = conn.prepareStatement(sql);
	        	    statement.setString(1, code);
	        	    
	        	    int rows = statement.executeUpdate();
	        	    
	        	    if(rows > 0){
	        	    	JOptionPane.showMessageDialog(null, "Successfully added new menu: "+code);
	        	    }
	        	    
	        	    setContentPane(jp);
		        	invalidate();
		        	validate();
		        	model.setRowCount(0);
	        	 
	        	}
	        	catch(HeadlessException | SQLException err) {
	        		JOptionPane.showMessageDialog(add, err.getMessage());
	        	}
	        	emptyForm();
	        	
	        }
	    });
	 
	 edit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	try {
	        		String sql = "UPDATE menu set price='"+update_price.getText()+"',stock='"+update_stock.getText()+"' where code= '"+form_code.getText()+"'";
	        		PreparedStatement statement = conn.prepareStatement(sql);
	        		int rows = statement.executeUpdate();
	        		JOptionPane.showMessageDialog(null, "Update is successful!");
	        		setContentPane(jp);
		        	invalidate();
		        	validate();
		        	model.setRowCount(0);
	        	}
	        	catch(HeadlessException | SQLException err) {
	        		JOptionPane.showMessageDialog(edit, err.getMessage());
	        	}
	        	emptyForm();
	        	
	        }
	    });
	 
	 destroy.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	try {
	        		String delete= "Delete from menu  where code= '"+delete_id.getText()+"'";
	        	    PreparedStatement statement = conn.prepareStatement(delete);
	        		int rows = statement.executeUpdate();
	        		JOptionPane.showMessageDialog(null, "Deletion is successful!");
	        		setContentPane(jp);
		        	invalidate();
		        	validate();
		        	model.setRowCount(0);
	        	}
	        	catch(HeadlessException | SQLException err) {
	        		JOptionPane.showMessageDialog(destroy, err.getMessage());
	        	}
	        	
	        	emptyForm();
	        	
	        }
	    });
	 //
	 
	 //tombol back ke jp
	 back_insert.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	try {
	        		setContentPane(jp);
		        	invalidate();
		        	validate();
		        	model.setRowCount(0);
	        	}
	        	catch(HeadlessException err) {
	        		JOptionPane.showMessageDialog(back_insert, err.getMessage());
	        	}
	        	
	        	emptyForm();
	        	
	        }
	    });
	 
	 back_view.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	try {
	        		setContentPane(jp);
		        	invalidate();
		        	validate();
		        	model.setRowCount(0);
	        	}
	        	catch(HeadlessException err) {
	        		JOptionPane.showMessageDialog(back_view, err.getMessage());
	        	}
	        	
	        	emptyForm();
	        	
	        }
	    });
	 
	 back_update.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	try {
	        		setContentPane(jp);
		        	invalidate();
		        	validate();
		        	model.setRowCount(0);
	        	}
	        	catch(HeadlessException err) {
	        		JOptionPane.showMessageDialog(back_update, err.getMessage());
	        	}
	        	
	        	emptyForm();
	        	
	        }
	    });
	 
	 back_delete.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	try {
	        		setContentPane(jp);
		        	invalidate();
		        	validate();
		        	model.setRowCount(0);
	        	}
	        	catch(HeadlessException err) {
	        		JOptionPane.showMessageDialog(back_delete, err.getMessage());
	        	}
	        	
	        	emptyForm();
	        	
	        }
	    });
	 //
	 
 }
 

 
 public static void main(String[] args) throws SQLException {    
  
	 new db();
	 
 }
 
}

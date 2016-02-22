package ihm.gestioneleve;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.IEcole;
import dao.SingletonDao;
import entities.Classe;
import entities.Parent;
import entities.Section;
import entities.Student;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EleveTable extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	/**
	 * Create the panel.
	 */
	int secID=0;
	public int getSecID() { 
		return secID;
	}
	public void setSecID(int secID) {
		this.secID = secID;
	}

	IEcole ie=SingletonDao.getIe();
	ArrayList<String> stds;
	public EleveTable() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "List Students", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int r=table.getSelectedRow();
				if(r!=-1){ 
					 if (JOptionPane.showConfirmDialog(EleveTable.this, 
					            "Are you sure to delete this etudiant?", "Confirmation de la supression?",  
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
					 {
						 	boolean b=ie.removeStudent((Integer) table.getValueAt(r,0));

						 	Section s=(Section)table.getValueAt(r,2);
						 	((DefaultTableModel) table.getModel()).removeRow(r);
						 	s.getStudents().remove(r); 
							table.clearSelection();
							JOptionPane.showMessageDialog(EleveTable.this,"deleted successfully");   
					 }
                   
					 
				}else{
					JOptionPane.showMessageDialog(EleveTable.this, "you should select a row first"); 
				}
			} 
		});
		panel.add(btnDelete);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				int r=table.getSelectedRow();
				if(r!=-1){ 
					Student s = ie.getStudent((Integer) table.getValueAt(r,0)); 
					
					AddEleveForm form=new AddEleveForm((Frame) SwingUtilities.getWindowAncestor(EleveTable.this),s,EleveTable.this);  
			        form.setLs(EleveTable.this);
					System.out.println("Idontnowwhybuttahtit");  
				}else{
					JOptionPane.showMessageDialog(EleveTable.this, "you should select a row first");   
				}
			
			}
		});
		panel.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		add(scrollPane);
		table = new JTable(){
			private static final long serialVersionUID = 1L;

			public Class getColumnClass(int column)
	        {
	            return getValueAt(0, column).getClass();
	        }
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setRowHeight(90);
		table.setSurrendersFocusOnKeystroke(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer); 

		String[] c=new String[] {"id", "name", "classe", "section", "date", "parent","Image"}; 
		DefaultTableModel dtm = new DefaultTableModel(null,c);
		table.setModel(dtm);   
		table.getTableHeader().setReorderingAllowed(false);

		

	} 
	public void setStudents(List<Student> stds){ 
		Object[][] o=new Object[stds.size()][7]; 
		int i=0;
		String[] c=new String[] {"id", "name", "classe", "section", "date", "parent","Image"}; 
		
		
		for (Student objects : stds) {
			o[i][0]=(Object)objects.getId();
			o[i][1]=(Object)objects.getName();
			o[i][2]=(Object)objects.getSection();
			o[i][3]=(Object)objects.getSection().getClasse();
			o[i][4]=(Object)objects.getDateBith().toString().substring(0, 11);
			o[i][5]=(Object)objects.getParent().getName();
			ImageIcon image;
			
			image = new ImageIcon(new ImageIcon(readContentIntoByteArray(objects.getImage())).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
				o[i][6]=image;  
				i++; 
		
			
			
		}
		DefaultTableModel dtm = new DefaultTableModel(o,c){ 
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }};
		 table.setModel(dtm);   
		repaint();
	}
	
	  private static byte[] readContentIntoByteArray(File file)
	   {
	      FileInputStream fileInputStream = null;
	      byte[] bFile = new byte[(int) file.length()];
	      try 
	      {
	         //convert file into array of bytes
	         fileInputStream = new FileInputStream(file);
	         fileInputStream.read(bFile);
	         fileInputStream.close();
	      }
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	      
	      return bFile;
	   }
	 
}

package ihm.gestionclasse;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import dao.IEcole;
import dao.SingletonDao;
import entities.Classe;
import entities.Section;
import entities.Teacher;

import javax.swing.UIManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionClasse extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private IEcole ie =SingletonDao.getIe();
	private JTable table;
	private JTable table_1;
	public GestionClasse() {
		setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ajouter Classe", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 323, 92);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblClasseName = new JLabel("classe name :");
		lblClasseName.setBounds(10, 28, 78, 14);
		panel.add(lblClasseName);
		
		textField = new JTextField();
		textField.setBounds(98, 21, 126, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnValider = new JButton("valider");
		btnValider.setBounds(98, 52, 89, 28);
		panel.add(btnValider);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ajouter Section", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(343, 11, 395, 153);
		add(panel_1);
		
		JLabel label = new JLabel("section name :");
		label.setBounds(10, 28, 104, 14);
		panel_1.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(124, 21, 126, 28);
		panel_1.add(textField_1);
		
		JButton button = new JButton("valider");
		button.setBounds(124, 114, 89, 28);
		panel_1.add(button);

		List<Classe> list_1 = new Vector(ie.getClasses());   
		JComboBox comboBox = new JComboBox((Vector) list_1); 
		comboBox.setBounds(124, 50, 126, 28);
		panel_1.add(comboBox);
		
		JLabel lblClasse = new JLabel("classe :");
		lblClasse.setBounds(10, 57, 69, 14);
		panel_1.add(lblClasse);
		
		JLabel lblNewLabel = new JLabel("teacher :");
		lblNewLabel.setBounds(10, 93, 69, 14);
		panel_1.add(lblNewLabel);

		List<Teacher> list_2 = new Vector(ie.getTeachers());   
		JComboBox comboBox_1 = new JComboBox((Vector) list_2); 
		comboBox_1.setBounds(124, 83, 126, 28);
		panel_1.add(comboBox_1);
		
		JButton btnRefresh = new JButton("refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Classe> secs=ie.getClasses();

				List<Classe> list_3 = new Vector(secs);  
				comboBox.removeAllItems();
				for (Classe cl : list_3) {
					comboBox.addItem(cl);   
				}
				
			}
		});
		btnRefresh.setBounds(259, 53, 89, 23);
		panel_1.add(btnRefresh);
		
		JButton button_1 = new JButton("refresh");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Teacher> tes=ie.getTeachers();

				List<Teacher> list_4 = new Vector(tes);   
				comboBox_1.removeAllItems();
				for (Teacher te : list_4) { 
					comboBox_1.addItem(te);   
				}
				
			}
		});
		button_1.setBounds(260, 86, 89, 23);
		panel_1.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 114, 323, 335);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ArrayList<Classe> classes=ie.getClasses();
		Object[][] o=new Object[classes.size()][2];  
		int i=0;
		for (Classe c : classes) {
			o[i][0]=(Object)c.getId(); 
			o[i][1]=(Object)c.getName();
			i++;
		}
		table.setModel(new DefaultTableModel(
			o, 
			new String[] {
				"id", "name"
			}
		){ 
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }});
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(343, 175, 395, 274);
		add(scrollPane_1);
		
		
		table_1 = new JTable();
		ArrayList<Section> sections=ie.getSections();
		Object[][] oo=new Object[sections.size()][4];  
		int ii=0;
		for (Section c : sections) {
			oo[ii][0]=(Object)c.getId(); 
			oo[ii][1]=(Object)c.getName();
			oo[ii][2]=(Object)c.getClasse().getName();
			oo[ii][3]=(Object)c.getTeacher().getName();  
			ii++; 
		}
		
		table_1.setModel(new DefaultTableModel(
			oo,
			new String[] {
				"id", "name", "Classe", "Teacher"
			}
		){ 
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }});  
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table_1.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(table_1);
		
		
	}
}

package ihm.gestionteacher;

import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.IEcole;
import dao.SingletonDao;
import entities.Student;
import entities.Teacher;
import ihm.gestioneleve.AddEleveForm;
import ihm.gestioneleve.EleveTable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class GestionTeacher extends JPanel {
	private JTextField name;
	private JTextField email;
	private IEcole ie=SingletonDao.getIe();
	private JTable table;
	private int id=0;
	private int row=0;
	public GestionTeacher() {
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();  
		scrollPane.setBounds(10, 110, 646, 341);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ArrayList<Teacher> teachers=ie.getTeachers();
		Object[][] o=new Object[teachers.size()][3]; 
		int i=0;
		for (Teacher teacher : teachers) {
			o[i][0]=(Object)teacher.getId();
			o[i][1]=(Object)teacher.getName();
			o[i][2]=(Object)teacher.getEmail();
			i++;
		}
		table.setModel(new DefaultTableModel( 
			o,
			new String[] {
				"id", "name", "email"
			}
		){ 
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }});

		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		name = new JTextField();
		name.setBounds(64, 30, 140, 25);
		add(name);
		name.setColumns(10);
		JLabel lblModeAjout = new JLabel("------mode ajout------");
		lblModeAjout.setBounds(10, 11, 140, 14);
		add(lblModeAjout);
		JButton btnNewButton = new JButton("save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				System.out.println(email.getText().matches(EMAIL_REGEX));
				if (name.getText().length()>=1 && email.getText().matches(EMAIL_REGEX)) {
					
					Teacher t=new Teacher(name.getText(), email.getText());
					
					if(id!=0){t.setId(id); }

					
					t=ie.updateOrAddTeacher(t);
					System.out.println(id);
					
					System.out.println("id="+t.getId()); 
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					if(id!=0){
						model.setValueAt(t.getId(),row,0);
						model.setValueAt(t.getName(),row,1);
						model.setValueAt(t.getEmail(),row,2);   
					}
					else{
					model.addRow(new Object[]{t.getId(),t.getName(), t.getEmail()}); 
					}
					email.setText(""); 
					name.setText(""); 
					lblModeAjout.setText("------mode ajout------");
					id=0;
					row=-1;
				}else{
					JOptionPane.showMessageDialog(GestionTeacher.this, "name or email invalide");  
				}
				
			}
		});
		btnNewButton.setBounds(64, 66, 89, 33);
		add(btnNewButton);
		
		JLabel lblname = new JLabel("Nom");
		lblname.setBounds(10, 33, 46, 14);
		add(lblname);
		
		JLabel lblemail = new JLabel("email");
		lblemail.setBounds(251, 33, 46, 14);
		add(lblemail);
		
		email = new JTextField();
		email.setBounds(295, 30, 140, 25);
		add(email);
		email.setColumns(10);
	
		JButton btnUpdate = new JButton("update"); 
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r=table.getSelectedRow();
				if(r!=-1){ 
					
					Teacher s = ie.getTeacher((Integer) table.getValueAt(r,0)); 
					name.setText(s.getName());
					email.setText(s.getEmail()); 
					id=s.getId();
					row=r;
					lblModeAjout.setText("------mode edition------"); 
				}else{
					JOptionPane.showMessageDialog(GestionTeacher.this, "you should select a row first");   
				}
			}
		});
		btnUpdate.setBounds(666, 183, 89, 31);
		add(btnUpdate);
		
		
		
		
	}
}

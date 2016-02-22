package ihm.gestioneleve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import dao.IEcole;
import dao.SingletonDao;
import entities.Classe;
import entities.Parent;
import entities.Section;
import entities.Student;
import util.ImagePanel;

public class AddEleveForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	EleveTable ls;
	public EleveTable getLs() {
		return ls;
	}
	public void setLs(EleveTable ls) {
		this.ls = ls;
	}
	IEcole ie;  
	JComboBox comboBox;
	JComboBox comboBox_2;
	JComboBox comboBox_1;
	JDateChooser dateChooser;
	ImagePanel imagePanel;
	JTextField textField;
	AddEleveForm ref;

	int id=0;
//	public static void main(String[] args) {
//		try {
//			AddEleveForm dialog = new AddEleveForm(null);
//			dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddEleveForm(Frame jf,Student s,EleveTable ls) {
		super(jf);
		this.ls=ls;
		setResizable(false);
		ie =SingletonDao.getIe();    
		ArrayList<Parent> p=ie.getParents(); 
		ArrayList<Classe> c=ie.getClasses();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		setBounds(100, 100, 483, 330);
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		getContentPane().setLayout(new BorderLayout()); 
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		//

		JLabel lblName = new JLabel("Name"); 
		lblName.setBounds(10, 23, 46, 14);
		contentPanel.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(93, 20, 183, 25); 
		contentPanel.add(textField);
		textField.setColumns(10);
		int i=0;
		JLabel parent = new JLabel("Parent");
		parent.setBounds(10, 61, 46, 14);
		contentPanel.add(parent);

		List<Classe> list = new Vector(ie.getParents());  
		comboBox = new JComboBox((Vector)list); 
		comboBox.setBounds(93, 61, 183, 25);	 
		contentPanel.add(comboBox);
		
		JLabel label = new JLabel("Classe");
		label.setBounds(10, 97, 46, 14);
		contentPanel.add(label);
	
		List<Classe> list_1 = new Vector(ie.getClasses());  
		comboBox_1 = new JComboBox((Vector)list_1);
		comboBox_1.setBounds(93, 97, 183, 25);
		getContentPane().add(comboBox_1);  
	
		JLabel label_1 = new JLabel("Section");
		label_1.setBounds(10, 138, 46, 14);
		contentPanel.add(label_1);
		List<Section> secs=list_1.get(0).getSections();  

		List<Classe> list_2 = new Vector(secs);  
		comboBox_2 = new JComboBox((Vector)list_2);   
		
		comboBox_2.setBounds(93, 138, 183, 25);
		
		contentPanel.add(comboBox_2);  
		comboBox_1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Classe classe = (Classe)comboBox_1.getSelectedItem();
					List<Section> secs=classe.getSections();

					List<Section> list_3 = new Vector(secs);  
					comboBox_2.removeAllItems();
					for (Section sec : list_3) {
						comboBox_2.addItem(sec); 
					}
				} 
				
				
				
			}
		});
		JLabel lblBirthday = new JLabel("BirthDay");
		lblBirthday.setBounds(10, 188, 73, 14);
		contentPanel.add(lblBirthday);
		JLabel label_2 = new JLabel("Image");
		label_2.setBounds(10, 224, 46, 14);
		contentPanel.add(label_2);
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(301, 42, 126, 160);
		contentPanel.add(panel);
		panel.setLayout(null); 
		
		imagePanel = new ImagePanel(new File("default.png"));   
		imagePanel.setBounds(0, 0,126, 160);
		panel.add(imagePanel);

		dateChooser = new JDateChooser(); 
		dateChooser.setDateFormatString("yyyy-mm-dd");
		dateChooser.setBounds(93, 179, 183, 25); 
		contentPanel.add(dateChooser);
		if(s!=null){
			textField.setText(s.getName());
			try {
				imagePanel.setPic(ImageIO.read(s.getImage()),s.getImage());
			} catch (IOException e) {
				e.printStackTrace();
			} 
			for (Classe classe : ie.getClasses()) {
				if (classe.getId()==s.getSection().getClasse().getId()) {
					List<Section> secs1=ie.getSectionsByClasse(classe.getId());
					comboBox_1.setSelectedItem(s.getSection().getClasse());  

					List<Section> list_3 = new Vector(secs1); 
					comboBox_2.removeAllItems(); 
					for (Section sec : list_3) {
						comboBox_2.addItem(sec);
						if (sec.getId()==s.getSection().getId()) { 
							comboBox_2.setSelectedItem(sec);  
						} 
					}
					
				}
			}
			for (Parent p1: ie.getParents()) {  
				if (p1.getId()==s.getParent().getId()) {
					comboBox.setSelectedItem(s.getSection().getClasse()); 
				}
			}  
			System.out.println(s.getDateBith());
			dateChooser.setDate(s.getDateBith());  
			
			
		}
		JButton btnParcourir = new JButton("Parcourir");
		File f; 
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
			 	JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG & GIF Images", "jpg", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(AddEleveForm.this); 
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
			    		imagePanel.setPic(ImageIO.read(chooser.getSelectedFile()),chooser.getSelectedFile()); 
					} catch (IOException e) { 
						JOptionPane.showMessageDialog(AddEleveForm.this, "Image Error: "+e.getMessage());  
					}  
			    }                     
			}
		});
		btnParcourir.setBounds(93, 215, 89, 32); 
		contentPanel.add(btnParcourir);
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int parentId = ((Parent)comboBox.getSelectedItem()).getId();
				int sectionid = ((Section)comboBox_2.getSelectedItem()).getId(); 
				try { 
					Date d= dateChooser.getDate();
					if(d==null){
						JOptionPane.showMessageDialog(AddEleveForm.this, "Error: Date format error");
					}else{
						if(textField.getText().length()>=5){

							Student ss= new Student(textField.getText(),d,imagePanel.getF());
							if(s!=null){
								ss.setId(s.getId());
							}
							ie.updateOrAddStudent(ss,parentId,sectionid); 
							JOptionPane.showMessageDialog(ref, "       Operation Success");
							System.out.println(getLs().getSecID()+"==?"+sectionid);
							Section sec=(Section)comboBox_2.getSelectedItem();
							if(getLs().getSecID()==sectionid){   
								getLs().setStudents(ie.getStudentsBySection(sectionid));  
							}else{
								getLs().setStudents(ie.getStudentsBySection(getLs().getSecID()));  
							}
							AddEleveForm.this.dispose();
						}else{ 
							JOptionPane.showMessageDialog(AddEleveForm.this, "Error: name length must be > 5 char");  
						}
					} 
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(AddEleveForm.this, "Error: "+e2.getMessage());
				}
			}
		});
		btnSave.setBounds(299, 240, 89, 32); 
		contentPanel.add(btnSave);
		//
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
	public static void main(String[] args) {
		AddEleveForm form=new AddEleveForm(null,null,null); 
	}
}

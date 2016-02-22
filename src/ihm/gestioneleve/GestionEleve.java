package ihm.gestioneleve;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dao.IEcole;
import dao.SingletonDao;
import entities.Classe;
import entities.Section;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class GestionEleve extends JPanel {

	/**
	 * Create the panel.
	 */
	IEcole ie;
	public GestionEleve() {
		setLayout(null);
		ie=SingletonDao.getIe();
		JButton button = new JButton("Ajouter");
		button.setBounds(162, 11, 100, 29);

		EleveTable listerStudentv2 = new EleveTable();
		listerStudentv2.setBorder(null);
		listerStudentv2.setBounds(10, 146, 740, 317);
		add(listerStudentv2);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddEleveForm form=new AddEleveForm((Frame) SwingUtilities.getWindowAncestor(GestionEleve.this),null,listerStudentv2); 
			} 
		});  
		add(button);
		JLabel label = new JLabel("Ajouter un nouveau \u00E9l\u00E8ve :");
		label.setBounds(20, 18, 163, 14);
		add(label);
		JLabel label_1 = new JLabel("Afficher les \u00E9l\u00E8ves:  -choisir une classe   :");
		label_1.setBounds(20, 58, 242, 14);
		add(label_1);
		List<Classe> list_1 = new Vector<Classe>(ie.getClasses());  
		JComboBox<Classe> classes = new JComboBox<Classe>((Vector<Classe>)list_1); 
		classes.setBounds(267, 51, 119, 29);
		add(classes);
		List<Section> secs=list_1.get(0).getSections();
		List<Classe> list_2 = new Vector(secs);  
		JComboBox<Section> sections = new JComboBox<Section>((Vector)list_2);  
		sections.setBounds(267, 82, 119, 29);
		add(sections);
		JButton btnNewButton = new JButton("Afficher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Section section = (Section)sections.getSelectedItem(); 
					listerStudentv2.setStudents(ie.getStudentsBySection(section.getId()));	  
					listerStudentv2.setSecID(section.getId()); 
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(GestionEleve.this, "Vous devez selectionner la classe et la section d'abord"); 
				}
			}
		});
		btnNewButton.setBounds(267, 115, 89, 29);
		add(btnNewButton);
		
		JLabel lblChoisirUneSection = new JLabel("-choisir une section :");
		lblChoisirUneSection.setBounds(122, 90, 146, 14);
		add(lblChoisirUneSection);
		classes.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Classe classe = (Classe)classes.getSelectedItem(); 
					List<Section> secs=ie.getSectionsByClasse(classe.getId());
					List<Section> list_3 = new Vector(secs);   
					sections.removeAllItems();
					for (Section sec : list_3) {
						sections.addItem(sec);
						//System.out.println(sec);
					}
				}
			}
		});

	}
}

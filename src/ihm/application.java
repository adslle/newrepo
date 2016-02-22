package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.toedter.calendar.JCalendar;

import dao.SingletonDao;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import ihm.gestioneleve.GestionEleve;

import javax.swing.JOptionPane;
import ihm.gestionclasse.GestionClasse;
import ihm.gestionteacher.GestionTeacher;

public class application extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//		            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());      
//					application frame = new application();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public application() {
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override 
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(application.this,
		            "Are you sure to close this window?", "Really Closing?",  
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        		SingletonDao.closeEntityManager();
		            	System.exit(0); 
		        }
		    }
		}); 
		setResizable(false);
		setBounds(100, 100, 774, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		JPanel accueil = new JPanel(); 
		tabbedPane.addTab("Acueil", null, accueil, null);
		accueil.setLayout(new BorderLayout(0, 0));
		JCalendar calendar = new JCalendar();
		accueil.add(calendar);
		GestionEleve gestionEleve = new GestionEleve();
		tabbedPane.addTab("Eleve", null, gestionEleve, null);  
		
		GestionClasse gestionClasse = new GestionClasse();
		tabbedPane.addTab("Classe", null, gestionClasse, null);
		
		GestionTeacher gestionTeacher = new GestionTeacher();
		tabbedPane.addTab("Teacher", null, gestionTeacher, null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setVisible(true);
	}
}

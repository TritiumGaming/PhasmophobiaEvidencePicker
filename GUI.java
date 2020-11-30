import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * GUI class 
 * 
 * @author TritiumGaming
 */
@SuppressWarnings("serial")
public class GUI extends JFrame{
	
	private Investigation investigation = new Investigation();
	private CRadioButton[][] radioButtons = new CRadioButton[Investigation.Evidence.values().length][3];
	private JLabel[] labels_ghosts = new JLabel[] { 
			new JLabel("Banshee"), new JLabel("Demon"), 
			new JLabel("Jinn"), new JLabel("Mare"), 
			new JLabel("Oni"), new JLabel("Phantom"),
			new JLabel("Poltergeist"), new JLabel("Revenant"), 
			new JLabel("Shade"), new JLabel("Spirit"), 
			new JLabel("Wraith"), new JLabel("Yurei")};
	
	public GUI() {
		setTitle("Phasm. Evidence Picker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//Radio Buttons (LEFT SIDE)
		JPanel p_main = new JPanel();
		JPanel p_allRadios = new JPanel();
		JPanel[] p_radioGroups = new JPanel[Investigation.Evidence.values().length];
		JPanel p_label_title_evidence = new JPanel();
		JLabel[] labels_evidence = new JLabel[Investigation.Evidence.values().length];
		ButtonGroup[] radioGroups = new ButtonGroup[Investigation.Evidence.values().length];
		
		p_allRadios.setLayout(new BoxLayout(p_allRadios, BoxLayout.PAGE_AXIS));
		p_label_title_evidence.add(new JLabel("EVIDENCE:"));
		p_allRadios.add(p_label_title_evidence);
		
		for(int i = 0; i < Investigation.Evidence.values().length; i++)
			labels_evidence[i] = new JLabel(new String(Investigation.Evidence.values()[i].name()));
		
		for(int i = 0; i < radioGroups.length; i++) {
			JPanel p_label = new JPanel();
			p_radioGroups[i] = new JPanel();
			p_radioGroups[i].setLayout(new BoxLayout(p_radioGroups[i], BoxLayout.X_AXIS));
			radioGroups[i] = new ButtonGroup();
			for(int j = 0; j < 3; j++) {
				radioButtons[i][j] = new CRadioButton(
						investigation,
						Investigation.Evidence.values()[i], 
						Investigation.Evidence.Ruling.values()[j]
				);
				if(j == 1)
					radioButtons[i][j].setSelected(true);
				radioButtons[i][j].setBackground((j==0)?Color.RED:(j==2)?Color.GREEN:Color.WHITE);
				CRadioButton tRB = radioButtons[i][j];
				radioButtons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tRB.doEvidenceAction();
						for(JLabel l: labels_ghosts) {
							l.setForeground(Color.RED);
							for(Investigation.Ghost g: investigation.getGhosts())
								if(g.name().equalsIgnoreCase(l.getText()))
									if(g.getRating() < 3 && g.getRating() > -3)
										l.setForeground(Color.BLACK);
									else
										if(g.getRating() == 3)
											l.setForeground(Color.GREEN);
						}
					}
				});
				radioGroups[i].add(radioButtons[i][j]);
				p_radioGroups[i].add(radioButtons[i][j]);
			}
			p_label.setLayout(new BoxLayout(p_label, BoxLayout.X_AXIS));
			p_label.add(labels_evidence[i]);
			p_allRadios.add(p_label);
			p_allRadios.add(p_radioGroups[i]);
			p_allRadios.add(Box.createVerticalStrut(5));
		}
		p_allRadios.add(Box.createVerticalStrut(20));	
		
		//Ghost List (RIGHT SIDE)
		JPanel p_ghosts = new JPanel();
		JPanel p_label_title_ghosts = new JPanel();
		
		p_label_title_ghosts.add(new JLabel("POSSIBLE GHOSTS:"));
		p_label_title_ghosts.add(Box.createVerticalStrut(20));
		
		p_ghosts.setLayout(new BoxLayout(p_ghosts, BoxLayout.PAGE_AXIS));
		p_ghosts.add(p_label_title_ghosts);
		p_ghosts.add(p_label_title_ghosts);
		for(int i = 0; i < labels_ghosts.length; i++) {
			JPanel p_label = new JPanel();
			p_label.setLayout(new BoxLayout(p_label, BoxLayout.X_AXIS));
			p_label.add(labels_ghosts[i]);
			p_ghosts.add(Box.createVerticalStrut(5));
			p_ghosts.add(p_label);
		}
		p_ghosts.add(Box.createVerticalStrut(20));
		
		//Finalize Window
		p_main.setLayout(new BoxLayout(p_main, BoxLayout.X_AXIS));
		p_main.add(Box.createVerticalStrut(20));
		p_main.add(Box.createHorizontalStrut(20));
		p_main.add(p_allRadios);
		p_main.add(Box.createHorizontalStrut(50));
		p_main.add(p_ghosts);
		p_main.add(Box.createHorizontalStrut(20));
		p_main.add(Box.createVerticalStrut(20));
		
		add(p_main);
		
		setVisible(true);
		setResizable(false);
		pack();
	}
	
	private class CRadioButton extends JRadioButton{
				
		private Investigation i = null;
		private Investigation.Evidence e = null;
		private Investigation.Evidence.Ruling r = null;
		
		public CRadioButton(Investigation i, Investigation.Evidence e, Investigation.Evidence.Ruling r) {
			this.i = i;
			this.e = e;
			this.r = r;
		}
		
		public void doEvidenceAction() {
			i.setEvidenceRuling(e, r);
		}
	}
	
}

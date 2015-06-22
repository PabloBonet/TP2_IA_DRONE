package frsf.ia.tp.paqueteGrafico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIDialogPosicion extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public int posX;
	public  int posY;
	private JComboBox cmbPosX;
	private JComboBox cmbPosY;
	public boolean b_ok;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIDialogPosicion dialog = new UIDialogPosicion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UIDialogPosicion() {
		setTitle("Posici\u00F3n Drone");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblIndiqueLaPosicin = new JLabel("Indique la Posici\u00F3n del drone:");
			GridBagConstraints gbc_lblIndiqueLaPosicin = new GridBagConstraints();
			gbc_lblIndiqueLaPosicin.insets = new Insets(0, 0, 5, 5);
			gbc_lblIndiqueLaPosicin.gridx = 1;
			gbc_lblIndiqueLaPosicin.gridy = 0;
			contentPanel.add(lblIndiqueLaPosicin, gbc_lblIndiqueLaPosicin);
		}
		{
			JLabel lblX = new JLabel("X:");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 2;
			gbc_lblX.gridy = 1;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			 cmbPosX = new JComboBox();
			cmbPosX.setModel(new DefaultComboBoxModel(new String[] {"0", "70", "130"}));
			GridBagConstraints gbc_cmbPosX = new GridBagConstraints();
			gbc_cmbPosX.insets = new Insets(0, 0, 5, 0);
			gbc_cmbPosX.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbPosX.gridx = 3;
			gbc_cmbPosX.gridy = 1;
			contentPanel.add(cmbPosX, gbc_cmbPosX);
		}
		{
			JLabel lblY = new JLabel("Y:");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 0, 5);
			gbc_lblY.gridx = 2;
			gbc_lblY.gridy = 2;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			cmbPosY = new JComboBox();
			cmbPosY.setModel(new DefaultComboBoxModel(new String[] {"0", "65", "135"}));
			GridBagConstraints gbc_cmbPosY = new GridBagConstraints();
			gbc_cmbPosY.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbPosY.gridx = 3;
			gbc_cmbPosY.gridy = 2;
			contentPanel.add(cmbPosY, gbc_cmbPosY);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						System.out.println("click");
						String posx = cmbPosX.getSelectedItem().toString();
						posX = Integer.parseInt(posx);
						
						String posy = cmbPosY.getSelectedItem().toString();
						posY = Integer.parseInt(posy);
						
						b_ok = true;
						dispose();
						
					}
				});
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String posx = cmbPosX.getSelectedItem().toString();
						posX = Integer.parseInt(posx);
						
						String posy = cmbPosY.getSelectedItem().toString();
						posY = Integer.parseInt(posy);
						
						b_ok = true;
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

package layout.component;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PGNLayLayout implements ActionListener,ChangeListener {
	static Properties prop = new Properties();
	public static final String chineseSurnameFile = "chineseSurnameFile";
	public static final String outputFile = "outputFile";
	public static final String passwordLength = "passwordLength";
	public static final String englishNameFile = "englisheNameFile";
	public static final String chineseNameFile = "chineseNameFile";
	private JFrame frame;
	private JButton button;
	private JProgressBar progressBar;
	public static String[] surnameSeeds = null;
	public static String[] accountSeeds = null;
	public static String[] chinameSeeds = null;
	public static String outputPath = "" ;
	public static int passwordlen = 0;
	public static int num = 0;
	public static int res_num = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PGNLayLayout window = new PGNLayLayout();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PGNLayLayout() {
		loadProperties();
		initialize();
	}

	public static boolean propChecker(){
		boolean isReady = true;
		if(prop.getProperty(chineseNameFile)==null){
			return false;
		}
		if(prop.getProperty(chineseSurnameFile)==null){
			return false;
		}
		if(prop.getProperty(englishNameFile)==null){
			return false;
		}
		if(prop.getProperty(outputPath)==null){
			return false;
		}
		if(prop.getProperty(passwordLength)==null){
			return false;
		}
		return isReady;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("數量");
		
		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
		
		JRadioButton rdbtnLine = new JRadioButton("Line(中文姓名)");
		
		JRadioButton rdbtnWechat = new JRadioButton("WeChat(中文姓名 + 密碼)");
		
		JRadioButton rdbtnFacebook = new JRadioButton("Facebook(中文姓名+ email +密碼)");
//		timer=new Timer(100,this);
		final JRadioButton[] rdbs = {rdbtnLine,rdbtnWechat,rdbtnFacebook};
		 //Group the radio buttons.
		  final ButtonGroup group = new ButtonGroup();
		  group.add(rdbtnLine);
		  group.add(rdbtnWechat);
		  group.add(rdbtnFacebook);
		  progressBar = new JProgressBar();
		 
		  progressBar.setMinimum(0);
		  progressBar.setMaximum(100);
		  progressBar.setValue(0);
		  progressBar.setStringPainted(true);
		  progressBar.setBorderPainted(true);
		  progressBar.setBackground(Color.pink);
	    button = new JButton("產生");
	    
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Integer number = (Integer)spinner.getValue();
				num = number.intValue();
				int select = -1;
				for(int i = 0 ; i < rdbs.length; i++){
					if(rdbs[i].isSelected()){
						select = i;
						break;
					}
				}
				//確認properties是否存在
				if(propChecker()==false){
					System.out.println("確認config.properties都有存在");
					JOptionPane.showMessageDialog(null, "確認config.properties都有存在");
					frame.dispose();
					return;
				}
				if(select!=-1){
					
					progressBar.setValue(0);
					FileUtil.generateResult(outputPath+"Line.csv", 0, progressBar, surnameSeeds, chinameSeeds, accountSeeds, passwordlen, num);
					FileUtil.generateResult(outputPath+"WebChat.csv", 1, progressBar, surnameSeeds, chinameSeeds, accountSeeds, passwordlen, num);
					FileUtil.generateResult(outputPath+"Facebook.csv", 2, progressBar, surnameSeeds, chinameSeeds, accountSeeds, passwordlen, num);
					button.setEnabled(false);
					
					button.setEnabled(true);
					progressBar.setValue(100);
					JOptionPane.showMessageDialog(null, "已產生帳密文件result.csv");
				}
				else{
					JOptionPane.showMessageDialog(null, "請至少選一個類別");
				}
			}
		});
		
		
		JLabel label_1 = new JLabel("進度");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_1))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button)
								.addComponent(rdbtnWechat)
								.addComponent(rdbtnLine)
								.addComponent(rdbtnFacebook)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(208))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(label))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnLine)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnWechat)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnFacebook)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(107, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);	

	}

	@Override
	public void stateChanged(ChangeEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==button){
//			timer.start();
//		}
//		if(e.getSource()==timer){
//			int value= progressBar.getValue();
//			if(res_num < num){
//			    progressBar.setValue(++value);
////				System.out.println("num : "+num);
////				
////				System.out.println("value : "+num);
//			   // value= progressBar.getValue();
//			  //  System.out.println("value : "+num);
//			}else{
//				timer.stop();
//				button.setEnabled(true);
//				JOptionPane.showMessageDialog(null, "已產生帳密文件result.csv");
//			}
//		}
	}
	
	public static void loadProperties(){
	    InputStream input = null;
	    try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			System.out.println("readSurnameFile:"+ prop.getProperty(chineseSurnameFile));
			System.out.println("output:" + prop.getProperty(outputFile));
			System.out.println("passwordLength: " + prop.getProperty(passwordLength ));
			System.out.println("englishNameFile: " + prop.getProperty(englishNameFile));
		    System.out.println("chineseNameFile: " + prop.getProperty(chineseNameFile));
			passwordlen =  Integer.parseInt(prop.getProperty(passwordLength));
			outputPath = prop.getProperty(outputFile);
			surnameSeeds = FileUtil.readFile(prop.getProperty(chineseSurnameFile));
			accountSeeds = FileUtil.readFile(prop.getProperty(englishNameFile));
			chinameSeeds = FileUtil.readFile(prop.getProperty(chineseNameFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

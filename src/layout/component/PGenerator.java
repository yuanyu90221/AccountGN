package layout.component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author YuanyuLiang
 *
 * @description 使用thread去產生帳號
 */
public class PGenerator implements Runnable {
	
	static Properties prop = new Properties();
	
	public static final String chineseSurnameFile = "chineseSurnameFile";
	public static final String outputFile = "outputFile";
	public static final String passwordLength = "passwordLength";
	public static final String englishNameFile = "englisheNameFile";
	public static final String chineseNameFile = "chineseNameFile";
	public static final String accountNum = "accountNum";
	private String filepath;
    private int type;
    private int num;
    private static int passwordlen;
    private static String outputPath;
    
    private static String[] surnames;
    private static String[] chinames;
    private static String[] accounts;
    
    private static int accountNumber = 0;
	
    @Override
	public void run() {
		// TODO Auto-generated method stub
		
        FileUtil.generateResult(outputPath+filepath, type, surnames, chinames, accounts, passwordlen, num);
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PGenerator pgrLine = new PGenerator("Line.csv",0,accountNumber);
		PGenerator pgrWebChat = new PGenerator("WebChat.csv",1,accountNumber);
		PGenerator pgrFb = new PGenerator("Facebook.csv",2,accountNumber);
		Thread pgthFb = new Thread(pgrFb);
		Thread pgthWebCh = new Thread(pgrWebChat);
		Thread pgthLine = new Thread(pgrLine);
		pgthFb.start();
		pgthWebCh.start();
		pgthLine.start();
		
	}
	static {
		loadProperties();
	}
	public PGenerator(String filepath, int type, int num) {
		super();
		this.filepath = filepath;
		this.type = type;
		this.num = num;
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
		    System.out.println("accountNum: " + prop.getProperty(accountNum));
			passwordlen =  Integer.parseInt(prop.getProperty(passwordLength));
			accountNumber = Integer.parseInt(prop.getProperty(accountNum));
			outputPath = prop.getProperty(outputFile);
			surnames = FileUtil.readFile(prop.getProperty(chineseSurnameFile));
			accounts = FileUtil.readFile(prop.getProperty(englishNameFile));
			chinames = FileUtil.readFile(prop.getProperty(chineseNameFile));
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

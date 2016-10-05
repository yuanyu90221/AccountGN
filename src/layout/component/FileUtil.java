package layout.component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JProgressBar;

public class FileUtil {
	private static final String COMMA_DELEMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "username,password,account";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String[] result = FileUtil.readFile("surname");
        System.out.println(result.length);
        ArrayList<ResultModel> rmA = new ArrayList<ResultModel>();
        Random r = new Random();
        PassGenerator pg = new PassGenerator(8);
        PassGenerator pg_1 = new PassGenerator(7);
        AccountGenerator ag = new AccountGenerator(1);
        for(int i = 0 ; i  < 4; i ++){
        	int p = r.nextInt(result.length);
        	ResultModel rmd = new ResultModel();
        	rmd.setUsername(result[p]);
        	System.out.println(rmd.getUsername());
        	rmd.setPassword(pg.nextString());
        	rmd.setAccount(ag.nextString()+pg_1.nextString());
        	rmA.add(rmd);
        }
        FileUtil.writeFile("result.csv", 2,null, rmA.toArray(new ResultModel[0]));
	}
	
	public static int generateResult(String filepath, int type, JProgressBar bar, String[] current, int len, int num){
		ArrayList<ResultModel> rmA = new ArrayList<ResultModel>();
        Random r = new Random();
        PassGenerator pg = new PassGenerator(len);
        PassGenerator pg_1 = new PassGenerator(len-1);
        AccountGenerator ag = new AccountGenerator(1);
        for(int i = 0 ; i  <num; i ++){
        	int p = r.nextInt(current.length);
        	ResultModel rmd = new ResultModel();
        	rmd.setUsername(current[p]);
        	System.out.println(rmd.getUsername());
        	rmd.setPassword(pg.nextString());
        	rmd.setAccount(ag.nextString()+pg_1.nextString());
        	rmA.add(rmd);
        }
        return writeFile(filepath, type, bar, rmA.toArray(new ResultModel[0]));
	}
	public static int writeFile(String filepath, int type,JProgressBar bar, ResultModel... rmdArr){
		FileWriter fw = null;
		int result = 0;
		try {
			fw = new FileWriter(filepath);
			// UTF-8 BOM
			fw.append('\ufeff');
			fw.append(FILE_HEADER);
			fw.append(NEW_LINE_SEPARATOR);
			for(int index = 0 ;  index < rmdArr.length; index++){
				writeLine(fw, type,rmdArr[index]);
				fw.append(NEW_LINE_SEPARATOR);
				if(bar !=null){
					bar.setValue(index);
					System.out.println("test"+index);
				}
				result = index;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(fw != null){
				try {
					fw.flush();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		  return result;
		}
	}
    
	public static void writeLine(FileWriter fw,int type, ResultModel rmd) throws IOException{
		    	fw.append(rmd.getUsername());
				fw.append(COMMA_DELEMITER);
				fw.append(rmd.getPassword());
				fw.append(COMMA_DELEMITER);
				fw.append(rmd.getAccount());			
	}
	
	public static String[] readFile(String filepath){
		String[] array = {};
		FileReader fd = null;
		BufferedReader bd = null;
		try {
			fd = new FileReader(filepath);
			bd = new BufferedReader(fd);
			List<String> lines = new ArrayList<String>();
			String line = null;
			while((line = bd.readLine())!=null){
				lines.add(line);
			}
			bd.close();
			array = lines.toArray(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(fd!=null){
				try {
					fd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bd!=null){
				try {
					bd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return array;
	}
}

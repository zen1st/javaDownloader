import java.io.*;

public class FileHelper
{
	private String path;

	public FileHelper(String path)
	{
		this.path = path;
	}

	public boolean ifExist()
	{
		File f = new File(this.path);
		return f.isFile();
	}

	/*
	public StringBuilder getFileContent()
	{
			try (BufferedReader br = new BufferedReader(new FileReader(this.path))) 
			{
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				
				while (line != null) 
				{
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				
				return sb;
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("File doesn't exist.");
			return sb;
	}
	*/
	
	public String getFileContent()
	{
			try (BufferedReader br = new BufferedReader(new FileReader(this.path))) 
			{
				String str = "";
				
				String line = br.readLine();
				
				while (line != null) 
				{
					str+=line;
					str+=System.lineSeparator();
					line = br.readLine();
				}
				
				return str;
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return "File doesn't exist.";
	}
	
	/*
	public static void main(String[] args) 
	{
		String fileName = args[0];
		
		FileHelper fh = new FileHelper(fileName);
		
		System.out.println("Does this file exist? " + fh.ifExist());
		
		System.out.println("File content : " + fh.getFileContent());
	}*/
}
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class Snipper {

	public static void main(String[] args) throws Exception {
		String sourcePath = args[0];
		String targetPath = args[1];
		String filter= args[2];		
		String resourceFile=args[3];		
		File sourceDir = new File(sourcePath);
		File targetDir = new File(targetPath);		
		File[] files = sourceDir.listFiles();
		
		Properties p= new Properties();
		try (Reader rdr= new InputStreamReader(new FileInputStream(resourceFile),"utf-8")) {
			p.load(rdr);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}				
		String fileEncoding=p.getProperty("file.encoding"); 
		
		for (int i = 0; i < files.length; i++) {
			List<String> lines = IOUtils.readLines(new FileInputStream(files[i]), "utf-8");			
			for (int j = 0; j < lines.size(); j++) {
				if(Snipper.filter(lines.get(j), filter)) {
					String snippetFileName = String.format("%s-%d.%s", FilenameUtils.getBaseName(files[i].getName()), j,
							FilenameUtils.getExtension(files[i].getName()));
					String snippetFilePath = String.format("%s/%s", targetDir.getAbsolutePath(), snippetFileName);
					IOUtils.write(Snipper.modify(lines.get(j)), new FileOutputStream(snippetFilePath), "utf-8");
					System.out.format("extracted %s %n", snippetFilePath);
				}
			}
		}
		System.out.println("done");
	}
	
	public static boolean filter(String line, String filter) {
		return line.toLowerCase().startsWith(filter.toLowerCase());
	}
	public static String modify (String line) {
		return line.replace(". ", ".");
	}
	
}

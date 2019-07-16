package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class HtmlIfyer {

	public static void main(String[] args) throws Exception {
		String sourcePath = args[0];
		String targetPath = args[1];		
		
		File sourceDir = new File(sourcePath);
		File targetDir = new File(targetPath);		
		File[] files = sourceDir.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			List<String> lines = IOUtils.readLines(new FileInputStream(files[i]), "utf-8");			
			for (int j = 0; j < lines.size(); j++) {
					String baseName=FilenameUtils.getBaseName(files[i].getName());
					String htmlFileName = String.format("%s.%s", baseName, "html");
					String htmlFilePath = String.format("%s\\%s", targetDir.getAbsolutePath(), htmlFileName);
					try(OutputStream out= new FileOutputStream(htmlFilePath)) {
						IOUtils.write(HtmlIfyer.head(baseName),out,"utf-8");
						IOUtils.write(HtmlIfyer.modify(lines.get(j)), out, "utf-8");
						IOUtils.write(HtmlIfyer.tail(),out,"utf-8");
						System.out.format("created %s %n", htmlFilePath);
					}
					
			}
		}
		System.out.println("done");
	}
	
	
	private static String tail() {
		return String.format("%n</body>%n</html>");
	}


	private static String head(String title) {
		return String.format("<html>%n<body>%n<h2>%s</h2>%n", title);
	}


	public static String modify (String line) {
		return String.format("<p style=\"font-family: Courier New; line-height: 200%%;\">%n%s%n</p>", line);
	}
	
}

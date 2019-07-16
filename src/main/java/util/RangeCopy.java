package util;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class RangeCopy 
{
	
	public static void main(String[] args) throws Exception {		
		String fromDirPath= args[0];
		String toDirPath= args[1];
		int startIndex= Integer.parseInt(args[2]);
		int numberOfFiles= Integer.parseInt(args[3]);
		final String pattern= args[4];				
		System.out.format("copyutil: source-dir=%s, target-dir=%s, from=%d, count=%d, pattern=%s %n", 
				fromDirPath, toDirPath, startIndex, numberOfFiles, pattern);
		
		File fromDir= new File(fromDirPath);
		File[] fromFiles= fromDir.listFiles(f -> f.getName().endsWith(pattern));
		int count=0;
		for(int i=0; i<fromFiles.length; i++) {
			if(i>= startIndex && i < startIndex+numberOfFiles) {
				String toFilePath= String.format("%s/%s", toDirPath,fromFiles[i].getName());
				FileUtils.copyFile(fromFiles[i], new File(toFilePath));
				count++;
				System.out.format("\t copying %d: %s %n", i, fromFiles[i].getName());
			}						
		}
		System.out.format("copyutil: copied %d files.%n", count);
	}

}

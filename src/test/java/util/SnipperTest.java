package util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class SnipperTest {
		
	@Rule
	public TemporaryFolder tempFolder= new TemporaryFolder();
	public File sourceFolder;
	public File targetFolder;
	
	@Before
	public void init() throws Exception {
		sourceFolder= tempFolder.newFolder("source");
		targetFolder= tempFolder.newFolder("target");
	}
	@Test
	public void test() throws Exception {
		makeTestFile("test001.txt", sourceFolder, "001", "002", "true", "003");
		makeTestFile("test002.txt", sourceFolder, "true", "true");
		Snipper.main(new String[] {sourceFolder.getAbsolutePath(), targetFolder.getAbsolutePath(),
			    "true", "src/main/resources/snipper-config.properties"});
		List<String> lines1= readLines(targetFolder,"test001-2.txt");
		assertEquals(1, lines1.size());
		List<String> lines2= readLines(targetFolder,"test002-0.txt");
		assertEquals(1, lines2.size());
		List<String> lines3= readLines(targetFolder,"test002-1.txt");
		assertEquals(1, lines3.size());
		
	}	
	private void makeTestFile(String fileName, File folder, String... lines) throws Exception {
		File file= new File(folder, fileName);
		FileUtils.writeLines(file, "utf8", Arrays.asList(lines));
	}
	private List<String> readLines(File folder, String name) throws Exception {
		return FileUtils.readLines(new File(folder, name));
	}
	
	
}

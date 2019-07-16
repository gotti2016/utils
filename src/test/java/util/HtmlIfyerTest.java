package util;

public class HtmlIfyerTest {


	public void test() throws Exception {
		String sourceDir="C:\\daten\\work\\justiz.innovationworkshop-2019\\personen-erkennung\\Trainingssets\\snippets-3";
		String targetDir="C:\\daten\\work\\justiz.innovationworkshop-2019\\personen-erkennung\\Trainingssets\\snippets-3";
		HtmlIfyer.main(new String[] {sourceDir, targetDir, "Das Oberlandesgericht Wien"});
	}

}

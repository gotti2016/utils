package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class RangeCopyTest {


	public void test() throws Exception
	{
		String[] sources=new String[] {"SENAT01", "SENAT02", "SENAT11", "SENAT12" };		
		
		for(String source:sources) {
			RangeCopy.main(new String[] {"C:\\daten\\work\\justiz.anonym\\FROM-CUSTOMER\\"+source, 
				"C:\\daten\\work\\justiz.innovationworkshop-2019\\personen-erkennung\\Trainingssets\\tranche-2",
				"25", "100", ".odt"});
		}
	}

}

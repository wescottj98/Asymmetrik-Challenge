import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;



public class UnitTests {

	@Test
	public void testTrain() {
		AutoComplete a = new AutoComplete();
		String test = "the third thing that i need to tell you is that this thing does not think thoroughly"; 
		a.train(test);
		ArrayList<Candidate> b = a.getArray();
		String result = "";
		for(Candidate c :b) {
			result += c.getWord() + " ";
		}
		test = test.trim();
		result = result.trim();
		assertTrue(test.equals(result));
	}
	
	@Test
	public void testWordCandidateGetters() {
		WordCandidate test = new WordCandidate("test", 2);
		assertTrue(test.getWord().equals("test"));
		assertTrue(test.getConfidence() == 2);
	}
	
	@Test
	public void testCase1() {
		AutoComplete a = new AutoComplete();
		a.train("The third thing that I need to tell you is that this thing does not think thoroughly.");
		String input = "thi";
		List<Candidate> cands = a.getWords(input);
		String results = "Input: \"" + input + "\" --> ";
		for(int i = 0; i < cands.size(); i++) {
			results += "\""+cands.get(i).getWord()+ "\" (" + cands.get(i).getConfidence().intValue() + "), ";
		}
		results = results.substring(0, results.length()-2);
		System.out.println(results);
		assertTrue(results.equals("Input: \"thi\" --> \"thing\" (2), \"think\" (1), \"third\" (1), \"this\" (1)"));
	}

	@Test
	public void testCase2() {
		AutoComplete a = new AutoComplete();
		a.train("The third thing that I need to tell you is that this thing does not think thoroughly.");
		String input = "nee";
		List<Candidate> cands = a.getWords(input);
		String results = "Input: \"" + input + "\" --> ";
		for(int i = 0; i < cands.size(); i++) {
			results += "\""+cands.get(i).getWord()+ "\" (" + cands.get(i).getConfidence().intValue() + "), ";
		}
		results = results.substring(0, results.length()-2);
		System.out.println(results);
		assertTrue(results.equals("Input: \"nee\" --> \"need\" (1)"));
	}
	
	@Test
	public void testCase3() {
		AutoComplete a = new AutoComplete();
		a.train("The third thing that I need to tell you is that this thing does not think thoroughly.");
		String input = "th";
		List<Candidate> cands = a.getWords(input);
		String results = "Input: \"" + input + "\" --> ";
		for(int i = 0; i < cands.size(); i++) {
			results += "\""+cands.get(i).getWord()+ "\" (" + cands.get(i).getConfidence().intValue() + "), ";
		}
		results = results.substring(0, results.length()-2);
		System.out.println(results);
		assertTrue(results.equals("Input: \"th\" --> \"that\" (2), \"thing\" (2), \"think\" (1), \"this\" (1), \"third\" (1), \"the\" (1), \"thoroughly\" (1)"));
		//I`m not sure how you get the words in this specific order ^
	}
}

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class AutoComplete implements AutocompleteProvider {

	private ArrayList<Candidate> words = new ArrayList<Candidate>();
	
	public AutoComplete() {
	}
	
	public ArrayList<Candidate> getArray(){
		ArrayList<Candidate> wordArray = new ArrayList<Candidate>();
		for(Candidate w: words) {
			wordArray.add(new WordCandidate(w));
		}
		return wordArray;
	}
	
	public List<Candidate> getWords(String fragment){
		//sorts out all the words containing the fragment into preSuggestion
		ArrayList<Candidate> preSuggestion = new ArrayList<Candidate>();
		for(int index = 0; index < words.size(); index++) {
			try {
				if((words.get(index).getWord().substring(0, fragment.length()).equals(fragment))) {		
					preSuggestion.add(words.get(index));
				}
			}catch(java.lang.StringIndexOutOfBoundsException e) {
			}
		}
		//removes duplicates and calculates confidence
		ArrayList<Candidate> postSuggestion = new ArrayList<Candidate>();
		for(int x = 0; x < preSuggestion.size(); x++) {
			for(int y = 0; y< postSuggestion.size(); y++) {
				if(preSuggestion.get(x).equals(postSuggestion.get(y))) {
					WordCandidate nCand = new WordCandidate(postSuggestion.get(y).getWord(), postSuggestion.get(y).getConfidence()+1);
					postSuggestion.remove(y);
					preSuggestion.remove(x);
					postSuggestion.add(y, nCand);
				}	
			}
			postSuggestion.add(preSuggestion.get(x));
				
		}
		//sorts and returns
		return AutoComplete.sortList(postSuggestion);
	}
	public void train(String passage) {
		passage = passage.toLowerCase();
		passage = passage.replace(".", " ");
		passage = passage.replace("!", " ");
		passage = passage.replace("?", " ");
		passage += " ";
		//adds each individual word to the list of words
		while(passage.indexOf(' ') != -1) {
			WordCandidate potentialWord = new WordCandidate(passage.substring(0, passage.indexOf(' ')),1);
			words.add(potentialWord);
			passage = passage.substring(passage.indexOf(' ')+1);
		}
	}
	
	private static ArrayList<Candidate> sortList(ArrayList<Candidate> list) {
		//converts to WordCandidate sorts and converts back to Candidate
		ArrayList<WordCandidate> wordList = new ArrayList<WordCandidate>();
		for(int i = 0; i<list.size();i++) {
			wordList.add((WordCandidate)list.get(i));
		}
		ArrayList<Candidate> result = new ArrayList<Candidate>();
		Collections.sort(wordList);
		for(int i = 0; i<wordList.size();i++) {
			result.add((Candidate)wordList.get(i));
		}
		return result;
	}

}

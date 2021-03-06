
public class WordCandidate implements Candidate, Comparable{

	//instance variables
	private String word;
	private int confidence;
	
	
	//constructors
	public WordCandidate(String word, int confidence) {
		this.word = word;
		this.confidence = confidence;
	}
	public WordCandidate(Candidate word) {
		this.word = word.getWord();
		this.confidence = word.getConfidence();
	}

	
	public String getWord() {
		return word;
	}
	public Integer getConfidence() {
		return new Integer(confidence);
	}
	
	
	public boolean equals(Object obj) {
		if(this.compareTo(obj) == 0) {
			return true;
		}
		return false;
	}
	
	
	public int compareTo(Object obj) {
		if(this == obj) {
			return 0;
		}
		if(!(obj instanceof WordCandidate)) {
			return -1;
		}
		WordCandidate toEquate = (WordCandidate)obj;
		//compares confidence from greatest to least instead of least to greatest
		int result = toEquate.getConfidence().intValue() - this.confidence;
		//if confidence is the same compares strings lexicographically 
		if(result == 0) {
			result = this.word.compareTo(toEquate.getWord());
		}
		return result;
	}
}

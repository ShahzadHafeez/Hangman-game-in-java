package ap_assignment1;

import java.util.Random;
import java.util.Scanner;


public class Hangman {

	StringBuffer secretWord;
	StringBuffer guessWord;
	int wordLength;
	int guessLeft;
	int guessCorrect;
	
	Scanner scan=new Scanner(System.in);
	
	{
		System.out.println("Welcome to Hangman!");
	}
	
	Hangman()
	{
		HangmanLexicon obj=new HangmanLexicon();
		int wordCount=obj.getWordCount();
		
		Random randObj=new Random();
		int index=randObj.nextInt(wordCount);
		
		secretWord=new StringBuffer(obj.getWord(index).toLowerCase());
		wordLength=secretWord.length();
		
		System.out.println("--> "+secretWord);
		guessWord=new StringBuffer();
		
		for (int i=0;i<wordLength;i++)
		{
			guessWord.append("-");
		}
		guessLeft=8;
		guessCorrect=0;
		
	}
	
	void print()
	{
		System.out.println();
		System.out.println("The word now looks like this: "+guessWord);
		System.out.println("You have "+guessLeft+" guesses Left.");
	}
	
	char getInput()
	{
		System.out.print("Your guess: ");
		String input = (scan.next()).toLowerCase();
		
		
		while(input.length()==0 || input.length()!=1 || (input.charAt(0)<97 || input.charAt(0)>=123)  )
		{
			
			System.out.print("illegal Input! Your guess again: ");
			input=(scan.next()).toLowerCase();
		}
		
		
		return input.charAt(0);
	}
	
	
	
	boolean isCharExist(char ch)
	{
		
		for(int i=0;i<wordLength;i++)
		{
			if (secretWord.charAt(i)==ch && guessWord.charAt(i)=='-')
			{
				return true;
			}
			
		}
		
		
		return false;
	}
	
	
	
	int findCharIndex(char ch)
	{
		for (int i=0;i<wordLength;i++)
		{
			if (secretWord.charAt(i)==ch && guessWord.charAt(i)=='-')
			{
				return i;
			}
		}
		
		
		return -1;
	}
	
	
	void run()
	{
		
		
		print();
		char guessChar=getInput();
		
		int indexOfGuess=0;
		
		
		if (isCharExist(guessChar))
		{
			System.out.println("That guess is correct.");
			while(isCharExist(guessChar)) {
				indexOfGuess=findCharIndex(guessChar);
				guessWord.setCharAt(indexOfGuess, guessChar);
				
				guessCorrect+=1;
			}
		}
		else
		{
			System.out.println("There is no '"+guessChar+"' in the Word.");
			guessLeft-=1;
		}
		
		
		
	}
	
	
	void play()
	{
		while(guessLeft > 0 && guessCorrect < wordLength  )
		{
			run();
		}
		
		if (guessLeft<=0)
		{
			System.out.println();
			System.out.println("You are completely hung.");
			System.out.println("The word was: "+secretWord );
			System.out.println("---> You lose <---");
		}
		else
		{
			System.out.println();
			System.out.println("---> You win <---");
		}
		
	}
	
	
}

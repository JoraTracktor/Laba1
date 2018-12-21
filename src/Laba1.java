import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Laba1 {

	public static void main(String[] args) throws IOException {
		//"������� ���. ����� � ���. ��� 1.txt"
		//"����� � ���. �����.txt"
		//"��������� �����.txt"
		//shift();
		//frequencyOfSymbols("����� � ���. �����.txt");
		ArrayList shiftedList = frequencyOfSymbols("��������� �����.txt");
		ArrayList warAndPeace = frequencyOfSymbols("������� ���. ����� � ���. ��� 1.txt");
		decryption ("��������� �����.txt",shiftedList, warAndPeace);
		
	}
	
	public static void shift() {
		try{
			FileReader reader = new FileReader("����� � ���. �����.txt");
			FileWriter writer = new FileWriter("��������� �����.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line, newLine ="";
			int key = 1;
			while (bufferedReader.ready()){
				line = bufferedReader.readLine();   
				for (char c : line.toCharArray()) {
					if (c >='�' && c <= '�') {
						newLine += (char)  (((c + key) - 1040) % 32 + 1040); 
			        }
			        else if (c >='�' && c <= '�') {
			        	newLine += (char) (((c + key) - 1072) % 32 + 1072);
			        }
			        else
			        	newLine += (char) c;
				   }
				   writer.write(newLine);
				   writer.append("\r\n");				 
				   newLine ="";
			   }			
			bufferedReader.close();
			writer.close();
			} catch (IOException e) {
				System.out.println("������");
			}
	}
	
	

	public static ArrayList frequencyOfSymbols(String file) throws IOException {
		Map<Character, Integer> map = new HashMap<>();
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		ArrayList<Character> list = new ArrayList<>();
		
		int number = numberOfSymbols(file);
		System.out.println("Number of symbols in text " + number);
		
		while (bufferedReader.ready()){
			line = bufferedReader.readLine();
			 for (char c : line.toCharArray()) {	
		        	if ( c >='�' && c <= '�') {
		        		map.put(c, map.containsKey(c)  ?  (map.get(c)+1) : 1);
		        	}
			 }
		}
		for (Map.Entry<Character, Integer> k : map.entrySet()){ 
			int min = Integer.MAX_VALUE; 
			Character save = null ; 
			for (Map.Entry<Character, Integer> j : map.entrySet()){ 
				if (j.getValue()<min) { 
					min = j.getValue(); 
					save = j.getKey(); 
				} 
			} 
			list.add(save); 
			map.put(save, Integer.MAX_VALUE); 
		}
		System.out.println(list);
		return list;
	}
	
	public static Integer numberOfSymbols(String file) throws IOException {
		int number = 0;
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		while (bufferedReader.ready()) {
			line = bufferedReader.readLine();   
			for (char c : line.toCharArray()) {
				if (c>='�' && c <= '�'||c>='�' && c <= '�') {
					number++;
				}		
			}
		}
		return number;
	}
	
	public static void decryption(String file, ArrayList shiftedList, ArrayList warAndPeace) throws IOException {
		FileReader reader = new FileReader(file);
		FileWriter writer = new FileWriter("�������������� �����.txt");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line, newLine = "";
		while (bufferedReader.ready()){
			line = bufferedReader.readLine();
			for (char c : line.toCharArray()) {
				if ((c >='�' && c <= '�')) {// || (c >='�' && c <= '�')) {
					newLine +=  warAndPeace.get(shiftedList.indexOf(c));
				}
				else
					newLine += c;	
				//System.out.println(c);
				//System.out.println(shiftedList.indexOf(c));
			}
			writer.write(newLine);
			writer.append("\r\n");				 
			newLine ="";
		}
		bufferedReader.close();
		writer.close();
	}
	
	public static void bigrams() {
		
	}
}


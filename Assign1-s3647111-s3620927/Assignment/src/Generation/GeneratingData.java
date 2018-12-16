import java.util.Random;
import java.io.*;
public class GeneratingData {
	
	private int RV;
	private int RE;
	private int S;
	private int N;
	private int AE;
	private int AV;
	private int len;
	private PrintWriter outWrite;
	
	//Construtor
	public GeneratingData(int len, int AV, int AE, int RV, int RE, int S, int N) throws IOException {
		this.RV= RV;
		this.RE= RE;
		this.S= S;
		this.N= N;
		this.AE= AE;
		this.AV= AV;
		this.len= len;
		outWrite= new PrintWriter(new BufferedWriter(new FileWriter(OutputFile(),true)));	
	}
	
	private String OutputFile(){
		File outputF;
		int indFile= 1;
		
		do {
			outputF= new File("src"+ indFile +".in" );
			indFile++;
		}
		while(outputF.isFile());{
			return outputF.getName();			
		}
	}
	
	private void ReVert() throws IOException {
		Random ran= new Random();
		for(int j= 0; j< this.RV; j++) {
			int rand= ran.nextInt(len);
			outWrite.println("RV"+ " "+ rand);
		}
		
	}
	

	private void ReEdge() throws IOException {
		Random ran= new Random();
		for(int j= 0; j< this.RE; j++) {
			int rand= ran.nextInt(len);
			outWrite.println("RE"+ " "+ rand+" "+ rand);
		}
		
	}
	

	private void ShortPath() throws IOException {
		Random ran= new Random();
		for(int j= 0; j< this.S; j++) {
			int rand= ran.nextInt(len);
			outWrite.println("S"+ " "+ rand +" "+ rand);
		}
		
	}
	

	private void Neigh() throws IOException {
		Random ran= new Random();
		for(int j= 0; j< this.N; j++) {
			int rand= ran.nextInt(len);
			outWrite.println("N"+ " "+ rand);
		}
		
	}
	

	private void AddEdge() throws IOException {
		Random ran= new Random();
		for(int j= 0; j< this.AE; j++) {
			int rand= ran.nextInt(len);
			outWrite.println("AE"+ " "+ rand+ " "+ rand);
		}
		
	}
	

	private void AddVert() throws IOException {
		Random ran= new Random();
		for(int j= 0; j< this.AV; j++) {
			int rand= ran.nextInt(len);
			outWrite.println("AV"+ " "+ rand);
		}
		
	}
	
	public void print() throws IOException{
		outWrite.println("V");
		outWrite.print("E");
		outWrite.println("Q");
	}
	
	public static void usage() throws IOException {
		System.err.println("Generating Data for Graph"+
							"Add number of Vertices"+ 
							"Add number of edges"+
							"Remove how many vertices"+
							"Remove how many edges"+
							"Check Neighbours"+
							"Check Shortest Distance");
		System.exit(1);
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length< 7 || args.length> 7) {
			usage();
			return;
		}
		try {
			GeneratingData GR= new GeneratingData(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
			GR.AddVert();
			GR.AddEdge();
			GR.ReVert();
			GR.ReEdge();
			GR.ShortPath();
			GR.Neigh();
			GR.print();
		}
		catch(Exception a){
			System.err.println(a.getMessage());
		}
	}
}

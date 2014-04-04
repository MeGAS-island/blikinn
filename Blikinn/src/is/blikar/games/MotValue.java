package is.blikar.games;

/**
 * 
 * @author Guðný Lára Guðmundsdóttir
 * @since 6.11.13
 * Description: This class has methods save information about meets
 *
 */

public class MotValue {
	String motstring, grein,kyn;
	int meet;
	
	public void setMeet(int meet){
		this.meet=meet;
	}
	
	public int getMeet(){
		return meet;
	}
	
	public String getKyn(){
		return kyn;
	}
	
	public void setKyn(String kyn){
		this.kyn=kyn;
	}
	
	public String getGrein(){
		return grein;
	}
	
	public void setGrein(String grein){
		this.grein=grein;
	}
	
	public String getMot(){
		return motstring;
	}

	public void setMot(String motstring){
		String[] new_meet;
		new_meet=motstring.split("null");
		this.motstring=new_meet[0];
	}
}
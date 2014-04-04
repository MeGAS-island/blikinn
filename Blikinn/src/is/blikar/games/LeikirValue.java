package is.blikar.games;

/**
 * 
 * @author Guðný Lára Guðmundsdóttir
 * @since 26.11.13
 * Description: This class has methods save information about games
 *
 */

public class LeikirValue{
	String heimalid, utilid, mork_uti, mork_heima, date, stada, nyjasta_mark_heima, nyjasta_mark_uti;
	int meet;
	boolean done;

	public boolean getDone(){
		return done;
	}
	
	public void setDone(boolean done){
		this.done=done;
	}
	
	public String getNyjastaMarkHeima(){
		return nyjasta_mark_heima;
	}
	
	public void setNyjastaMarkHeima(String nyjasta_mark_heima){
		String marktext;
		if(nyjasta_mark_heima != null && nyjasta_mark_heima != "") {
			String[] split = nyjasta_mark_heima.split(" ");
			marktext =split[0]+ " á " + split[1] + " mínútu " ;
		}
		else marktext ="";
		this.nyjasta_mark_heima = marktext;
	}
	
	public String getNyjastaMarkUti(){
		return nyjasta_mark_uti;
	}
	
	public void setNyjastaMarkUti(String nyjasta_mark_uti){
		String marktext;
		if(nyjasta_mark_uti != null && nyjasta_mark_uti != "") {
			String[] split = nyjasta_mark_uti.split(" ");
			marktext =split[0]+ " á " + split[1] + " mínútu " ;
		}
		else marktext ="";
		this.nyjasta_mark_uti = marktext;
	}
	
	public String getHeimalid() {
		return heimalid;
	}

	public void setHeimalid(String heimalid) {
		this.heimalid = heimalid;
	}
	
	public void setMeet(int meet){
		this.meet=meet;
	}
	
	public int getMeet(){
		return meet;
	}
	
	public String getUtilid() {
		return utilid;
	}

	public void setUtilid(String utilid) {
		this.utilid = utilid;
	}
	
	public String getMarkHeima() {
		return mork_heima;
	}

	public void setMarkHeima(String mork_heima) {
		this.mork_heima = mork_heima;
	}
	
	public String getMarkUti() {
		return mork_uti;
	}

	public void setMarkUti(String mork_uti) {
		this.mork_uti = mork_uti;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		String[] splitdate = date.split("-");
		String year = splitdate[0];
		String month = splitdate[1];
		String day_time[] = splitdate[2].split("T");
		String day = day_time[0];
		String times[] = day_time[1].split(":");
		String time = times[0]+":"+times[1];
		date = day + "." + month + "." + year +" "+time;
		this.date = date;
	}
	
	public String getStada(){
		return stada;
	}
	
	public void setStada(String stada){
		this.stada=stada;
	}
}
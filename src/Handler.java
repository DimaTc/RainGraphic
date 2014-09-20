import java.awt.Graphics2D;
import java.util.LinkedList;


public class Handler {
	
	private LinkedList<WinObject> tmp_objects = new LinkedList<WinObject>();
	private LinkedList<WinObject> objects = new LinkedList<WinObject>();
	
	public Handler(){
		
	}
	
	public void draw(Graphics2D g){
		for(int i = 0 ; i < objects.size(); i++)
			objects.get(i).draw(g);
	}
	
	public void update(){
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).update();
			
		}
	}
	
	public void addObject(WinObject obj){
		tmp_objects.add(obj);
	}
	
	public void removeObject(int id){
		tmp_objects.remove(id);
	}
	
	public void orderlist(){
		for(int i = 0 ; i < tmp_objects.size() ; i++){
			WinObject min = tmp_objects.get(i);
			for(int j = i; j < tmp_objects.size(); j++)
				if(min.size < tmp_objects.get(j).size){
					min = tmp_objects.get(j);	
					tmp_objects.set(j, tmp_objects.get(i)) ;
					tmp_objects.set(i, min);
					
				}
		}
		objects = tmp_objects;
		tmp_objects.remove();
	}
}

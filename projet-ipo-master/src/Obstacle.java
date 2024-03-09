public class Obstacle extends Entite{
    public Obstacle(){
        super(3);
    }
    public Obstacle(int res){
        super(res);
    }
    public String toString(String background){
        if (resistance>=3)
            return "@@@";
        if (resistance==1){
            return background.charAt(0) + "@" + background.charAt(background.length()-1);
        }else return "@" + background.charAt(1) + "@";
    }
}

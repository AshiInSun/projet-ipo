public class CaseLibre extends CaseTraversable{
    public CaseLibre(int l , int c){
        super(l, c, null);
    }
    public CaseLibre(int l , int c,Entite e){
        super(l, c, e);
    }
    public String toString(){
        return this.toString("   ");
    }
    public boolean estLibre(){
        return contenu==null;
    }
    public String toString(String background){
        if(!this.estLibre()){
            return this.contenu.toString(background);
        }else{
            return background;       
        }
    }
}

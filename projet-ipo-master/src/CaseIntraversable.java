public class CaseIntraversable extends Case{
    public CaseIntraversable(int l , int c){
        super(l,c);
    }
    public void vide(){}
    public boolean estLibre(){return false;}
    public String toString(){
        return "###";
    }
    public void entre(Entite e){
        throw new IllegalAccessError("Case intraversable impossible d'y acceder");
    }
}

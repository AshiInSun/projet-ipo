public class Sortie extends CaseTraversable{
    public Sortie(int l, int c){
        super(l,c);
    }
    public boolean estLibre(){return true;}
    public String toString(){
        return "( )";
    }
    public void entre(Entite e){
        if (e instanceof Personnage) {
            e.sort();
            Jeu.sortis++;
        }else{
            e.nouvelleDir();
        }
    }
}

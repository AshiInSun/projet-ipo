public class Joueur extends EntiteMobile{

    public Joueur(Direction direc,int x) {
        super(direc,x);
    }
    public String toString() {return toString("   ");}
    public String toString(String background) {
        return background.charAt(0) + "P" + background.charAt(2);
    }
    public void action(Case courante,Case cible){
        //verifier les différents cas
        if (cible instanceof CaseIntraversable)
            return;
        else if (cible instanceof Sortie) {
            cible.entre(this);
            courante.vide();
        }else if (cible.estLibre()) {
            try{
                cible.entre(this);
                courante.vide();
            }catch(IllegalAccessError e){
                return;
            }
        }
    }public void mortDuJoueur(){
        if (this.estMort())
            throw new RuntimeException("Mort du joueur");
    }
}


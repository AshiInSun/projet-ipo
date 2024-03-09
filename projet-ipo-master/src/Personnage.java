public class Personnage extends EntiteMobile {
    public Personnage(Direction d){
        super(d);
    }
    public String toString(String background){
        switch(direction){
            case nord : return background.charAt(0) + "^" + background.charAt(2);
            case sud : return background.charAt(0) +  "v" + background.charAt(2);
            case est : return background.charAt(0) + ">" + background.charAt(2);
            case ouest : return background.charAt(0) + "<" + background.charAt(2);
            default : return background.charAt(0) + "Perso" + background.charAt(2);
        }
    }public void action(Case courante,Case cible){
        //verifier les différents cas
        if (cible instanceof CaseIntraversable)
            this.nouvelleDir();
        else if (cible instanceof Sortie) {
            this.sort();
            courante.vide();
            Jeu.increSortis();
        }else {
            if(cible.estLibre()){
                cible.entre(this);
                courante.vide();
            }
            else{
                Entite e = ((CaseTraversable)cible).getContenu();
                if (e instanceof Obstacle){e.receiveDMG(cible); this.nouvelleDir();}
                else {this.nouvelleDir();}
            }
        }
    }
}

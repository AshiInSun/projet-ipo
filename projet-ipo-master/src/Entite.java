public abstract class Entite {


    protected int resistance;
    public Entite(int res){resistance = res;}
    public abstract String toString(String background);
    public void receiveDMG(Case c){
        if (resistance==1)
            c.vide();
        resistance--;
    }
    public void sort(){resistance = 0;}
    public void nouvelleDir(){return;}
    public boolean enVie(){return resistance>0;}
    public boolean estMort(){return !this.enVie();}
    public int getResistance() {
        return resistance;
    }
}

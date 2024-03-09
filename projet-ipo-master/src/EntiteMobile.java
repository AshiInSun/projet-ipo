public abstract class EntiteMobile extends Entite{
    protected Direction direction;

    public EntiteMobile(Direction direc){
        super(3);
        direction = direc;
    }
    public EntiteMobile(Direction direc,int x){
        super(x);
        direction = direc;
    }
    public abstract String toString(String background);

    public void nouvelleDir(){direction = Direction.random();}

    public Direction getDir(){return direction;}
    public abstract void action(Case courante, Case cible);

}
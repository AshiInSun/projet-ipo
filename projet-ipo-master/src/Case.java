public abstract class Case {
    protected final int lig,col;
    public Case(int l, int c){
        lig = l;
        col = c;
    }
    public abstract boolean estLibre();
    public abstract void entre(Entite e);
    public abstract void vide();
    public int getLig(){return lig;}
    public int getCol(){return col;}
}
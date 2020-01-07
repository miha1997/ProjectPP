// generated with ast extension for cup
// version 0.8
// 7/0/2020 10:27:23


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private DesignatorStart DesignatorStart;
    private DesignatorArrayPart DesignatorArrayPart;

    public Designator (DesignatorStart DesignatorStart, DesignatorArrayPart DesignatorArrayPart) {
        this.DesignatorStart=DesignatorStart;
        if(DesignatorStart!=null) DesignatorStart.setParent(this);
        this.DesignatorArrayPart=DesignatorArrayPart;
        if(DesignatorArrayPart!=null) DesignatorArrayPart.setParent(this);
    }

    public DesignatorStart getDesignatorStart() {
        return DesignatorStart;
    }

    public void setDesignatorStart(DesignatorStart DesignatorStart) {
        this.DesignatorStart=DesignatorStart;
    }

    public DesignatorArrayPart getDesignatorArrayPart() {
        return DesignatorArrayPart;
    }

    public void setDesignatorArrayPart(DesignatorArrayPart DesignatorArrayPart) {
        this.DesignatorArrayPart=DesignatorArrayPart;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStart!=null) DesignatorStart.accept(visitor);
        if(DesignatorArrayPart!=null) DesignatorArrayPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStart!=null) DesignatorStart.traverseTopDown(visitor);
        if(DesignatorArrayPart!=null) DesignatorArrayPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStart!=null) DesignatorStart.traverseBottomUp(visitor);
        if(DesignatorArrayPart!=null) DesignatorArrayPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        if(DesignatorStart!=null)
            buffer.append(DesignatorStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorArrayPart!=null)
            buffer.append(DesignatorArrayPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}

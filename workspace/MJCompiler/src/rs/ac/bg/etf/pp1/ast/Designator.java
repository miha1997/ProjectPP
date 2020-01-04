// generated with ast extension for cup
// version 0.8
// 4/0/2020 17:30:8


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String designatorName;
    private DesignatorArrayPart DesignatorArrayPart;

    public Designator (String designatorName, DesignatorArrayPart DesignatorArrayPart) {
        this.designatorName=designatorName;
        this.DesignatorArrayPart=DesignatorArrayPart;
        if(DesignatorArrayPart!=null) DesignatorArrayPart.setParent(this);
    }

    public String getDesignatorName() {
        return designatorName;
    }

    public void setDesignatorName(String designatorName) {
        this.designatorName=designatorName;
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
        if(DesignatorArrayPart!=null) DesignatorArrayPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrayPart!=null) DesignatorArrayPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrayPart!=null) DesignatorArrayPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        buffer.append(" "+tab+designatorName);
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

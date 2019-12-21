// generated with ast extension for cup
// version 0.8
// 21/11/2019 15:46:15


package rs.ac.bg.etf.pp1.ast;

public class NewFactor extends Factor {

    private Type Type;
    private FactorNewArrayPart FactorNewArrayPart;

    public NewFactor (Type Type, FactorNewArrayPart FactorNewArrayPart) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.FactorNewArrayPart=FactorNewArrayPart;
        if(FactorNewArrayPart!=null) FactorNewArrayPart.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public FactorNewArrayPart getFactorNewArrayPart() {
        return FactorNewArrayPart;
    }

    public void setFactorNewArrayPart(FactorNewArrayPart FactorNewArrayPart) {
        this.FactorNewArrayPart=FactorNewArrayPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(FactorNewArrayPart!=null) FactorNewArrayPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FactorNewArrayPart!=null) FactorNewArrayPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FactorNewArrayPart!=null) FactorNewArrayPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NewFactor(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorNewArrayPart!=null)
            buffer.append(FactorNewArrayPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NewFactor]");
        return buffer.toString();
    }
}

// generated with ast extension for cup
// version 0.8
// 2/0/2020 22:18:51


package rs.ac.bg.etf.pp1.ast;

public class DesignatorFactor extends Factor {

    private Designator Designator;
    private FactorMethodPart FactorMethodPart;

    public DesignatorFactor (Designator Designator, FactorMethodPart FactorMethodPart) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FactorMethodPart=FactorMethodPart;
        if(FactorMethodPart!=null) FactorMethodPart.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FactorMethodPart getFactorMethodPart() {
        return FactorMethodPart;
    }

    public void setFactorMethodPart(FactorMethodPart FactorMethodPart) {
        this.FactorMethodPart=FactorMethodPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FactorMethodPart!=null) FactorMethodPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FactorMethodPart!=null) FactorMethodPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FactorMethodPart!=null) FactorMethodPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorFactor(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorMethodPart!=null)
            buffer.append(FactorMethodPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorFactor]");
        return buffer.toString();
    }
}

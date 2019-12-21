// generated with ast extension for cup
// version 0.8
// 21/11/2019 15:46:15


package rs.ac.bg.etf.pp1.ast;

public class ConstDef implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String labelConstName;
    private ConstValue ConstValue;

    public ConstDef (String labelConstName, ConstValue ConstValue) {
        this.labelConstName=labelConstName;
        this.ConstValue=ConstValue;
        if(ConstValue!=null) ConstValue.setParent(this);
    }

    public String getLabelConstName() {
        return labelConstName;
    }

    public void setLabelConstName(String labelConstName) {
        this.labelConstName=labelConstName;
    }

    public ConstValue getConstValue() {
        return ConstValue;
    }

    public void setConstValue(ConstValue ConstValue) {
        this.ConstValue=ConstValue;
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
        if(ConstValue!=null) ConstValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstValue!=null) ConstValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstValue!=null) ConstValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDef(\n");

        buffer.append(" "+tab+labelConstName);
        buffer.append("\n");

        if(ConstValue!=null)
            buffer.append(ConstValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDef]");
        return buffer.toString();
    }
}

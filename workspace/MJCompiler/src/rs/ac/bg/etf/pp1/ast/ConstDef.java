// generated with ast extension for cup
// version 0.8
// 11/0/2020 22:23:17


package rs.ac.bg.etf.pp1.ast;

public class ConstDef implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String constName;
    private AssignOp AssignOp;
    private ConstValue ConstValue;

    public ConstDef (String constName, AssignOp AssignOp, ConstValue ConstValue) {
        this.constName=constName;
        this.AssignOp=AssignOp;
        if(AssignOp!=null) AssignOp.setParent(this);
        this.ConstValue=ConstValue;
        if(ConstValue!=null) ConstValue.setParent(this);
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public AssignOp getAssignOp() {
        return AssignOp;
    }

    public void setAssignOp(AssignOp AssignOp) {
        this.AssignOp=AssignOp;
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
        if(AssignOp!=null) AssignOp.accept(visitor);
        if(ConstValue!=null) ConstValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssignOp!=null) AssignOp.traverseTopDown(visitor);
        if(ConstValue!=null) ConstValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssignOp!=null) AssignOp.traverseBottomUp(visitor);
        if(ConstValue!=null) ConstValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDef(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(AssignOp!=null)
            buffer.append(AssignOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
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

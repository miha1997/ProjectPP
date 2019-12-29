// generated with ast extension for cup
// version 0.8
// 29/11/2019 17:7:23


package rs.ac.bg.etf.pp1.ast;

public class VarDef implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String varName;
    private ArrayVarDef ArrayVarDef;

    public VarDef (String varName, ArrayVarDef ArrayVarDef) {
        this.varName=varName;
        this.ArrayVarDef=ArrayVarDef;
        if(ArrayVarDef!=null) ArrayVarDef.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ArrayVarDef getArrayVarDef() {
        return ArrayVarDef;
    }

    public void setArrayVarDef(ArrayVarDef ArrayVarDef) {
        this.ArrayVarDef=ArrayVarDef;
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
        if(ArrayVarDef!=null) ArrayVarDef.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayVarDef!=null) ArrayVarDef.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayVarDef!=null) ArrayVarDef.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDef(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ArrayVarDef!=null)
            buffer.append(ArrayVarDef.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDef]");
        return buffer.toString();
    }
}

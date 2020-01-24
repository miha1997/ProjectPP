// generated with ast extension for cup
// version 0.8
// 11/0/2020 22:23:17


package rs.ac.bg.etf.pp1.ast;

public class EmptyMethodVariableDeclarationList extends MethodVarDeclList {

    public EmptyMethodVariableDeclarationList () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EmptyMethodVariableDeclarationList(\n");

        buffer.append(tab);
        buffer.append(") [EmptyMethodVariableDeclarationList]");
        return buffer.toString();
    }
}

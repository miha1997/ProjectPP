// generated with ast extension for cup
// version 0.8
// 2/0/2020 22:18:51


package rs.ac.bg.etf.pp1.ast;

public class NoMultiDeclList extends MultiDeclList {

    public NoMultiDeclList () {
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
        buffer.append("NoMultiDeclList(\n");

        buffer.append(tab);
        buffer.append(") [NoMultiDeclList]");
        return buffer.toString();
    }
}

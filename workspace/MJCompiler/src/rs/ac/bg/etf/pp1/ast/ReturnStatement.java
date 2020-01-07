// generated with ast extension for cup
// version 0.8
// 7/0/2020 10:27:23


package rs.ac.bg.etf.pp1.ast;

public class ReturnStatement extends Statement {

    private ExpressionPart ExpressionPart;

    public ReturnStatement (ExpressionPart ExpressionPart) {
        this.ExpressionPart=ExpressionPart;
        if(ExpressionPart!=null) ExpressionPart.setParent(this);
    }

    public ExpressionPart getExpressionPart() {
        return ExpressionPart;
    }

    public void setExpressionPart(ExpressionPart ExpressionPart) {
        this.ExpressionPart=ExpressionPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExpressionPart!=null) ExpressionPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExpressionPart!=null) ExpressionPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExpressionPart!=null) ExpressionPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnStatement(\n");

        if(ExpressionPart!=null)
            buffer.append(ExpressionPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnStatement]");
        return buffer.toString();
    }
}

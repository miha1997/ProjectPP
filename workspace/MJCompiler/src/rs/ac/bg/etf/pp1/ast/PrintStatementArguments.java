// generated with ast extension for cup
// version 0.8
// 29/11/2019 17:7:23


package rs.ac.bg.etf.pp1.ast;

public class PrintStatementArguments extends Statement {

    private Expression Expression;
    private Integer labelNumConst;

    public PrintStatementArguments (Expression Expression, Integer labelNumConst) {
        this.Expression=Expression;
        if(Expression!=null) Expression.setParent(this);
        this.labelNumConst=labelNumConst;
    }

    public Expression getExpression() {
        return Expression;
    }

    public void setExpression(Expression Expression) {
        this.Expression=Expression;
    }

    public Integer getLabelNumConst() {
        return labelNumConst;
    }

    public void setLabelNumConst(Integer labelNumConst) {
        this.labelNumConst=labelNumConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expression!=null) Expression.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expression!=null) Expression.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expression!=null) Expression.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatementArguments(\n");

        if(Expression!=null)
            buffer.append(Expression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+labelNumConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatementArguments]");
        return buffer.toString();
    }
}

// generated with ast extension for cup
// version 0.8
// 21/11/2019 15:46:15


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private MethodType MethodType;
    private String labelMethodName;
    private MethodVarDeclList MethodVarDeclList;
    private StatementList StatementList;

    public MethodDecl (MethodType MethodType, String labelMethodName, MethodVarDeclList MethodVarDeclList, StatementList StatementList) {
        this.MethodType=MethodType;
        if(MethodType!=null) MethodType.setParent(this);
        this.labelMethodName=labelMethodName;
        this.MethodVarDeclList=MethodVarDeclList;
        if(MethodVarDeclList!=null) MethodVarDeclList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodType getMethodType() {
        return MethodType;
    }

    public void setMethodType(MethodType MethodType) {
        this.MethodType=MethodType;
    }

    public String getLabelMethodName() {
        return labelMethodName;
    }

    public void setLabelMethodName(String labelMethodName) {
        this.labelMethodName=labelMethodName;
    }

    public MethodVarDeclList getMethodVarDeclList() {
        return MethodVarDeclList;
    }

    public void setMethodVarDeclList(MethodVarDeclList MethodVarDeclList) {
        this.MethodVarDeclList=MethodVarDeclList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
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
        if(MethodType!=null) MethodType.accept(visitor);
        if(MethodVarDeclList!=null) MethodVarDeclList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodType!=null) MethodType.traverseTopDown(visitor);
        if(MethodVarDeclList!=null) MethodVarDeclList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodType!=null) MethodType.traverseBottomUp(visitor);
        if(MethodVarDeclList!=null) MethodVarDeclList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodType!=null)
            buffer.append(MethodType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+labelMethodName);
        buffer.append("\n");

        if(MethodVarDeclList!=null)
            buffer.append(MethodVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}

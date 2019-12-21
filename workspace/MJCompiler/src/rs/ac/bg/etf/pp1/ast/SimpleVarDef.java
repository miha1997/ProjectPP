// generated with ast extension for cup
// version 0.8
// 21/11/2019 15:46:15


package rs.ac.bg.etf.pp1.ast;

public class SimpleVarDef extends VarDef {

    private String labelVarName;

    public SimpleVarDef (String labelVarName) {
        this.labelVarName=labelVarName;
    }

    public String getLabelVarName() {
        return labelVarName;
    }

    public void setLabelVarName(String labelVarName) {
        this.labelVarName=labelVarName;
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
        buffer.append("SimpleVarDef(\n");

        buffer.append(" "+tab+labelVarName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleVarDef]");
        return buffer.toString();
    }
}

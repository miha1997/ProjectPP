// generated with ast extension for cup
// version 0.8
// 11/0/2020 22:23:17


package rs.ac.bg.etf.pp1.ast;

public class ConstantValueBool extends ConstValue {

    private Boolean value;

    public ConstantValueBool (Boolean value) {
        this.value=value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value=value;
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
        buffer.append("ConstantValueBool(\n");

        buffer.append(" "+tab+value);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantValueBool]");
        return buffer.toString();
    }
}

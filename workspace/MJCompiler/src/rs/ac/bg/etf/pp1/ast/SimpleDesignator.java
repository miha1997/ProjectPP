// generated with ast extension for cup
// version 0.8
// 21/11/2019 15:46:15


package rs.ac.bg.etf.pp1.ast;

public class SimpleDesignator extends Designator {

    private String labelDesignatorName;

    public SimpleDesignator (String labelDesignatorName) {
        this.labelDesignatorName=labelDesignatorName;
    }

    public String getLabelDesignatorName() {
        return labelDesignatorName;
    }

    public void setLabelDesignatorName(String labelDesignatorName) {
        this.labelDesignatorName=labelDesignatorName;
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
        buffer.append("SimpleDesignator(\n");

        buffer.append(" "+tab+labelDesignatorName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleDesignator]");
        return buffer.toString();
    }
}

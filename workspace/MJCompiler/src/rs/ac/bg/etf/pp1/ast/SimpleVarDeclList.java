// generated with ast extension for cup
// version 0.8
// 2/0/2020 22:18:51


package rs.ac.bg.etf.pp1.ast;

public class SimpleVarDeclList extends VarDeclList {

    private VarDeclList VarDeclList;
    private VarDef VarDef;

    public SimpleVarDeclList (VarDeclList VarDeclList, VarDef VarDef) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.VarDef=VarDef;
        if(VarDef!=null) VarDef.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public VarDef getVarDef() {
        return VarDef;
    }

    public void setVarDef(VarDef VarDef) {
        this.VarDef=VarDef;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(VarDef!=null) VarDef.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(VarDef!=null) VarDef.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(VarDef!=null) VarDef.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SimpleVarDeclList(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDef!=null)
            buffer.append(VarDef.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleVarDeclList]");
        return buffer.toString();
    }
}

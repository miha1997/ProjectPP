// generated with ast extension for cup
// version 0.8
// 29/11/2019 17:7:23


package rs.ac.bg.etf.pp1.ast;

public class SimpleMultiDeclList extends MultiDeclList {

    private MultiDeclList MultiDeclList;
    private MultiDecl MultiDecl;

    public SimpleMultiDeclList (MultiDeclList MultiDeclList, MultiDecl MultiDecl) {
        this.MultiDeclList=MultiDeclList;
        if(MultiDeclList!=null) MultiDeclList.setParent(this);
        this.MultiDecl=MultiDecl;
        if(MultiDecl!=null) MultiDecl.setParent(this);
    }

    public MultiDeclList getMultiDeclList() {
        return MultiDeclList;
    }

    public void setMultiDeclList(MultiDeclList MultiDeclList) {
        this.MultiDeclList=MultiDeclList;
    }

    public MultiDecl getMultiDecl() {
        return MultiDecl;
    }

    public void setMultiDecl(MultiDecl MultiDecl) {
        this.MultiDecl=MultiDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MultiDeclList!=null) MultiDeclList.accept(visitor);
        if(MultiDecl!=null) MultiDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultiDeclList!=null) MultiDeclList.traverseTopDown(visitor);
        if(MultiDecl!=null) MultiDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultiDeclList!=null) MultiDeclList.traverseBottomUp(visitor);
        if(MultiDecl!=null) MultiDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SimpleMultiDeclList(\n");

        if(MultiDeclList!=null)
            buffer.append(MultiDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MultiDecl!=null)
            buffer.append(MultiDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleMultiDeclList]");
        return buffer.toString();
    }
}

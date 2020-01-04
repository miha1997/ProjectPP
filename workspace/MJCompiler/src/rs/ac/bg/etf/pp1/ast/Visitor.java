// generated with ast extension for cup
// version 0.8
// 4/0/2020 17:30:8


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(MethodType MethodType);
    public void visit(FactorMethodPart FactorMethodPart);
    public void visit(ArrayVarDef ArrayVarDef);
    public void visit(Factor Factor);
    public void visit(Mulop Mulop);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(AssignOp AssignOp);
    public void visit(DesignatorArrayPart DesignatorArrayPart);
    public void visit(MultiDecl MultiDecl);
    public void visit(MethodVarDeclList MethodVarDeclList);
    public void visit(VarDeclList VarDeclList);
    public void visit(ConstValue ConstValue);
    public void visit(VarDecl VarDecl);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(MultiDeclList MultiDeclList);
    public void visit(Addop Addop);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Statement Statement);
    public void visit(Term Term);
    public void visit(StatementList StatementList);
    public void visit(Expression Expression);
    public void visit(MulopMod MulopMod);
    public void visit(MulopDiv MulopDiv);
    public void visit(MulopMul MulopMul);
    public void visit(AddopSub AddopSub);
    public void visit(AddopAdd AddopAdd);
    public void visit(NoFactorMethodPart NoFactorMethodPart);
    public void visit(SimpleFactorMethodPart SimpleFactorMethodPart);
    public void visit(DesignatorFactor DesignatorFactor);
    public void visit(NewFactor NewFactor);
    public void visit(ExpressFactor ExpressFactor);
    public void visit(ConstFactor ConstFactor);
    public void visit(MulTerm MulTerm);
    public void visit(SimpleTerm SimpleTerm);
    public void visit(MultiExpression MultiExpression);
    public void visit(SubExpression SubExpression);
    public void visit(SimpleExpression SimpleExpression);
    public void visit(NoDesignatorArrayPart NoDesignatorArrayPart);
    public void visit(SimpleDesignatorArrayPart SimpleDesignatorArrayPart);
    public void visit(Designator Designator);
    public void visit(AssignOpError AssignOpError);
    public void visit(SimpleAssignOp SimpleAssignOp);
    public void visit(DesignatorDec DesignatorDec);
    public void visit(DesignatorInc DesignatorInc);
    public void visit(AssignDesignatorStatement AssignDesignatorStatement);
    public void visit(ReadStatement ReadStatement);
    public void visit(PrintStatementArguments PrintStatementArguments);
    public void visit(PrintStatement PrintStatement);
    public void visit(SimpleStatement SimpleStatement);
    public void visit(NoStatementList NoStatementList);
    public void visit(SimpleStatementList SimpleStatementList);
    public void visit(MethodTypeOther MethodTypeOther);
    public void visit(MethodTypeVoid MethodTypeVoid);
    public void visit(EmptyMethodVariableDeclarationList EmptyMethodVariableDeclarationList);
    public void visit(SimpleMethodVarDeclList SimpleMethodVarDeclList);
    public void visit(MethodName MethodName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDeclList NoMethodDeclList);
    public void visit(SimpleMethodDeclList SimpleMethodDeclList);
    public void visit(Type Type);
    public void visit(NoArrayDef NoArrayDef);
    public void visit(SimpleArrayDef SimpleArrayDef);
    public void visit(VarDef VarDef);
    public void visit(VarDeclErrorComma VarDeclErrorComma);
    public void visit(OneVarDeclList OneVarDeclList);
    public void visit(SimpleVarDeclList SimpleVarDeclList);
    public void visit(VarDeclError VarDeclError);
    public void visit(SimpleVarDeclNoError SimpleVarDeclNoError);
    public void visit(ConstantValueBool ConstantValueBool);
    public void visit(ConstantValueNum ConstantValueNum);
    public void visit(ConstantValueChar ConstantValueChar);
    public void visit(ConstDef ConstDef);
    public void visit(OneConstDeclList OneConstDeclList);
    public void visit(SimpleConstDeclList SimpleConstDeclList);
    public void visit(ConstDecl ConstDecl);
    public void visit(MultiVarDecl MultiVarDecl);
    public void visit(MultiConstDecl MultiConstDecl);
    public void visit(NoMultiDeclList NoMultiDeclList);
    public void visit(SimpleMultiDeclList SimpleMultiDeclList);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}

package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor{
	private int mainPC;
	boolean isVoid = false;
	
	public int getMainPc() {
		return mainPC;
	}
	
	public void visit(PrintStatement printStatement) {
		Code.loadConst(2);
		if(printStatement.getExpression().obj.getType().getKind() == Struct.Int || printStatement.getExpression().obj.getType().getKind() == Struct.Bool ) {
			Code.put(Code.print);
		}else {
			Code.put(Code.bprint);
		}
	}
	
	public void visit(ConstFactor constFactor) {
		Code.load(constFactor.obj);
	}
	
	public void visit(MethodName methodName) {
		if(methodName.getMethodName().equals("main")) {
			mainPC = Code.pc;
		}
		
		methodName.obj.setAdr(Code.pc);
		SyntaxNode methodNode = methodName.getParent();
		VarCounter varCounter = new VarCounter();
		methodNode.traverseTopDown(varCounter);
		
		Code.put(Code.enter);
		Code.put(methodName.obj.getLevel());
		Code.put(methodName.obj.getLocalSymbols().size());
		
	}
	
	public void visit(MethodTypeVoid methodTypeVoid) {
		isVoid = true;
	}
	
	public void visit(MethodTypeOther methodTypeOther) {
		isVoid = false;
	}
	
	public void visit(MethodDecl methodDecl) {
		if (isVoid == false) {
			Code.put(Code.trap);
			Code.put(Code.const_1);
		}
		
		Code.put(Code.exit);
		Code.put(Code.return_);
		
	}
	
	public void visit(AssignDesignatorStatement assignDesignatorStatement) {
		Code.store(assignDesignatorStatement.getDesignator().obj);
	}
	
	public void visit(Designator designator) {
		if(designator.obj.getKind() == Obj.Meth || designator.obj.getKind() == Obj.Type)
			return;
		
		if(designator.getDesignatorArrayPart().getClass().equals(SimpleDesignatorArrayPart.class)) {
			Obj arrayObj = Tab.find(designator.getDesignatorName());
			Code.load(arrayObj);
		}else {
			Code.load(designator.obj);
		}
		
		//if it is array/elem		
		if(designator.getDesignatorArrayPart().getClass().equals(SimpleDesignatorArrayPart.class)) {
			SyntaxNode syntaxNode = designator.getParent();
			
			while(syntaxNode != null) {
				if(syntaxNode instanceof Expression) {
					if(((Expression) syntaxNode).obj.getType().getKind() == Struct.Char) {
						Code.put(Code.baload);
					}
					else {
						Code.put(Code.aload);	
					}
					
					return;
				}	
				
				syntaxNode = syntaxNode.getParent();
			}
		}
	}
	
	public void visit(NewFactor newFactor) {
		if(newFactor.obj.getType().getKind() == Struct.Array) {
			Code.put(Code.newarray);
			
			if(newFactor.obj.getType().getElemType().getKind() == Struct.Int || newFactor.obj.getType().getElemType().getKind() == Struct.Bool) {				
				Code.put(1);
			}
			else {
				Code.put(0);
			}
		}
	}
	
	public void visit(DesignatorFactor designatorFactor) {
		if(designatorFactor.getFactorMethodPart().getClass().equals(SimpleFactorMethodPart.class)) {
			int offset = designatorFactor.getDesignator().obj.getAdr() - Code.mainPc;
			
			Code.put(Code.call);
			Code.put2(offset);
		}
	}
	
	public void visit(PrintStatementArguments printStatementArguments) {
		Code.loadConst(printStatementArguments.getN2());
		
		if(printStatementArguments.getExpression().obj.getType().getKind() == Struct.Int || printStatementArguments.getExpression().obj.getType().getKind() == Struct.Bool ) {
			Code.put(Code.print);
		}else {
			Code.put(Code.bprint);
		}
	}
	
	public void visit(ReadStatement readStatement) {
		if(readStatement.getDesignator().obj.getType().getKind() == Struct.Int || readStatement.getDesignator().obj.getType().getKind() == Struct.Bool) {
			Code.put(Code.read);
		}
		else {
			Code.put(Code.bread);
		}
		Code.store(readStatement.getDesignator().obj);
	}
	
	public void visit(DesignatorInc designatorInc) {
		if(designatorInc.getDesignator().obj.getKind() != Obj.Elem ) {
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(designatorInc.getDesignator().obj);
		}
		else {
			Code.put(Code.aload);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.put(Code.astore);
		}
	}
	
	public void visit(DesignatorDec designatorDec) {
		if(designatorDec.getDesignator().obj.getKind() != Obj.Elem ) {
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(designatorDec.getDesignator().obj);
		}
		else {
			Code.put(Code.aload);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.put(Code.astore);
		}
	}
	
}

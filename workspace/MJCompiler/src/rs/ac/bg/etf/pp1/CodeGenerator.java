package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPC;
	boolean isVoid = false;

	public int getMainPc() {
		return mainPC;
	}
	
	//PRINT AND READ
	public void visit(PrintStatement printStatement) {
		Code.loadConst(4);
		if (printStatement.getExpression().obj.getType().getKind() == Struct.Int
				|| printStatement.getExpression().obj.getType().getKind() == Struct.Bool) {
			Code.put(Code.print);
		} else {
			Code.put(Code.bprint);
		}
	}
	
	public void visit(PrintStatementArguments printStatementArguments) {
		Code.loadConst(printStatementArguments.getN2());

		if (printStatementArguments.getExpression().obj.getType().getKind() == Struct.Int
				|| printStatementArguments.getExpression().obj.getType().getKind() == Struct.Bool) {
			Code.put(Code.print);
		} else {
			Code.put(Code.bprint);
		}
	}

	public void visit(ReadStatement readStatement) {
		if (readStatement.getDesignator().obj.getType().getKind() == Struct.Int
				|| readStatement.getDesignator().obj.getType().getKind() == Struct.Bool) {
			Code.put(Code.read);
		} else {
			Code.put(Code.bread);
		}
		Code.store(readStatement.getDesignator().obj);
	}
	
	//METHOD
	public void visit(MethodName methodName) {
		if (methodName.getMethodName().equals("main")) {
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
		Code.put(Code.exit);
		Code.put(Code.return_);

	}

	//STATEMENT
	public void visit(AssignDesignatorStatement assignDesignatorStatement) {
		Code.store(assignDesignatorStatement.getDesignator().obj);
	}
	
	public void visit(ReturnStatement returnStatement) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	//DESIGNATOR
	public void visit(DesignatorStart designatorStart) {
		Designator designator = (Designator) designatorStart.getParent();

		if (designator.obj.getKind() == Obj.Meth || designator.obj.getKind() == Obj.Type)
			return;

		if (designator.getDesignatorArrayPart().getClass().equals(SimpleDesignatorArrayPart.class)) {
			Code.load(designatorStart.obj);
		}

	}
	
	public void visit(DesignatorInc designatorInc) {
		if (designatorInc.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.put(Code.aload);
		}
		else
			Code.load(designatorInc.getDesignator().obj);
				
		Code.put(Code.const_1);
		Code.put(Code.add);
		Code.store(designatorInc.getDesignator().obj);
		
	}

	public void visit(DesignatorDec designatorDec) {
		if (designatorDec.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.put(Code.aload);
		}
		else
			Code.load(designatorDec.getDesignator().obj);
				
		Code.put(Code.const_1);
		Code.put(Code.sub);
		Code.store(designatorDec.getDesignator().obj);
		
	}
	
	//FACTOR
	public void visit(NewFactor newFactor) {
		if (newFactor.obj.getType().getKind() == Struct.Array) {
			Code.put(Code.newarray);

			if (newFactor.obj.getType().getElemType().getKind() == Struct.Int
					|| newFactor.obj.getType().getElemType().getKind() == Struct.Bool) {
				Code.put(1);
			} else {
				Code.put(0);
			}
		}
	}
	
	public void visit(ConstFactor constFactor) {
		Code.load(constFactor.obj);
	}
	
	public void visit(DesignatorFactor designatorFactor) {
		if (designatorFactor.getFactorMethodPart().getClass().equals(SimpleFactorMethodPart.class)) {
			int offset = designatorFactor.getDesignator().obj.getAdr() - Code.pc;

			Code.put(Code.call);
			Code.put2(offset);
		}else {
			Code.load(designatorFactor.getDesignator().obj);
		}
	}
	
	//EXPRESSION
	public void visit(SubExpression subExpression) {
		Code.loadConst(-1);
		Code.put(Code.mul);
	}
	
	public void visit(MultiExpression multiExpression) {
		if(multiExpression.getAddop().getClass().equals(AddopAdd.class))
			Code.put(Code.add);
		else 
			Code.put(Code.sub);
	}
	
	//TERM
	public void visit(MulTerm mulTerm) {
		if(mulTerm.getMulop().getClass().equals(MulopMul.class))
			Code.put(Code.mul);
		else if(mulTerm.getMulop().getClass().equals(MulopDiv.class)) {
			Code.put(Code.div);
		}else {
			Code.put(Code.rem);
		}
	}

}

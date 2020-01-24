package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;
	boolean errorDetected = false;
	int globalCounter = 0;

	Obj currentMethod = null;
	boolean hasReturn;
	boolean isVoid = false;

	Struct lastType = Tab.noType;
	public static final Struct bool = new Struct(Struct.Bool);
	Logger log = Logger.getLogger(getClass());

	public SemanticPass() {
		Tab.insert(Obj.Type, "bool", bool);
	}

	// REPORT
	public String report_node(Obj objectNode) {
		String message = "";
		switch (objectNode.getKind()) {
		case 0:
			message += "Con ";
			break;
		case 1:
			message += "Var ";
			break;
		case 2:
			message += "Type ";
			break;
		case 3:
			message += "Meth ";
			break;
		case 4:
			message += "Fld ";
			break;
		case 5:
			message += "Elem ";
			break;
		case 6:
			message += "Prog ";
			break;
		default:
			break;
		}
		message += objectNode.getName() + ": ";

		int key = objectNode.getType().getKind();
		while (key != 0) {
			switch (key) {
			case 0:
				break;
			case 1:
				message += "int, ";
				key = 0;
				break;
			case 2:
				message += "char, ";
				key = 0;
				break;
			case 3:
				message += "array of ";
				key = objectNode.getType().getElemType().getKind();
				break;
			case 4:
				message += "class, ";
				key = 0;
				break;
			case 5:
				message += "bool, ";
				key = 0;
				break;
			default:
				break;
			}
		}

		message += objectNode.getAdr() + ", " + objectNode.getLevel();
		return message;
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString() + "\n");
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	// PROGRAM
	public void visit(ProgramName programName) {
		programName.obj = Tab.insert(Obj.Prog, programName.getProgName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {

		Obj mainFunc = Tab.find("main");
		if (mainFunc == Tab.noObj || mainFunc.getKind() != Obj.Meth || mainFunc.getType() != Tab.noType) {
			report_error("Ne postoji main funkcija!", null);
		}

		Tab.chainLocalSymbols(program.getProgramName().obj);
		Tab.closeScope();
	}

	// VAR
	public void visit(VarDef varDef) {
		Obj varDefObj = Tab.currentScope.findSymbol(varDef.getVarName());

		if (varDefObj == null) {
			if (varDef.getArrayVarDef().getClass().equals(NoArrayDef.class)) {
				varDef.obj = Tab.insert(Obj.Var, varDef.getVarName(), lastType);
			} else {
				varDef.obj = Tab.insert(Obj.Var, varDef.getVarName(), new Struct(Struct.Array, lastType));
			}

			report_info("Deklarisana promenljiva " + varDef.getVarName(), varDef);
			varDeclCount++;

		} else {
			report_error("Greska na liniji " + varDef.getLine() + ", promenljiva " + varDef.getVarName()
					+ " je vec definisana", null);
		}
	}

	// CONST
	public void visit(ConstDef constDef) {
		Obj constValueObj = constDef.getConstValue().obj;

		if (constValueObj.getType().equals(lastType)) {
			if (Tab.currentScope.findSymbol(constDef.getConstName()) == null) {
				globalCounter++;
				constDef.obj = Tab.insert(Obj.Con, constDef.getConstName(), lastType);
				constDef.obj.setAdr(constValueObj.getAdr());
				report_info("Deklarisana konstanta " + constDef.getConstName(), constDef);

			} else {
				report_error("Greska na liniji " + constDef.getLine() + ", naziv " + constDef.getConstName()
						+ " vec postoji!", null);
			}

		} else {
			report_error("Greska na liniji " + constDef.getLine() + ", konstanta " + constDef.getConstName()
					+ " nije odgovarajuceg tipa!", null);
		}

	}

	public void visit(ConstantValueNum constantValueNum) {
		constantValueNum.obj = new Obj(Obj.Con, "temp", Tab.intType, constantValueNum.getValue(), Obj.NO_VALUE);
	}

	public void visit(ConstantValueChar constantValueChar) {
		constantValueChar.obj = new Obj(Obj.Con, "temp", Tab.charType, constantValueChar.getValue().toCharArray()[1],
				Obj.NO_VALUE);
	}

	public void visit(ConstantValueBool constantValueBool) {

		if (constantValueBool.getValue().toString().equals("true")) {
			constantValueBool.obj = new Obj(Obj.Con, "temp", bool, 1, Obj.NO_VALUE);
		} else {
			constantValueBool.obj = new Obj(Obj.Con, "temp", bool, 0, Obj.NO_VALUE);
		}
	}

	// TYPE
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());

		if (typeNode == Tab.noObj && typeNode.getType().getKind() != Struct.Bool) {
			type.struct = Tab.noType;
			report_error("Greska na liniji " + type.getLine() + ", tip " + type.getTypeName()
					+ " ne postoji u tabeli simbola!", null);
		} else {
			if (Obj.Type == typeNode.getKind()) {
				lastType = typeNode.getType();
				type.struct = typeNode.getType();

			} else {
				type.struct = Tab.noType;
				report_error(
						"Greska na liniji " + type.getLine() + ", naziv" + type.getTypeName() + " ne predstavlja tip!",
						null);
			}
		}

	}

	// METHOD
	public void visit(MethodTypeVoid methodTypeVoid) {
		lastType = Tab.noType;
		isVoid = true;
	}

	public void visit(MethodTypeOther methodTypeOther) {
		lastType = methodTypeOther.getType().struct;
	}

	public void visit(MethodName methodName) {
		Obj methodObj = Tab.currentScope.findSymbol(methodName.getMethodName());

		if (methodObj == null) {
			methodName.obj = Tab.insert(Obj.Meth, methodName.getMethodName(), lastType);
			currentMethod = methodName.obj;
			Tab.openScope();
			hasReturn = false;

			report_info("Definisana funkcija " + methodName.getMethodName(), methodName);

		} else {
			report_error("Greska na liniji " + methodName.getLine() + ", ime funkcije " + methodName.getMethodName()
					+ " vec postoji", null);
		}

	}

	public void visit(MethodDecl methodDecl) {
		if (currentMethod.getType() != Tab.noType && !hasReturn)
			report_error("Greska na liniji " + methodDecl.getLine() + ", nema return naredbe", null);

		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		hasReturn = false;
		isVoid = false;
		currentMethod = null;
	}

	// STATEMENT
	public void visit(AssignDesignatorStatement assignDesignatorStatement) {
		if (assignDesignatorStatement.getDesignator().obj.getKind() != Obj.Var
				&& assignDesignatorStatement.getDesignator().obj.getKind() != Obj.Elem)
			report_error(
					"Greska na liniji " + assignDesignatorStatement.getLine() + ", dodela je moguca samo u var i elem",
					null);

		else if (assignDesignatorStatement.getDesignator().obj.getType()
				.getKind() != assignDesignatorStatement.getExpression().obj.getType().getKind()) {
			report_error("Greska na liniji " + assignDesignatorStatement.getLine() + ", tipovi nisu kompatibilni",
					null);
		} else if (assignDesignatorStatement.getDesignator().obj.getType().getKind() == Struct.Array) {
			if (assignDesignatorStatement.getDesignator().obj.getType().getElemType()
					.getKind() != assignDesignatorStatement.getExpression().obj.getType().getElemType().getKind())
				report_error("Greska na liniji " + assignDesignatorStatement.getLine() + ", tipovi nisu kompatibilni",
						null);
		}

		assignDesignatorStatement.obj = assignDesignatorStatement.getDesignator().obj;
	}

	public void visit(ReturnStatement returnStatement) {
		if (currentMethod != null) {
			hasReturn = true;

			if (isVoid && !returnStatement.getExpressionPart().getClass().equals(NoExpressionPart.class))
				report_error("Greska na liniji " + returnStatement.getLine() + ", funkcija je void", null);

			if (isVoid)
				return;

			if (currentMethod.getType().getKind() != returnStatement.getExpressionPart().obj.getType().getKind())
				report_error("Greska na liniji " + returnStatement.getLine() + ", return nije odgovarajuceg tipa!",
						null);
			else if (currentMethod.getType().getKind() == Struct.Array) {
				if (currentMethod.getType().getElemType().getKind() != returnStatement.getExpressionPart().obj.getType()
						.getElemType().getKind()) {
					report_error("Greska na liniji " + returnStatement.getLine() + ", return nije odgovarajuceg tipa!",
							null);
				}
			}
		} else {
			report_error("Greska na liniji " + returnStatement.getLine() + ", return iskaz je van funkcije!", null);
		}
	}

	// PRINT AND READ
	public void visit(ReadStatement readStatement) {
		if (readStatement.getDesignator().obj.getKind() != Obj.Var
				&& readStatement.getDesignator().obj.getKind() != Obj.Elem)
			report_error("Greska na liniji " + readStatement.getLine() + ", moguce je citati samo elem i var", null);
		else if (readStatement.getDesignator().obj.getType().getKind() != Struct.Int
				&& readStatement.getDesignator().obj.getType().getKind() != Struct.Bool
				&& readStatement.getDesignator().obj.getType().getKind() != Struct.Char) {
			report_error("Greska na liniji " + readStatement.getLine() + ", dati tip se ne moze procitati", null);
		}

	}

	public void visit(PrintStatement printStatement) {
		if (printStatement.getExpression().obj.getType().getKind() != Struct.Int
				&& printStatement.getExpression().obj.getType().getKind() != Struct.Bool
				&& printStatement.getExpression().obj.getType().getKind() != Struct.Char) {
			report_error("Greska na liniji " + printStatement.getLine() + ", dati tip se ne moze ispisati", null);
		}
		printCallCount++;

	}

	public void visit(PrintStatementArguments printStatementArguments) {
		if (printStatementArguments.getExpression().obj.getType().getKind() != Struct.Int
				&& printStatementArguments.getExpression().obj.getType().getKind() != Struct.Bool
				&& printStatementArguments.getExpression().obj.getType().getKind() != Struct.Char) {
			report_error("Greska na liniji " + printStatementArguments.getLine() + ", dati tip se ne moze ispisati",
					null);
		}
		printCallCount++;

	}
	
	//DESIGNATOR
	public void visit(Designator designator) {
		Obj designatorObj = Tab.find(designator.getDesignatorStart().getDesignatorName());

		if (designatorObj == Tab.noObj)
			report_error("Greska na liniji " + designator.getLine() + ", promenljiva "
					+ designator.getDesignatorStart().getDesignatorName() + " nije deklarisana", null);

		if (designator.getDesignatorArrayPart().getClass().equals(SimpleDesignatorArrayPart.class)) {
			SimpleDesignatorArrayPart designatorArrayPart = (SimpleDesignatorArrayPart) designator
					.getDesignatorArrayPart();

			if (designatorArrayPart.getExpression().obj.getType().getKind() != Struct.Int)
				report_error("Greska na liniji " + designator.getLine() + ", indeks nije tipa int", null);
			if (designatorObj.getType().getKind() != Struct.Array)
				report_error("Greska na liniji " + designator.getLine() + ", promenljiva "
						+ designator.getDesignatorStart().getDesignatorName() + " nije tipa niza", null);

			designator.obj = new Obj(Obj.Elem, designator.getDesignatorStart().getDesignatorName(),
					designatorObj.getType().getElemType());
		} else {
			designator.obj = designatorObj;
		}

		report_info("Koriscena promenljiva " + report_node(designatorObj), designator);
	}
	
	public void visit(DesignatorStart designatorStart) {
		designatorStart.obj = Tab.find(designatorStart.getDesignatorName());
	}
	
	public void visit(DesignatorInc designatorInc) {
		if (designatorInc.getDesignator().obj.getKind() != Obj.Var
				&& designatorInc.getDesignator().obj.getKind() != Obj.Elem)
			report_error("Greska na liniji " + designatorInc.getLine() + ", moguce je inkrementirati samo elem i var",
					null);
		else if (designatorInc.getDesignator().obj.getType().getKind() != Struct.Int) {
			report_error("Greska na liniji " + designatorInc.getLine() + ", moguce je inkrementirati samo tip int",
					null);
		}

		designatorInc.obj = designatorInc.getDesignator().obj;
	}

	public void visit(DesignatorDec designatorDec) {
		if (designatorDec.getDesignator().obj.getKind() != Obj.Var
				&& designatorDec.getDesignator().obj.getKind() != Obj.Elem)
			report_error("Greska na liniji " + designatorDec.getLine() + ", moguce je dekrementirati samo elem i var",
					null);
		else if (designatorDec.getDesignator().obj.getType().getKind() != Struct.Int) {
			report_error("Greska na liniji " + designatorDec.getLine() + ", moguce je dekrementirati samo tip int",
					null);
		}

		designatorDec.obj = designatorDec.getDesignator().obj;
	}

	public void visit(DesignatorStatementFactor designatorStatementFactor) {
		if (!designatorStatementFactor.getFactor().getClass().equals(DesignatorFactor.class)) {
			report_error("Greska na liniji " + designatorStatementFactor.getLine() + ", nedozvoljen izraz", null);
		}
	}
	
	//EXPRESSION
	public void visit(FullExpressionPart fullExpressionPart) {
		fullExpressionPart.obj = fullExpressionPart.getExpression().obj;
	}

	public void visit(SimpleExpression simpleExpression) {
		simpleExpression.obj = simpleExpression.getTerm().obj;
	}

	public void visit(SubExpression subExpression) {
		if (subExpression.getTerm().obj.getType().getKind() != Struct.Int)
			report_error(
					"Greska na liniji " + subExpression.getLine() + ", minus se ne moze korisiti ispred ovakvog izraza",
					null);

		subExpression.obj = subExpression.getTerm().obj;
	}

	public void visit(MultiExpression multiExpression) {
		boolean expressionIsInt = false;
		boolean termIsInt = false;

		expressionIsInt = (multiExpression.getExpression().obj.getType().getKind() == Struct.Int);
		termIsInt = (multiExpression.getTerm().obj.getType().getKind() == Struct.Int);

		if (!expressionIsInt || !termIsInt)
			report_error("Greska na liniji " + multiExpression.getLine() + ", izraz nije tipa int", null);

		if (expressionIsInt)
			multiExpression.obj = multiExpression.getExpression().obj;
		else
			multiExpression.obj = multiExpression.getTerm().obj;

	}

	//TERM
	public void visit(SimpleTerm simpleTerm) {
		simpleTerm.obj = simpleTerm.getFactor().obj;
	}

	public void visit(MulTerm mulTerm) {
		boolean factorIsInt = false;
		boolean termIsInt = false;

		factorIsInt = (mulTerm.getFactor().obj.getType().getKind() == Struct.Int);
		termIsInt = (mulTerm.getTerm().obj.getType().getKind() == Struct.Int);

		if (!factorIsInt || !termIsInt)
			report_error("Greska na liniji " + mulTerm.getLine() + ", izraz nije tipa int", null);

		if (factorIsInt)
			mulTerm.obj = mulTerm.getFactor().obj;
		else
			mulTerm.obj = mulTerm.getTerm().obj;
	}

	//FACTOR
	public void visit(ConstFactor constFactor) {
		constFactor.obj = constFactor.getConstValue().obj;
	}

	public void visit(ExpressFactor expressFactor) {
		expressFactor.obj = expressFactor.getExpression().obj;
	}

	public void visit(NewFactor newFactor) {
		newFactor.obj = new Obj(Obj.Var, "", new Struct(Struct.Array, newFactor.getType().struct));

		if (newFactor.getExpression().obj.getType().getKind() != Struct.Int)
			report_error("Greska na liniji " + newFactor.getLine() + ", indeks nije tipa int", null);
	}

	public void visit(DesignatorFactor designatorFactor) {
		designatorFactor.obj = designatorFactor.getDesignator().obj;
	}
	
	//ERROR
	public void visit(AssignOpError assignOpError) {
		errorDetected = true;
	}
	
	public void visit(VarDeclError varDeclError) {
		errorDetected = true;
	}
	
	public void visit(VarDeclErrorComma varDeclErrorComma) {
		errorDetected = true;
	}

}

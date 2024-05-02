package asint;

import asint.SintaxisAbstracta.And;
import asint.SintaxisAbstracta.Array;
import asint.SintaxisAbstracta.ArrobaInstr;
import asint.SintaxisAbstracta.Asignacion;
import asint.SintaxisAbstracta.Bloque;
import asint.SintaxisAbstracta.BloqueInstr;
import asint.SintaxisAbstracta.Campo;
import asint.SintaxisAbstracta.DecProc;
import asint.SintaxisAbstracta.DecType;
import asint.SintaxisAbstracta.DecVar;
import asint.SintaxisAbstracta.DeleteInstr;
import asint.SintaxisAbstracta.Desigual;
import asint.SintaxisAbstracta.Div;
import asint.SintaxisAbstracta.ExpCampo;
import asint.SintaxisAbstracta.False;
import asint.SintaxisAbstracta.Iden;
import asint.SintaxisAbstracta.IfElseInstr;
import asint.SintaxisAbstracta.IfInstr;
import asint.SintaxisAbstracta.Igual;
import asint.SintaxisAbstracta.LitCad;
import asint.SintaxisAbstracta.LitEnt;
import asint.SintaxisAbstracta.LitReal;
import asint.SintaxisAbstracta.Mayor;
import asint.SintaxisAbstracta.MayorIgual;
import asint.SintaxisAbstracta.Menor;
import asint.SintaxisAbstracta.MenorIgual;
import asint.SintaxisAbstracta.Mod;
import asint.SintaxisAbstracta.MuchasDecs;
import asint.SintaxisAbstracta.MuchasExp;
import asint.SintaxisAbstracta.MuchasInstrs;
import asint.SintaxisAbstracta.MuchosCamps;
import asint.SintaxisAbstracta.MuchosParams;
import asint.SintaxisAbstracta.Mul;
import asint.SintaxisAbstracta.Neg;
import asint.SintaxisAbstracta.NewInstr;
import asint.SintaxisAbstracta.NlInstr;
import asint.SintaxisAbstracta.NoDecs;
import asint.SintaxisAbstracta.NoExp;
import asint.SintaxisAbstracta.NoInstrs;
import asint.SintaxisAbstracta.NoParam;
import asint.SintaxisAbstracta.Not;
import asint.SintaxisAbstracta.Null;
import asint.SintaxisAbstracta.Or;
import asint.SintaxisAbstracta.ParamFormRef;
import asint.SintaxisAbstracta.ParamFormal;
import asint.SintaxisAbstracta.ProcInstr;
import asint.SintaxisAbstracta.Prog;
import asint.SintaxisAbstracta.Punt;
import asint.SintaxisAbstracta.ReadInstr;
import asint.SintaxisAbstracta.Resta;
import asint.SintaxisAbstracta.SiDecs;
import asint.SintaxisAbstracta.SiExp;
import asint.SintaxisAbstracta.SiInstrs;
import asint.SintaxisAbstracta.SiParam;
import asint.SintaxisAbstracta.Suma;
import asint.SintaxisAbstracta.TArray;
import asint.SintaxisAbstracta.TBool;
import asint.SintaxisAbstracta.TIden;
import asint.SintaxisAbstracta.TInt;
import asint.SintaxisAbstracta.TPunt;
import asint.SintaxisAbstracta.TReal;
import asint.SintaxisAbstracta.TString;
import asint.SintaxisAbstracta.TStruct;
import asint.SintaxisAbstracta.True;
import asint.SintaxisAbstracta.UnCamp;
import asint.SintaxisAbstracta.UnParam;
import asint.SintaxisAbstracta.UnaDec;
import asint.SintaxisAbstracta.UnaExp;
import asint.SintaxisAbstracta.UnaInstr;
import asint.SintaxisAbstracta.WhileInstr;
import asint.SintaxisAbstracta.WriteInstr;

public class ProcesamientoAbstracto implements Procesamiento {

	@Override
	public void procesa(Prog a) {}

	@Override
	public void procesa(Bloque a) {}

	@Override
	public void procesa(SiDecs a) {}

	@Override
	public void procesa(NoDecs a) {}

	@Override
	public void procesa(MuchasDecs a) {}

	@Override
	public void procesa(UnaDec a) {}

	@Override
	public void procesa(DecProc a) {}

	@Override
	public void procesa(DecType a) {}

	@Override
	public void procesa(DecVar a) {}

	@Override
	public void procesa(SiParam a) {}

	@Override
	public void procesa(NoParam a) {}

	@Override
	public void procesa(MuchosParams a) {}

	@Override
	public void procesa(UnParam a) {}

	@Override
	public void procesa(ParamFormRef a) {}

	@Override
	public void procesa(ParamFormal a) {}

	@Override
	public void procesa(TArray a) {}

	@Override
	public void procesa(TPunt a) {}

	@Override
	public void procesa(TInt a) {}

	@Override
	public void procesa(TReal a) {}

	@Override
	public void procesa(TBool a) {}

	@Override
	public void procesa(TString a) {}

	@Override
	public void procesa(TIden a) {}

	@Override
	public void procesa(TStruct a) {}

	@Override
	public void procesa(MuchosCamps a) {}

	@Override
	public void procesa(UnCamp a) {}

	@Override
	public void procesa(Campo a) {}

	@Override
	public void procesa(SiInstrs a) {}

	@Override
	public void procesa(NoInstrs a) {}

	@Override
	public void procesa(MuchasInstrs a) {}

	@Override
	public void procesa(UnaInstr a) {}

	@Override
	public void procesa(ArrobaInstr a) {}

	@Override
	public void procesa(ProcInstr a) {}

	@Override
	public void procesa(NlInstr a) {}

	@Override
	public void procesa(NewInstr a) {}

	@Override
	public void procesa(ReadInstr a) {}

	@Override
	public void procesa(WriteInstr a) {}

	@Override
	public void procesa(DeleteInstr a) {}

	@Override
	public void procesa(WhileInstr a) {}

	@Override
	public void procesa(IfElseInstr a) {}

	@Override
	public void procesa(IfInstr a) {}

	@Override
	public void procesa(BloqueInstr a) {}

	@Override
	public void procesa(SiExp a) {}

	@Override
	public void procesa(NoExp a) {}

	@Override
	public void procesa(MuchasExp a) {}

	@Override
	public void procesa(UnaExp a) {}

	@Override
	public void procesa(Asignacion a) {}

	@Override
	public void procesa(Suma a) {}

	@Override
	public void procesa(And a) {}

	@Override
	public void procesa(Or a) {}

	@Override
	public void procesa(Resta a) {}

	@Override
	public void procesa(Menor a) {}

	@Override
	public void procesa(Mayor a) {}

	@Override
	public void procesa(MenorIgual a) {}

	@Override
	public void procesa(MayorIgual a) {}

	@Override
	public void procesa(Igual a) {}

	@Override
	public void procesa(Desigual a) {}

	@Override
	public void procesa(Mul a) {}

	@Override
	public void procesa(Div a) {}

	@Override
	public void procesa(Mod a) {}

	@Override
	public void procesa(Neg a) {}

	@Override
	public void procesa(Array a) {}

	@Override
	public void procesa(ExpCampo a) {}

	@Override
	public void procesa(Punt a) {}

	@Override
	public void procesa(Not a) {}

	@Override
	public void procesa(LitEnt a) {}

	@Override
	public void procesa(LitReal a) {}

	@Override
	public void procesa(Iden a) {}

	@Override
	public void procesa(True a) {}

	@Override
	public void procesa(False a) {}

	@Override
	public void procesa(LitCad a) {}

	@Override
	public void procesa(Null a) {}
}

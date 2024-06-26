package asint;

import asint.SintaxisAbstracta.*;

public interface Procesamiento {
	void procesa(Prog a);
	void procesa(Bloque a);
    void procesa(SiDecs a);
    void procesa(NoDecs a);
    void procesa(MuchasDecs a);
    void procesa(UnaDec a);
    void procesa(DecProc a);
    void procesa(DecType a);
    void procesa(DecVar a);
    void procesa(SiParam a);
    void procesa(NoParam a);
    void procesa(MuchosParams a);
    void procesa(UnParam a);
    void procesa(ParamFormRef a);
    void procesa(ParamFormal a);
    void procesa(TArray a);
    void procesa(TPunt a);
    void procesa(TInt a);
    void procesa(TReal a);
    void procesa(TBool a);
    void procesa(TString a);
    void procesa(TIden a);
    void procesa(TStruct a);
    void procesa(MuchosCamps a);
    void procesa(UnCamp a);
    void procesa(Campo a);
    void procesa(SiInstrs a);
    void procesa(NoInstrs a);
    void procesa(MuchasInstrs a);
    void procesa(UnaInstr a);
    void procesa(ArrobaInstr  a);
    void procesa(ProcInstr  a);
    void procesa(NlInstr  a);
    void procesa(NewInstr  a);
    void procesa(ReadInstr  a);
    void procesa(WriteInstr  a);
    void procesa(DeleteInstr  a);
    void procesa(WhileInstr  a);
    void procesa(IfElseInstr  a);
    void procesa(IfInstr  a);
    void procesa(BloqueInstr a);
    void procesa(SiExp a);
    void procesa(NoExp a);
    void procesa(MuchasExp a);
    void procesa(UnaExp a);
    void procesa(Asignacion a);
    void procesa(Suma a);
    void procesa(And a);
    void procesa(Or a);
    void procesa(Resta a);
    void procesa(Menor a);
    void procesa(Mayor a);
    void procesa(MenorIgual a);
    void procesa(MayorIgual a);
    void procesa(Igual a);
    void procesa(Desigual a);
    void procesa(Mul a);
    void procesa(Div a);
    void procesa(Mod a);
    void procesa(Neg a);
    void procesa(Array a);
    void procesa(ExpCampo a);
    void procesa(Punt a);
    void procesa(Not a);
    void procesa(LitEnt a);
    void procesa(LitReal a);
    void procesa(Iden a);
    void procesa(True a);
    void procesa(False a);
    void procesa(LitCad a);
    void procesa(Null a);
}

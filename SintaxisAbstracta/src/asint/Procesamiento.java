package asint;

import asint.SintaxisAbstracta.*;

public interface Procesamiento {
    void procesa(SiDecs a);
    void procesa(NoDecs a);
    void procesa(MuchasDecs a);
    void procesa(UnaDec a);
    void procesa(DecProg a);
    void procesa(DecType a);
    void procesa(DecVar a);
    void procesa(SiParam a);
    void procesa(NoParam a);
    void procesa(UnParam a);
    void procesa(MuchosParams a);
    void procesa(TArray a);
    void procesa(TPunt a);
    void procesa(TInt a);
    void procesa(TReal a);
    void procesa(TBool a);
    void procesa(TString a);
    void procesa(TIden a);
    void procesa(TStruct a);
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
    void procesa(UnaExp a);
    void procesa(MuchasExp a);
    void procesa(Asignacion a);
    void procesa(Suma a);
    void procesa(Resta a);
    void procesa(Mul a);
    void procesa(Div a);
    void procesa(Mod a);
    void procesa(Array a);
    void procesa(ExpCampo a);
    void procesa(Punt a);
}
